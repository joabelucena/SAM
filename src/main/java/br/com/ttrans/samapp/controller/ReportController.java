package br.com.ttrans.samapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import br.com.ttrans.samapp.library.DAO;
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
	
	
	
//	@RequestMapping(value = "/setReportInfo", method = RequestMethod.POST)
//	@ResponseStatus(value=HttpStatus.OK)
//	public void spagoSetReport(HttpServletRequest request
//			, @RequestParam(required=true, value="label") String label
//			, Authentication aut
//			, Locale locale
//			, HttpServletResponse response){
//		
//		String user = dao.getMv("SYS_SPGUSER"	, "");
//		String pass = dao.getMv("SYS_SPGPASS"	, "");
//		String url =  dao.getMv("SYS_SPGURL"	, "");
//		String role = dao.getMv("SYS_SPGROLE"	, "");
//		String message = null;
//		
//		HttpSession session = request.getSession();
//		
//		//Retorna o documento 
//		SDKDocument document = getDoc(user, pass, label ,message);
//		
//		//Encontrou Documento
//		if(document != null){
//			session.setAttribute("spagobi_user"			, user				);
//			session.setAttribute("spagobi_pwd"			, pass				);
//			session.setAttribute("spagobi_documentId"	, document.getId()	);
//			session.setAttribute("spagobi_role"			, role				);
//			session.setAttribute("spagobi_url"			, url				);
//			
//		}else{
//			session.setAttribute("spagobi_userMessage", message);
//		}
//	}
//	
//	/**
//	 * This function returns a Spago Document from a 'label' passed as parameter.
//	 * @param user
//	 * @param pass
//	 * @param label
//	 * @param message
//	 * @return SDKDocument (SpagoBi Report)
//	 */
//	private SDKDocument getDoc(String user, String pass, String label, String message){
//		
//		SDKDocument document = null;	
//		
//		DocumentsServiceProxy proxy = new DocumentsServiceProxy(user, pass);
//		
//		String endpoint = dao.getMv("SYS_SPGEND"	, "http://10.27.0.1:8180/SpagoBI/sdk/DocumentsService");
//		
//		proxy.setEndpoint(endpoint);
//		
//		try {
//			
//			SDKDocument[] documents = proxy.getDocumentsAsList(null, null, null);
//			
//			for(int i = 0; i < documents.length ; i ++ ){
//				if(documents[i].getLabel().equals(label)) document = documents[i];
//			}
//
//			
//		} catch (RemoteException e) {
//			message = "Ocorreram erros na execução do relatório. Entre em contato com o administrador do sistema";
//			logger.error(e.getMessage());			
//		}
//		
//		return document;
//	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/jasper", produces={"application/pdf"})
	public void getRpt1(
			@RequestParam(required=true, value="label") String label,
//			@RequestBody Map<String, Object> params,
			HttpServletResponse response) throws JRException, IOException {
	
//	public void getRpt1(HttpServletResponse response) throws JRException, IOException {
		
//		System.out.println("chegou");
//		String label = "RPT01";
		
		logger.info("Path: " + (REPORTS_PATH + label + ".jasper"));
		
		BasicDataSource ds = (BasicDataSource) appContext.getBean("dataSource");
		
		try {
			InputStream jasperStream = this.getClass().getResourceAsStream(REPORTS_PATH + label + ".jasper");
			
			Map<String, Object> params = new HashMap<>();
			
			
			params.put("logo_ttrans", "/home/joabe/Documents/workspace_jasper/SAM_REPORTS/img/TTRANS.png");

//			params.put("Cartao de"			, "0");
//			params.put("Cartao ate"			, "999");

//			params.put("Nome de"			, "A");
//			params.put("Nome ate"			, "Z");

//			params.put("Tipo Cartao de"		, "VT");
//			params.put("Tipo Cartao ate"	, "VT");
			
			params.put("EquDe", "TAG-001-002");
			params.put("EquAte", "TAG-001-010");
			
			
//			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(REPORTS_PATH + label + ".jrxml"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds.getConnection());
			
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

			response.setContentType("application/pdf; charset=UTF-8");
			response.setHeader("Content-disposition", "inline; filename=" + label + ".pdf");
//			response.setHeader("Content-Length", "999999");
			
			
			

			OutputStream outStream = response.getOutputStream();

			
//			JasperRunManager.runReportToPdfStream(jasperStream, outStream, params, ds.getConnection());
//			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			
			
			conf.setPdfVersion(PdfVersionEnum.VERSION_1_7);
			
			
			JRPdfExporter exporter = new JRPdfExporter();
			
			
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
//			exporter.set
			
			
			exporter.setConfiguration(conf);
			
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
//			exporter.setParameter(JRPdfExporterParameter.PDF_VERSION, JRPdfExporterParameter.PDF_VERSION_1_7);
			
			exporter.exportReport();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/jasperget", produces={"application/pdf"})
	public void getRpt2(
//			@RequestParam(required=true, value="label") String label,
			@RequestParam Map<String, Object> params,
			HttpServletResponse response) throws JRException, IOException, URISyntaxException {
		
		
		String label = (String) params.remove("label");
		
		String logoTtrans = this.getClass().getClassLoader().getResource(REPORTS_PATH+"img/TTRANS.png").toURI().toString();
		
		params.put("logo_ttrans", logoTtrans);
		
		
		
		logger.info("Path: " + (REPORTS_PATH + label + ".jasper"));
		
		BasicDataSource ds = (BasicDataSource) appContext.getBean("dataSource");
		
		try {
			InputStream jasperStream = this.getClass().getResourceAsStream(REPORTS_PATH + label + ".jasper");
			
			
			JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(REPORTS_PATH + label + ".jrxml"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds.getConnection());
			
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=" + label + ".pdf");

			OutputStream outStream = response.getOutputStream();
			
//			JasperRunManager.runReportToPdfStream(jasperStream, outStream, params, ds.getConnection());
//			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			conf.setPdfVersion(PdfVersionEnum.VERSION_1_7);
			
			JRPdfExporter exporter = new JRPdfExporter();
			
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
			exporter.setConfiguration(conf);
			
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
//			exporter.setParameter(JRPdfExporterParameter.PDF_VERSION, JRPdfExporterParameter.PDF_VERSION_1_7);
			
			exporter.exportReport();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
