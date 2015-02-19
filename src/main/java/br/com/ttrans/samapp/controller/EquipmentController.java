package br.com.ttrans.samapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ttrans.samapp.service.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService service;
	
	@RequestMapping("/getInfo/{id}")
	public void getEquipInfo(Map<String, Object> map
							,@PathVariable("id") String id){
		
		map.put("equipment"	, this.service.getEquipment(id));
		map.put("mtbf"		, 0);
		map.put("mttr"		, 0);
		map.put("urlVideo"	, "");
		map.put("urlManual"	, "");
	}
}
