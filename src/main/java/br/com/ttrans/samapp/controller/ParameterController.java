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

import br.com.ttrans.samapp.service.ParameterService;
import br.com.ttrans.samapp.model.Parameters;

@Controller
@RequestMapping("/params")
public class ParameterController {
	@Autowired
	private ParameterService service;

	// Verificar, deve ser quando o link "Cadastros>Parametros" for acionado
	@RequestMapping("/params")
	public String setupForm(Map<String, Object> map) {

		Parameters parameter = new Parameters();
		map.put("parameter", parameter);
		map.put("parameterList", service.getAllParameter());

		return "entries/parameters/show";
	}
	
	@RequestMapping("/load")
	@ResponseBody
	public List<Parameters> loadData(Map<String, Object> map) {

		List<Parameters> paramList = service.getAllParameter();
		
		return paramList;
	}

	// ####### Incluir / Editar / Excluir #######
	@RequestMapping(value = { "/{action}/{par_id}" }, method = RequestMethod.GET)
	public String editPerson(@PathVariable("action") String action,
			@PathVariable("par_id") int par_id,
			@ModelAttribute Parameters parameter, BindingResult result,
			Map<String, Object> map, HttpServletRequest request) {


		System.out.println("action: " + action);
		System.out.println("par_id: " + this.service.getParameter(par_id));

		switch (action.toLowerCase()) { 

		case "add":
			map.put("title", "parameter.title.add");
			map.put("hidden", "hidden");
			map.put("disabled", "false");
			map.put("parameter", parameter);
			break;
		case "edit":
			map.put("title", "parameter.title.edit");
			map.put("hidden", "");
			map.put("disabled", "false");
			map.put("parameter", this.service.getParameter(par_id));
			break;
		case "delete":
			map.put("title", "parameter.title.remove");
			map.put("hidden", "");
			map.put("disabled", "true");
			map.put("parameter", this.service.getParameter(par_id));
			break;
		case "search":

			break;
		}

		return "entries/parameters/form";
	}

	// Action do formulario <form action="parameter.do">
	@RequestMapping(value = "/{action}/parameter.do", method = RequestMethod.POST)
	public String doActions(
			@ModelAttribute("parameter") @Valid Parameters parameter,
			BindingResult result, @PathVariable("action") String action,
			Map<String, Object> map,
			Authentication authentication) {

		System.out.println("Nome: " + authentication.getName());
		
		System.out.println("hasErrors(): " + result.hasErrors());
		if (result.hasErrors()) {

			switch (action.toLowerCase()) { 

			case "add":
				map.put("title", "parameter.title.add");
				map.put("hidden", "hidden");
				map.put("disabled", "false");
				break;
			case "edit":
				map.put("title", "parameter.title.edit");
				map.put("hidden", "");
				map.put("disabled", "false");
				break;
			case "delete":
				map.put("title", "parameter.title.remove");
				map.put("hidden", "");
				map.put("disabled", "true");
				break;
			case "search":
				break;
			}

			return "entries/parameters/form";
		}


		@SuppressWarnings("unused")
		Parameters parameterResult = new Parameters();

		switch (action.toLowerCase()) {

		case "add":
			parameter.setUsr_insert(authentication.getName());
			service.add(parameter);
			parameterResult = parameter;
			break;
		case "edit":
			parameter.setUsr_update(authentication.getName());
			service.edit(parameter);
			parameterResult = parameter;
			break;
		case "delete":
			parameter.setUsr_update(authentication.getName());
			parameter.setDeleted("*");
			service.edit(parameter);
			parameterResult = new Parameters();
			break;
		case "search":
			Parameters searchedParameter = service
					.getParameter(parameter.getPar_id());
			parameterResult = searchedParameter != null ? searchedParameter
					: new Parameters();
			break;
		}

		return "redirect:/params/";
	}

}
