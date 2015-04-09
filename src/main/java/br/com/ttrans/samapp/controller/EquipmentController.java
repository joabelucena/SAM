package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.CounterService;
import br.com.ttrans.samapp.service.EquipmentManufacturerService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EquipmentTypeService;

@Controller
@RequestMapping("/equipment")
@SuppressWarnings("rawtypes")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CounterService counterService;

	@Autowired
	private EquipmentManufacturerService manufactureService;
	
	@Autowired
	private EquipmentTypeService typeService;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", equipmentService.loadData());
		
		return result;
	}
}
