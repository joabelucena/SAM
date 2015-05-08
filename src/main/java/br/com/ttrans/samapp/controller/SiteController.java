package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.SiteService;

@Controller
@RequestMapping("/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	
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
}
