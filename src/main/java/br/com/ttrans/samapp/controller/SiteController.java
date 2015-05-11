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

import br.com.ttrans.samapp.model.ServiceStation;
import br.com.ttrans.samapp.service.SiteService;
import br.com.ttrans.samapp.service.ServiceStationService;

@Controller
@RequestMapping("/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ServiceStationService stationService;
	
	
	/*
	 * Load Data Methods
	 */
	@RequestMapping("/load")
	@ResponseBody
	public Map<String, Object> loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", siteService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/station")
	@ResponseBody
	public Map<String, Object> loadStation() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", stationService.loadData());
		
		return result;
	}
	
	
	/*
	 * CRUD Operations for: ServiceStation
	 */
	@RequestMapping("/station/add.action")
	@ResponseBody
	public Map<String,Object> addstation(@RequestBody ServiceStation station, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			stationService.add(station, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/station/update.action")
	@ResponseBody
	public Map<String,Object> updatestation(@RequestBody ServiceStation station, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			stationService.edit(station, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/station/delete.action")
	@ResponseBody
	public Map<String,Object> deletestation(@RequestBody ServiceStation station, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			stationService.delete(station, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		return result;
	}
	
}
