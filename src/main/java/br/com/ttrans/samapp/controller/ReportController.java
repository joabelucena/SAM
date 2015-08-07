package br.com.ttrans.samapp.controller;

import it.eng.spagobi.sdk.documents.bo.SDKDocument;
import it.eng.spagobi.sdk.proxy.DocumentsServiceProxy;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/rpt")
public class ReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@RequestMapping(value = "/setReportInfo", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void rptAlarms(HttpServletRequest request
			, @RequestParam(required=true, value="label") String label
			, Authentication aut
			, Locale locale
			, HttpServletResponse response){
		
		String user = "biuser";
		String pass = "biuser";
		String message = null;
		
		HttpSession session = request.getSession();
		
		//Retorna o documento 
		SDKDocument document = getDoc(user, pass, label ,message);
		
		//Encontrou Documento
		if(document != null){
			session.setAttribute("spagobi_user"			, user				);
			session.setAttribute("spagobi_pwd"			, pass				);
			session.setAttribute("spagobi_documentId"	, document.getId()	);
			session.setAttribute("spagobi_role"			, "spagobi/user"	);
			
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
		proxy.setEndpoint("http://10.114.0.130:8180/SpagoBI/sdk/DocumentsService");
		
		
		try {
//			document = proxy.getDocumentByLabel(label); // Not working =/
			
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

}
