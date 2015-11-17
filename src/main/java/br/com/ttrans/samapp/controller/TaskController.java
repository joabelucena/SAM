package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map<String, Object> loadData(){
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", service.loadData());
		
		return result;
	}
	
	/*
	 * CRUD Operations for: Task
	 */
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTask(@RequestBody Task task,
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			service.add(task, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map<String, Object> updateTask(@RequestBody Task task, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response,
            Locale locale) {
		
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
	public Map<String, Object> deleteTask(@RequestBody Task task, 
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
	
	@RequestMapping("/run.action")
	@ResponseBody
	public Map<String, Object> runTask(@RequestBody Task task, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			service.proccess(task);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	

}
