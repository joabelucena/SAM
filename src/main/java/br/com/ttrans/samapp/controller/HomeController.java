package br.com.ttrans.samapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.JSon;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.Users;
import br.com.ttrans.samapp.service.AlarmService;
import br.com.ttrans.samapp.service.CounterService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.SiteService;
import br.com.ttrans.samapp.service.TaskService;

/**
 * Handles requests for the application home page.
 */
@Scope("session")
@Controller
public class HomeController {
	
	@Autowired
	private JSon json;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CounterService counterService;
	
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
		
		String result = null;
		
		try {
			result = json.toJson(user.getRole().getMenus());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/*
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test(HttpServletRequest request, Authentication auth){
		Task task = taskService.get(1);
		
		taskService.proccess(task);
			
	}
	*/
	
	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<String> getTime(HttpServletRequest request, Authentication auth){
		
		return new ResponseEntity<String>(new SimpleDateFormat("hh:mm:ss a").format(new Date()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	public ResponseEntity<String> getUser(HttpServletRequest request, Authentication aut){
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		return new ResponseEntity<String>(user.getUsername() + " | " +user.getRole().getRoleName() , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public String test(@RequestParam(defaultValue="",required=false,value="alarm") String alarm,
			@RequestParam(defaultValue="",required=false,value="equipment") String equipment,
			HttpServletRequest request, Authentication aut){
		
		Task task = taskService.get(1);
		
		/*
		Task task = new Task();
		Set<TaskCondition> conditions = new HashSet<TaskCondition>();
		TaskCondition cond = new TaskCondition();
		
		cond.setField("test");
		cond.setLogicOper("OU");
		cond.setRelOper(">=");
		cond.setTask(task);
		cond.setType(TaskType.ALARM);
		cond.setSeq("01");
		cond.setValue(2);
		cond.setInsert("JOABE");
		
		conditions.add(cond);
		
		task.setActive(1);
		task.setAlarm(new Alarm("XPTOLEVE"));
		task.setDesc("TESTE HIBERNATE");
		task.setInsert("JOABE");
		task.setItems(conditions);

		
		taskService.add(task, aut);
		*/
		
		//Alarm al2 = new Alarm("LALAL");
		/*
		Alarm al = alarmService.get(alarm);
		Equipment eq = equipmentService.get(equipment);
		
		counterService.countIt(al,eq);
		//taskService.proccess(task);
		*/
		
		/*
		Alarm al = new Alarm(alarm);
		Equipment eq = new Equipment(equipment);
		
		counterService.reset(al, eq);
		*/
		
		taskService.proccess(task);
		return "SAM test method";
	}
}