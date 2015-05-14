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

import br.com.ttrans.samapp.model.DocumentType;
import br.com.ttrans.samapp.service.DocumentTypeService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentTypeService typeService;
	
	
	/*
	 * Load Data Methods
	 */
	@RequestMapping("/load/type")
	@ResponseBody
	public Map<String,Object> loadType() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", typeService.loadData());
		
		return result;
	}
	

	/*
	 * CRUD Operations for: DocumentType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map<String,Object> addManufacturer(@RequestBody DocumentType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map<String,Object>
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			typeService.add(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/type/update.action")
	@ResponseBody
	public Map<String,Object> updateManufacturer(@RequestBody DocumentType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map<String,Object>
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			typeService.edit(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/type/delete.action")
	@ResponseBody
	public Map<String,Object> deleteManufacturer(@RequestBody DocumentType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map<String,Object>
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			typeService.delete(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	

}
