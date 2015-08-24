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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.JsonDeserialisationTest;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Task;
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
	public Map<String, Object> menuLoad(HttpServletRequest request, Locale locale, Model model, Authentication authentication) {
		
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<Menu> menu = new ArrayList<Menu>();
		
		Iterator<Menu> it = user.getRole().getMenus().iterator();
		
		while(it.hasNext()){
			Menu mn = it.next();
			if(mn.getParent() == null){
				menu.add(mn);
			}
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
	
	@RequestMapping(value = "/jsonTest", method = RequestMethod.GET)
	@ResponseBody
	public String jsonTest(){
		ModelMap map = new ModelMap();
		logger.info("HibernateAware");
//		HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
		String json = "{\"id\":\"Sam.model.Task-1\",\"desc\":\"ASDASDASD\",\"active\":\"Y\",\"alarm_id\":\"BMNORMAL\",\"alarm_desc\":\"\",\"alarm\":{\"id\":\"BMNORMAL\",\"desc\":\"\",\"counterInc\":\"\",\"manNorm\":\"\",\"isNorm\":\"\",\"alarm_id\":\"\",\"group_id\":0,\"type_id\":0,\"model_id\":0,\"severity_id\":0,\"group_desc\":\"\",\"type_desc\":\"\",\"severity_desc\":\"\",\"model_desc\":\"\",\"alarm_desc\":\"\"},\"equipments\":[{\"id\":\"XPTO_01\",\"fixedAsset\":null,\"serviceTag\":null,\"ip\":\"\",\"type\":{\"id\":1,\"desc\":\"RELOGIO MESTRE\",\"insert\":\"SYSTEM\",\"update\":null},\"model\":{\"id\":1,\"desc\":\"QW-TIME III\",\"protocol\":{\"id\":3,\"desc\":\"SNMPv1\",\"insert\":\"SYSTEM\",\"update\":null},\"insert\":\"SYSTEM\",\"update\":null},\"manufacturer\":{\"id\":1,\"desc\":\"WESTERSTRAND\",\"insert\":\"SYSTEM\",\"update\":null},\"site\":{\"id\":1,\"desc\":\"WASHINGTON LUIS\",\"shortname\":\"WLU\",\"parent\":null,\"type\":{\"id\":1,\"desc\":\"ESTACAO\",\"insert\":\"joabe\",\"update\":null},\"station\":{\"id\":1,\"desc\":\"BASE A\",\"insert\":\"JOABE\",\"update\":null},\"insert\":\"joabe\",\"update\":null},\"system\":{\"id\":\"SSH\",\"desc\":\"SISTEMA DE SINCRONISMO HORARIO\",\"insert\":\"SYSTEM\",\"update\":null},\"documents\":[],\"warranty\":0,\"oid\":\"1.3.6.1.4.1.25281.1025.0\",\"mtbfPrev\":0,\"mtbfCalc\":0,\"mtbfManf\":0,\"installDate\":1438959019204,\"manufactureDate\":1438959019204,\"acquiredDate\":1438959019204,\"remark\":\"\",\"insert\":\"TESTE\",\"update\":null,\"fixed_asset\":\"\",\"service_tag\":\"\",\"mtbf_prev\":0,\"mtbf_calc\":0,\"mtbf_manf\":0,\"install_date\":null,\"manufacture_date\":null,\"acquired_date\":null,\"type_id\":1,\"type_desc\":\"RELOGIO MESTRE\",\"model_id\":1,\"model_desc\":\"QW-TIME III\",\"manufacturer_id\":1,\"manufacturer_desc\":\"WESTERSTRAND\",\"site_id\":1,\"site_desc\":\"WASHINGTON LUIS\",\"system_id\":null,\"system_desc\":\"SISTEMA DE SINCRONISMO HORARIO\",\"document_id\":0,\"document_desc\":\"\",\"document_url\":\"\",\"active\":true},{\"id\":\"10.114.0.151\",\"fixedAsset\":null,\"serviceTag\":null,\"ip\":\"10.114.0.151\",\"type\":{\"id\":1,\"desc\":\"RELOGIO MESTRE\",\"insert\":\"SYSTEM\",\"update\":null},\"model\":{\"id\":1,\"desc\":\"QW-TIME III\",\"protocol\":{\"id\":3,\"desc\":\"SNMPv1\",\"insert\":\"SYSTEM\",\"update\":null},\"insert\":\"SYSTEM\",\"update\":null},\"manufacturer\":{\"id\":1,\"desc\":\"WESTERSTRAND\",\"insert\":\"SYSTEM\",\"update\":null},\"site\":{\"id\":1,\"desc\":\"WASHINGTON LUIS\",\"shortname\":\"WLU\",\"parent\":null,\"type\":{\"id\":1,\"desc\":\"ESTACAO\",\"insert\":\"joabe\",\"update\":null},\"station\":{\"id\":1,\"desc\":\"BASE A\",\"insert\":\"JOABE\",\"update\":null},\"insert\":\"joabe\",\"update\":null},\"system\":{\"id\":\"SSH\",\"desc\":\"SISTEMA DE SINCRONISMO HORARIO\",\"insert\":\"SYSTEM\",\"update\":null},\"documents\":[],\"warranty\":0,\"oid\":\"1.3.6.1.4.1.25281.1025.0\",\"mtbfPrev\":0,\"mtbfCalc\":0,\"mtbfManf\":0,\"installDate\":1438959019214,\"manufactureDate\":1438959019214,\"acquiredDate\":1438959019214,\"remark\":\"\",\"insert\":\"TESTE\",\"update\":null,\"fixed_asset\":\"\",\"service_tag\":\"\",\"mtbf_prev\":0,\"mtbf_calc\":0,\"mtbf_manf\":0,\"install_date\":null,\"manufacture_date\":null,\"acquired_date\":null,\"type_id\":1,\"type_desc\":\"RELOGIO MESTRE\",\"model_id\":1,\"model_desc\":\"QW-TIME III\",\"manufacturer_id\":1,\"manufacturer_desc\":\"WESTERSTRAND\",\"site_id\":1,\"site_desc\":\"WASHINGTON LUIS\",\"system_id\":null,\"system_desc\":\"SISTEMA DE SINCRONISMO HORARIO\",\"document_id\":0,\"document_desc\":\"\",\"document_url\":\"\",\"active\":true}]}";
//		Task task = mapper.readValue(json, Task.class); // this
																		// line
																		// throw
																		// the
																		// exception
		
		
		/*
		JsonDeserialisationTest teste = new JsonDeserialisationTest();
		
		try{
			teste.allClassesUsedByOurControllersShouldBeDeserialisableByJackson();
		}catch(Exception e){
			
		}
		*/
		
		return "SAM test method";
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
		
		taskService.proccess(task);
		return "SAM test method";
	}
}