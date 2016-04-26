package br.com.ttrans.samapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.ClassFinder;
import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.User;

/**
 * Handles requests for the application home page.
 */
@Scope("session")
@Controller
public class HomeController {

	//Endpoint package for scanning
	private static final String WSPACKAGE = "br.com.ttrans.samapp.ws.endpoint";

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is {}.", locale);

		return "index";
	}

	/**
	 * Lists all web services and its informations
	 */
	@RequestMapping(value = { "/services", "/services/" }, method = RequestMethod.GET)
	public String listServices(Locale locale, Model model, HttpServletRequest request) {


		List<Class<?>> classes = ClassFinder.find(WSPACKAGE);
		
		List<String[]> list = new ArrayList<String[]>();

		String path = request.getRequestURL().substring(0,
				request.getRequestURL().indexOf(request.getContextPath()) + request.getContextPath().length());

		for (Class<?> obj : classes) {

			if (obj.isAnnotationPresent(WebInfo.class)) {

				WebInfo ws = (WebInfo) obj.getAnnotation(WebInfo.class);
				
				String[] info = { ws.name(), ws.description(),  path.concat(ws.url()) };		
				
				list.add(info);
			}

		}
		
		model.addAttribute("lists", list);

		return "services";
	}

	/**
	 * Loads User menu info
	 * @param request
	 * @param locale
	 * @param model
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/menu/load", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> menuLoad(HttpServletRequest request, Locale locale, Model model,
			Authentication authentication) {

		User user = (User) request.getSession().getAttribute("loggedUser");

		Map<String, Object> result = new HashMap<String, Object>();

		List<Menu> menu = new ArrayList<Menu>();

		Iterator<Menu> it = user.getRole().getMenus().iterator();

		while (it.hasNext()) {
			Menu mn = it.next();
			if (mn.getParent() == null)
				menu.add(mn);
		}

		result.put("items", menu);

		return result;
	}

	/**
	 * Retrieves Current time
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<Date> getTime(HttpServletRequest request) {

		return new ResponseEntity<Date>(new Date(), HttpStatus.OK);
	}

	/**
	 * Retrieves logged user name and Role
	 * 
	 * @param request
	 * @param aut
	 * @return
	 */
	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	@ResponseBody
	public String getUser(HttpServletRequest request, Authentication aut) {

		User user = (User) request.getSession().getAttribute("loggedUser");

		return user.getUsername() + " | " + user.getRole().getRoleName();
	}
	
	
	/**
	 * Test URL
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testeSam(HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		return result;
	}
}