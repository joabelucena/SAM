package br.com.ttrans.samapp.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.JSon;
import br.com.ttrans.samapp.model.Users;

/**
 * Handles requests for the application home page.
 */
@Scope("session")
@Controller
public class HomeController {
	
	@Autowired
	private JSon json;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
	
		return "index";
	}
	
	@RequestMapping(value = "/menu/load", method = RequestMethod.GET)
	@ResponseBody
	public String menuLoad(HttpServletRequest request, Locale locale, Model model, Authentication authentication) {
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		String result = "";
		
		try {
			result += json.toJson(user.getRole().getMenus());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<String> getTime(HttpServletRequest request, Authentication auth){
		Date date = new Date();
		Format formato = new SimpleDateFormat("hh:mm:ss a");
		
		return new ResponseEntity<String>(formato.format(date), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	public ResponseEntity<String> getUser(HttpServletRequest request, Authentication aut){
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
				
		return new ResponseEntity<String>(user.getUsername() + " | " +user.getRole().getRoleName() , HttpStatus.OK);
	}
}