package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.service.KPIService;

@Controller
@RequestMapping("/kpi")
public class KPIController {

	@Autowired
	private KPIService service;

	@RequestMapping("/load")
	@ResponseBody
	public Map<String, Object> getMtbf(HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", service.loadAll());

		return result;
	}

}
