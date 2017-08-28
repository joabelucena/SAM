package br.com.ttrans.samapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import br.com.ttrans.samapp.library.DAO;
import it.eng.spagobi.sdk.documents.bo.SDKDocument;
import it.eng.spagobi.sdk.proxy.DocumentsServiceProxy;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.type.PdfVersionEnum;


@Controller
@RequestMapping("/rpt")
public class ReportController {
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private ApplicationContext appContext;
	
	private static final String REPORTS_PATH = "/WEB-INF/reports/";
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	
	
	@RequestMapping(value = "/setReportInfo", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void spagoSetReport(HttpServletRequest request
			, @RequestParam(required=true, value="label") String label
			, Authentication aut
			, Locale locale
			, HttpServletResponse response){
		
		String user = dao.getMv("SYS_SPGUSER"	, "");
		String pass = dao.getMv("SYS_SPGPASS"	, "");
		String url =  dao.getMv("SYS_SPGURL"	, "");
		String role = dao.getMv("SYS_SPGROLE"	, "");
		String message = null;
		
		HttpSession session = request.getSession();
		
		//Retorna o documento 
		SDKDocument document = getDoc(user, pass, label ,message);
		
		//Encontrou Documento
		if(document != null){
			session.setAttribute("spagobi_user"			, user				);
			session.setAttribute("spagobi_pwd"			, pass				);
			session.setAttribute("spagobi_documentId"	, document.getId()	);
			session.setAttribute("spagobi_role"			, role				);
			session.setAttribute("spagobi_url"			, url				);
			
		}else{
			session.setAttribute("spagobi_userMessage", message);
		}
	}
	
	/**
	 * This function returns a Spago Document from a 'label' passed as parameter.
	 * @param user
	 * @param pass
	 * @param label
	 * @param message
	 * @return SDKDocument (SpagoBi Report)
	 */
	private SDKDocument getDoc(String user, String pass, String label, String message){
		
		SDKDocument document = null;	
		
		DocumentsServiceProxy proxy = new DocumentsServiceProxy(user, pass);
		
		String endpoint = dao.getMv("SYS_SPGEND"	, "http://10.27.0.1:8180/SpagoBI/sdk/DocumentsService");
		
		proxy.setEndpoint(endpoint);
		
		try {
			
			SDKDocument[] documents = proxy.getDocumentsAsList(null, null, null);
			
			for(int i = 0; i < documents.length ; i ++ ){
				if(documents[i].getLabel().equals(label)) document = documents[i];
			}

			
		} catch (RemoteException e) {
			message = "Ocorreram erros na execução do relatório. Entre em contato com o administrador do sistema";
			logger.error(e.getMessage());			
		}
		
		return document;
	}

	/**
	 * This method generates report according to 'params' parameter. The 'params' parameter must specify at least the 'label' parameter which is used to retrieve
	 * report bin file at reports path: /WEB-INF/reports/
	 * 
	 * @param params
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getReport", produces={"application/pdf"})
	public void getReport(
			@RequestParam Map<String, Object> params,
			HttpServletResponse response) throws JRException, IOException, URISyntaxException {
		
		//Retrieves param 'label' from params. Also, this param is removed in order to pass this collection as param source for Jasper.
		String label = (String) params.remove("label");
		
		
		String logoTtrans = this.getClass().getClassLoader().getResource(REPORTS_PATH+"img/TTRANS.png").toURI().toString();
		
		params.put("logo_ttrans", logoTtrans);
		
		logger.debug("Path: " + (REPORTS_PATH + label + ".jasper"));
		
		//Retrieves DataSource bean set on spring-context file (/WEB-NF/spring/appServlet/app-servlet.xml)
		BasicDataSource ds = (BasicDataSource) appContext.getBean("dataSource");
		
		try {
			
			logger.debug(params.toString());
			
			//Compiles JRXML file
			JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(REPORTS_PATH + label + ".jrxml"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds.getConnection());
			
			//Set response parameters
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=" + label + ".pdf");

			OutputStream outStream = response.getOutputStream();
			
			//Set report configuration
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			conf.setPdfVersion(PdfVersionEnum.VERSION_1_7);
			conf.setMetadataTitle("Relatorio SAM");
			
			//Set exporter configuration parameters
			JRPdfExporter exporter = new JRPdfExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
			exporter.setConfiguration(conf);
			
			//Exports report
			exporter.exportReport();
			
			
		} catch (Exception e) {
			logger.error("Erro ao gerar relatório Jasper: " + label + " - " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns a JSON based file for setting items on Report Parameter window
	 * 
	 * 
	 * @param label
	 * @param response
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/param")
	public Map<String, Object> getParam(
			@RequestParam(required=true, value="label") String label,
			HttpServletResponse response) throws JRException, IOException {
		
		Map<String, Object> data = new HashMap<>();

		try(InputStream inputStream = this.getClass().getResourceAsStream(REPORTS_PATH + label + ".param")){
			String file = IOUtils.toString(inputStream);
			
			ObjectMapper mapper = new ObjectMapper();
			
			MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
			
			data = mapper.readValue(file, type);
			
		}

		return data;
		
	}

}
