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

import br.com.ttrans.samapp.library.MailClient;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.service.StatusRuleService;
import br.com.ttrans.samapp.service.TaskService;

/**
 * Handles requests for the application home page.
 */
@Scope("session")
@Controller
public class HomeController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private StatusRuleService ruleService;

	@Autowired
	private MailClient client;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		logger.info("Welcome home! The client locale is {}.", locale);

		return "index";
	}

	@RequestMapping(value = "/menu/load", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> menuLoad(HttpServletRequest request,
			Locale locale, Model model, Authentication authentication) {

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

		String cMessage = "<html>Oi <b>Joabe</b><br><br></html>";

		client.sendMail(new String[] { "joabelucena@gmail.com" }	//PARA
				,new String[] { "joabelucena@hotmail.com" }			//CC
				,new String[] { "jlucena@ttrans.com.br" }			//CCO
				,"TESTE com CC e CCO",								//ASSUNTO
				cMessage);											//MENSAGEM

		return "test";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testeSam(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("loggedUser");

		List<ServiceOrderStatus> status = ruleService.getStatusByRole(user
				.getRole());

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", status);

		return result;
	}
}