package br.com.ttrans.samapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.MailClient;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.TaskCondition;
import br.com.ttrans.samapp.model.Users;
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
	private MailClient client;
	
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
	public Map<String, Object> menuLoad(HttpServletRequest request, 
			Locale locale, 
			Model model, 
			Authentication authentication) {
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<Menu> menu = new ArrayList<Menu>();
		
		Iterator<Menu> it = user.getRole().getMenus().iterator();
		
		while(it.hasNext()){
			Menu mn = it.next();
			if(mn.getParent() == null) menu.add(mn);
		}
		
		result.put("items", menu);
		
		return result;
	}
	
	@RequestMapping(value = "/gettime", method = RequestMethod.POST)
	public ResponseEntity<Date> getTime(HttpServletRequest request){
		
		return new ResponseEntity<Date>(new Date(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuser", method = RequestMethod.POST)
	public ResponseEntity<String> getUser(HttpServletRequest request, Authentication aut){
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		return new ResponseEntity<String>(user.getUsername() + " | " +user.getRole().getRoleName() , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	@ResponseBody
	public String taskTest(){
		
		String cMessage = "<html>Oi <b>Joabe</b><br><br></html>";
		
		
		client.sendMail(new String[]{"joabelucena@gmail.com"},
				new String[]{"gabriellypontez.gp@gmail.com","joabelucena@hotmail.com"},
				new String[]{"jlucena@ttrans.com.br"},
				"TESTE com CC e CCO",
				cMessage);
		
		return "test";
	}
	
	@RequestMapping(value = "/test/task", method = RequestMethod.POST)
	@ResponseBody
	public String taskTest(
			@RequestParam(defaultValue="",required=false,value="str1") String str1,
			@RequestParam(defaultValue="",required=false,value="str2") String str2,
			@RequestParam(defaultValue="",required=false,value="int1") int int1,
			@RequestParam(defaultValue="",required=false,value="int2") int int2,
			HttpServletRequest request, Authentication aut){
		
		Task task = taskService.get(int1);
		
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
		
//		Set<Equipment> equips = task.getEquipments();
		Set<TaskCondition> conds = task.getConditions();
		
		Iterator<TaskCondition> it = conds.iterator();
		
		while(it.hasNext()){
			TaskCondition cd = it.next();
			
			if(!it.hasNext()){
				conds.remove(cd);
//				cd.setTask(null);
			}
		}
		
		System.out.println("para");
		
		
		taskService.edit(task, aut);
//		taskService.proccess(task);
		
		
		return "SAM test method";
	}
}