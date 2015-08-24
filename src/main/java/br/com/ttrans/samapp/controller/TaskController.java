package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.service.TaskService;

@Controller
@RequestMapping("/task")
@SuppressWarnings("rawtypes")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData(){
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", service.loadData());
		
		return result;
	}
	
	/*
	 * CRUD Operations for: Task
	 */
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
	@ResponseBody
	public Map addTask(@RequestBody Map task,
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
//			service.add(task, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map updateTask(@RequestBody Task task, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			service.edit(task, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public Map deleteTask(@RequestBody Task task, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			service.delete(task, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	

}
