package br.com.ttrans.samapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.StatusRuleService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/so")
public class StatusRuleController {

	@Autowired
	private StatusRuleService service;
	
	@Autowired
	private EquipmentService test;

	@RequestMapping("/rules/")
	public String home() {

		return "entries/so/rules/show";
	}
	
	@RequestMapping(value = { "/rules/{action}/{sru_id}" }, method = RequestMethod.GET)
	public String handle(@PathVariable("action") String action,
			@PathVariable("sru_id") int id,
			@ModelAttribute StatusRule rule, BindingResult result,
			Map<String, Object> map, HttpServletRequest request) {

		doMap(rule , map, action, false, id);
		
		return "entries/so/rules/form";
	}

	@RequestMapping("/rules/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {

		List list = service.loadData();

		Gson gson = new Gson();

		String result = "{\"data\":" + gson.toJson(list) + "}";

		System.out.println(result);

		return result;
	}

	public void doMap(StatusRule rule, Map<String, Object> map, String action,
			Boolean lError, int id) {

		switch (action.toLowerCase()) {

		case "add":
			map.put("title", "view.form.statusrule.add");
			map.put("hidden", "hidden");
			map.put("disabled", "false");
			break;
		case "edit":
			map.put("title", "view.form.statusrule.edit");
			map.put("hidden", "");
			map.put("disabled", "false");
			break;
		case "delete":
			map.put("title", "view.form.statusrule.remove");
			map.put("hidden", "");
			map.put("disabled", "true");
			break;
		case "search":
			break;
		}

		// Caso Tenha retornado erro da validação do objeto
		if (lError) {
			map.put("rule", rule);
		} else {
			if (action.toLowerCase().equals("add")) {
				map.put("rule", rule);
			} else {
				map.put("rule", this.service.getRule(id));
			}
		}
	}
}
