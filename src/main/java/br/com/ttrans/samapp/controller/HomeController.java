package br.com.ttrans.samapp.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.JSon;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.service.MenuService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SuppressWarnings("rawtypes")
public class HomeController {
	
	@Autowired
	private MenuService service;
	
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
	public String menuLoad(Locale locale, Model model, Authentication authentication) {
		
		String result = "";
				
		List<Menu> menu = service.loadMenu(null);
		
		try {
			result += json.toJson(menu, (List) authentication.getAuthorities());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<String> getTime(){
		Date date = new Date();
		Format formato = new SimpleDateFormat("hh:mm:ss a");
		
		return new ResponseEntity<String>(formato.format(date), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	public ResponseEntity<String> getUser(Authentication aut){
		
		return new ResponseEntity<String>(aut.getName(), HttpStatus.OK);
	}
}