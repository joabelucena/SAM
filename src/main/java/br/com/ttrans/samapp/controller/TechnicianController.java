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

import br.com.ttrans.samapp.model.Technician;
import br.com.ttrans.samapp.service.TechnicianService;;

@Controller
@RequestMapping("/technician")
@SuppressWarnings("rawtypes")
public class TechnicianController {
	
	@Autowired
	private TechnicianService techService;
	
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadLog() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", techService.loadData());
		
		return result;
	}

	
	/*
	 * CRUD Operations for: Technician
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map addModel(@RequestBody Technician technician, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			techService.add(technician, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody Technician technician, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			techService.edit(technician, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody Technician technician, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			techService.delete(technician, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
}
