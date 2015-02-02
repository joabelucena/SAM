package br.com.ttrans.samapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.ttrans.samapp.model.Site;
import br.com.ttrans.samapp.model.SiteType;
import br.com.ttrans.samapp.model.SiteType;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.SiteTypeService;
import br.com.ttrans.samapp.validator.impl.SiteTypeValidator;

@Controller
@RequestMapping("/sites")
public class SiteTypeController {

	@Autowired
	private SiteTypeService service;
	
	@Autowired
	private SiteTypeValidator validator;
	
	@Autowired
	private EquipmentService equip;

	@RequestMapping("/types/")
	public String sites(Map<String, Object> map) {

		return "entries/sites/types/show";
	}

	@RequestMapping("/types/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {

		List types = service.loadData();

		Gson gson = new Gson();

		String result = "{\"data\":" + gson.toJson(types) + "}";

		System.out.println(result);

		return result;
	}

	// ####### Incluir / Editar / Excluir #######
	@RequestMapping(value = { "/types/{action}/{sty_id}" }, method = RequestMethod.GET)
	public String editType(@PathVariable("action") String action,
			@PathVariable("sty_id") int sty_id,
			@ModelAttribute SiteType siteType, BindingResult result,
			Map<String, Object> map, HttpServletRequest request) {

		doMap(siteType , map, action, false, sty_id);

		return "entries/sites/types/form";
	}

	// Action do formulario <form action="siteType.do">
	@RequestMapping(value = "/types/{action}/siteType.do", method = RequestMethod.POST)
	public String doActions(
			@ModelAttribute("siteType") @Valid SiteType siteType
			,BindingResult result
			,@PathVariable("action") String action
			,Map<String, Object> map
			,Authentication authentication) {
		
		if (!result.hasErrors()){
			validator.validate(siteType, result, action);
		}
		
		
		if (result.hasErrors()){
			
			//Monta collection
			doMap(siteType , map, action, true, 0);

			return "entries/sites/types/form";
		}
		
		switch (action.toLowerCase()) {

		case "add":
			siteType.setUsr_insert(authentication.getName());
			service.add(siteType);
			break;
		case "edit":
			siteType.setUsr_update(authentication.getName());
			service.edit(siteType);
			break;
		case "delete":
			siteType.setUsr_update(authentication.getName());
			siteType.setDeleted("*");
			service.edit(siteType);
			break;
		case "search":
			break;
		}
		
		
		return "redirect:/sites/types/";
		
	}

	public void doMap(SiteType siteType
			,Map<String, Object> map
			,String action
			,Boolean lError
			,int id) {

		switch (action.toLowerCase()) {

		case "add":
			map.put("title", "view.form.sitetype.add");
			map.put("hidden", "hidden");
			map.put("disabled", "false");
			break;
		case "edit":
			map.put("title", "view.form.sitetype.edit");
			map.put("hidden", "");
			map.put("disabled", "false");
			break;
		case "delete":
			map.put("title", "view.form.sitetype.remove");
			map.put("hidden", "");
			map.put("disabled", "true");
			break;
		case "search":
			break;
		}

		// Caso Tenha retornado erro da validação do objeto
		if (lError) {
			map.put("siteType", siteType);
		} else {
			if (action.toLowerCase().equals("add")) {
				map.put("siteType", siteType);
			} else {
				map.put("siteType", this.service.getSiteType(id));
			}
		}
	}

}
