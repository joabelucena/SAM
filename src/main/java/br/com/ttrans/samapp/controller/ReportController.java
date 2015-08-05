package br.com.ttrans.samapp.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/rpt/")
public class ReportController {
	
	
	@RequestMapping(value = "/1", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void rptAlarms(HttpServletRequest request
			, Authentication aut, Locale locale, HttpServletResponse response){
		
		String user = "biuser";
		String pass = "biuser";
		
		HttpSession session = request.getSession();
		
		session.setAttribute("spagobi_user"			, user				);
		session.setAttribute("spagobi_pwd"			, pass				);
		session.setAttribute("spagobi_documentId"	, 195			);
		session.setAttribute("spagobi_role"			, "spagobi/user"	);
		session.setAttribute("spagobi_documents", new ArrayList<String>());
		
		
	}

}
