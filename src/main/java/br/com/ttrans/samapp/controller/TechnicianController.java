package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.TechnicianService;

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

}
