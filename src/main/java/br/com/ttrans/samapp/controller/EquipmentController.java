package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.CounterService;
import br.com.ttrans.samapp.service.EquipmentManufacturerService;
import br.com.ttrans.samapp.service.EquipmentModelService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EquipmentTypeService;
/*
 * 
 * TAke a look at this: http://www.sencha.com/forum/showthread.php?106571
 * 
 */
@Controller
@RequestMapping("/equipment")
@SuppressWarnings("rawtypes")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CounterService counterService;

	@Autowired
	private EquipmentManufacturerService manufacturerService;
	
	@Autowired
	private EquipmentModelService modelService;
	
	@Autowired
	private EquipmentTypeService typeService;
	
	
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", equipmentService.loadData());
		
		return result;
	}

	@RequestMapping("/load/manufacturer")
	@ResponseBody
	public Map loadManufacturer() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", manufacturerService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/type")
	@ResponseBody
	public Map loadType() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", typeService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/model")
	@ResponseBody
	public Map loadModel() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", modelService.loadData());
		
		return result;
	}	
	
	@RequestMapping("/load/counter")
	@ResponseBody
	public Map loadCounter() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", counterService.loadData());
		
		return result;
	}
	
	
	@RequestMapping("/manufacturer/add.action")
	@ResponseBody
	public Map addManufacturer(HttpServletRequest request,
            HttpServletResponse response) {
		
		request.getParameter("data");
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		System.out.println("### add!");
		
		//result.put("data", counterService.loadData());
		
		return result;
	}
	
	@RequestMapping("/manufacturer/update.action")
	@ResponseBody
	public Map updateManufacturer(HttpServletRequest request,
            HttpServletResponse response) {
		
		request.getParameter("data");
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		System.out.println("### update!");
		
		//result.put("data", counterService.loadData());
		
		return result;
	}
	
	@RequestMapping("/manufacturer/delete.action")
	@ResponseBody
	public Map deleteManufacturer(HttpServletRequest request,
            HttpServletResponse response) {
		
		request.getParameter("data");
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		System.out.println("### delete!");
		
		//result.put("data", counterService.loadData());
		
		return result;
	}
	
}
