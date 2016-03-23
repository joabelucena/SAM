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

//		@SuppressWarnings("all")
//		final class WssDefinition {
//
//			private String name;
//			private String description;
//			private String url;
//
//			public WssDefinition(String name, String description, String url) {
//				this.name = name;
//				this.description = description;
//				this.url = url;
//			}
//
//			public String getName() {
//				return name;
//			}
//
//			public String getDescription() {
//				return description;
//			}
//
//			public String getUrl() {
//				return url;
//			}
//
//		}

		List<Class<?>> classes = ClassFinder.find(WSPACKAGE);
		
		List<String[]> list = new ArrayList<String[]>();

//		List<WssDefinition> list = new ArrayList<WssDefinition>();

		String path = request.getRequestURL().substring(0,
				request.getRequestURL().indexOf(request.getContextPath()) + request.getContextPath().length());

		for (Class<?> obj : classes) {

			if (obj.isAnnotationPresent(WebInfo.class)) {

				WebInfo ws = (WebInfo) obj.getAnnotation(WebInfo.class);

				System.out.println("Class Name: " + obj.getName());

				System.out.println("---------");

				System.out.println("Description :" + ws.description());
				System.out.println("Name :" + ws.name());
				System.out.println("URL :" + ws.url());

				System.out.println("---------");
				
				String[] info = { ws.name(), ws.description(),  path.concat(ws.url()) };		
				
				list.add(info);
				
//				list.add(new String[]{"firstarg", "secondarg", "thirdarg"});

//				list.add(ws.name(), ws.description(), path.concat(ws.url()));

			}

		}

		// System.out.println("teste");

		// try {
		//
		// Document doc = DocumentBuilderFactory.newInstance()
		// .newDocumentBuilder()
		// .parse(this.getClass().getResourceAsStream(WSCONTEXT));
		//
		// doc.getDocumentElement().normalize();
		//
		// NodeList nList = doc.getElementsByTagName("wss:binding");
		//
		// for (int temp = 0; temp < nList.getLength(); temp++) {
		//
		// Node nNode = nList.item(temp);
		//
		// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		//
		// Element eElement = (Element) nNode;
		//
		// list.add(new WssDefinition( eElement.getAttribute("name")
		// ,eElement.getAttribute("description")
		// ,path.concat(eElement.getAttribute("url"))));
		//
		// }
		// }
		//
		//
		// } catch (Exception e) {
		// logger.error("Error trying to open file: " + WSCONTEXT);
		// logger.error(e.getMessage());
		// }

		model.addAttribute("lists", list);

		return "services";
	}

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

	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<Date> getTime(HttpServletRequest request) {

		return new ResponseEntity<Date>(new Date(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	@ResponseBody
	public String getUser(HttpServletRequest request, Authentication aut) {

		User user = (User) request.getSession().getAttribute("loggedUser");

		return user.getUsername() + " | " + user.getRole().getRoleName();
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	@ResponseBody
	public String taskTest() {

		for (int i = 0; i <= 20; i++) {
			//
			// String cMessage = "<html>Oi <b>Xuvisco!</b><br><br>Esse é o
			// email: " + i+".</html>";
			//
			// client.sendMail(new String[] { "bruno.souza@inylbra.com.br" }
			// //PARA
			// ,new String[] {} //CC
			// ,new String[] {} //CCO
			// ,"TESTE Mail", //ASSUNTO
			// cMessage); //MENSAGEM

		}

		return "test";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testeSam(HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		return result;
	}
}