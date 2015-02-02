package br.com.ttrans.samapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.ServiceOrderStatusService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/so")
public class ServiceOrderStatusController {
	
	@Autowired
	private ServiceOrderStatusService service;
	
	@RequestMapping("/status/")	
	public String sites(Map<String, Object> map){
		
		return "entries/so/status/show";
	}
	
	
	@RequestMapping("/status/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {

		List list = service.loadData();
		
		Gson gson = new Gson();
		
		String result = "{\"data\":"+gson.toJson(list) +"}";
		
		System.out.println(result);
		
		return result;
	}

}
