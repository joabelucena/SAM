package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Parameter;
import br.com.ttrans.samapp.service.ParameterService;

@Controller
@RequestMapping("/parameter")
@SuppressWarnings("rawtypes")
public class ParameterController {
	
	@Autowired
	private ParameterService service;
	
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadLog() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", service.loadData());
		
		return result;
	}

	
	/*
	 * CRUD Operations for: Parameter
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map addModel(@RequestBody Parameter parameter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			service.add(parameter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody Parameter parameter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			service.edit(parameter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody Parameter parameter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			service.delete(parameter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
}
