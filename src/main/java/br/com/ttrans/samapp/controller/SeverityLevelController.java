package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.SeverityLevelService;

@Controller
@RequestMapping("/severity")
@SuppressWarnings("rawtypes")
public class SeverityLevelController {
	
	@Autowired
	private SeverityLevelService service;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", service.loadData());
		
		return result;
	}
}
