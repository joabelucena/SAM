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

import br.com.ttrans.samapp.model.Site;
import br.com.ttrans.samapp.service.SiteService;
import br.com.ttrans.samapp.service.SiteTypeService;
import br.com.ttrans.samapp.validator.impl.SiteValidator;

import com.google.gson.Gson;

@Controller
@RequestMapping("/sites")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private SiteValidator validator;
	
	@Autowired
	private SiteTypeService typeService;
	
	/**
	 * @author Joabe
	 * @param map
	 * @return
	 */
	@RequestMapping("/sites/")	
	public String sites(Map<String, Object> map){
		
		return "entries/sites/sites/show";
	}
	
	/**
	 * @author Joabe
	 * @param map
	 * @return
	 */
	@RequestMapping("/sites/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {

		List siteList = siteService.loadData();
		
		Gson gson = new Gson();
		
		String result = "{\"data\":"+gson.toJson(siteList) +"}";
		
		System.out.println(result);
		
		return result;
	}
	
	// ####### Incluir / Editar / Excluir #######
	/**
	 * @author Joabe
	 * @param action
	 * @param sit_id
	 * @param site
	 * @param result
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/sites/{action}/{sit_id}" }, method = RequestMethod.GET)
	public String editSite(@PathVariable("action") String action,
			@PathVariable("sit_id") int sit_id,
			@ModelAttribute Site site, BindingResult result,
			Map<String, Object> map, HttpServletRequest request) {

		doMap(site , map, action, false, sit_id);
		
		return "entries/sites/sites/form";
	}

	// Action do formulario <form action="site.do">
	/**
	 * @author Joabe
	 * @param site
	 * @param result
	 * @param action
	 * @param map
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/sites/{action}/site.do", method = RequestMethod.POST)
	public String doActions(
			@ModelAttribute("site") @Valid Site site
			,BindingResult result
			,@PathVariable("action") String action
			,Map<String, Object> map
			,Authentication authentication) {
		
		
		/*Object*/
		if(site.getParent().getSit_id() == 0){
			site.setParent(null);
		}
		
		if(site.getType().getSty_id() == 0){
			site.setType(null);
		}
		
		/*Validations*/
		if (!result.hasErrors()){
			validator.validate(site, result, action);
		}
		
		if (result.hasErrors()){
			
			//Monta collection
			doMap(site , map, action, true, 0);
			
			return "entries/sites/sites/form";
		}		
		
		
		/*DAO*/
		switch (action.toLowerCase()) {
		
		case "add":
			site.setUsr_insert(authentication.getName());
			siteService.add(site);
			break;
		case "edit":
			site.setUsr_update(authentication.getName());
			siteService.edit(site);
			break;
		case "delete":
			site.setUsr_update(authentication.getName());
			site.setDeleted("*");
			siteService.edit(site);
			break;
		case "search":
			break;
		}
		
		
		return "redirect:/sites/sites/";
	}
	
	/**
	 * @param site
	 * @param map
	 * @param action
	 * @param lError
	 * @param sit_id
	 */
	public void doMap(Site site
						,Map<String, Object> map
						,String action
						,Boolean lError
						,int id){
		
		switch (action.toLowerCase()) {

		case "add":
			map.put("title", "view.form.site.add");
			map.put("hidden", "hidden");
			map.put("disabled", "false");
			break;
		case "edit":
			map.put("title", "view.form.site.edit");
			map.put("hidden", "");
			map.put("disabled", "false");
			break;
		case "delete":
			map.put("title", "view.form.site.remove");
			map.put("hidden", "");
			map.put("disabled", "true");
			break;
		case "search":
			break;
		}
		
		//Caso Tenha retornado erro da validação do objeto
		if (lError){
			map.put("site", site);
		}else{
			if(action.toLowerCase().equals("add")){
				map.put("site", site);
			}else{
				map.put("site", this.siteService.getSite(id));
			}
		}
	}
}
