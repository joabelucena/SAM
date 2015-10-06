package br.com.ttrans.samapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.QueryException;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderJob;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.ServiceOrderType;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.service.ServiceOrderJobService;
import br.com.ttrans.samapp.service.ServiceOrderLogService;
import br.com.ttrans.samapp.service.ServiceOrderService;
import br.com.ttrans.samapp.service.ServiceOrderStatusService;
import br.com.ttrans.samapp.service.ServiceOrderTypeService;
import br.com.ttrans.samapp.service.StatusRuleService;
import br.com.ttrans.samapp.validator.ErrorMessageHandler;
import br.com.ttrans.samapp.validator.impl.ServiceOrderValidator;
import br.com.ttrans.samapp.validator.impl.StatusRuleValidator;

@Controller
@RequestMapping("/so")
@SuppressWarnings("rawtypes")
public class ServiceOrderController {

	@Autowired
	private EventService eventService;

	@Autowired
	private ServiceOrderService soService;

	@Autowired
	private ServiceOrderTypeService soTypeService;

	@Autowired
	private ServiceOrderStatusService soStatusService;
	
	@Autowired
	private ServiceOrderLogService soLogService;
	
	@Autowired
	private StatusRuleService ruleService;

	@Autowired
	private StatusRuleValidator statusRuleValidator;
	
	@Autowired
	private ServiceOrderValidator serviceOrderValidator;

	@Autowired
	private ServiceOrderJobService jobService;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ErrorMessageHandler errorMessageHandler;

	private static final Logger logger = LoggerFactory.getLogger(ServiceOrderController.class);
	
	/**
	 * Load Data Methods
	 */
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", soService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/log")
	@ResponseBody
	public Map loadLog() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", soLogService.loadData());
		
		return result;
	}

	@RequestMapping("/load/job")
	@ResponseBody
	public Map loadJob() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", jobService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/type")
	@ResponseBody
	public Map loadType() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", soTypeService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/status")
	@ResponseBody
	public Map loadStatus() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", soStatusService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/rules")
	@ResponseBody
	public Map loadRules() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", ruleService.loadData());
		
		return result;
	}
	
	@RequestMapping(value = "/newFromEvent", method = RequestMethod.POST)
	public Map<String, Object> newFromEvent(
			@RequestParam(value = "eveId"			, required = true) long eveId,
			@RequestParam(value = "startForecast"	, required = true) @DateTimeFormat(pattern="dd/MM/yyyy - HH:mm") Date startForecast,
			@RequestParam(value = "endForecast"		, required = true) @DateTimeFormat(pattern="dd/MM/yyyy - HH:mm") Date endForecast,
			@RequestParam(value = "type"			, required = true) String type,
			@RequestParam(value = "obs"				, required = true) String obs,
			Authentication authentication,
			Locale locale) {
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result"	,"");
		result.put("soId"	, 0);
		
		//Instancia objeto para tratamento com o banco
		ServiceOrder so = new ServiceOrder();
		
		//Instancia objeto para tratamento de erros
		Errors err = new BindException(so, "serviceorder");
		
		//Retorna evento
		Event event = eventService.get(eveId);
		
		//Retorna status inicial cadastrado em parametro		
		ServiceOrderStatus sNewSts = soStatusService.findByName(dao.getMv("SAM_SOSTATUS", "")); 

		//Cria objeto de log
		ServiceOrderLog log = new ServiceOrderLog(	sNewSts,					//Status De 
													sNewSts,					//Status Para	
													authentication.getName(),	//Usuario
													new Date(),					//Data/Hora
													obs,						//Observacao						
													authentication.getName());	//Usuario inserção (USR_INSERT)

		//Instancia LogSet
		Set<ServiceOrderLog> logSet = new HashSet<ServiceOrderLog>();

		try {

			//Log da OS
			logSet.add(log);

			//OS
			so.setEquipment(event.getEquipment());				// Equipamento
			so.setType(soTypeService.findByName(type));			// Tipo de Os (VERIFICAR)
			so.setEvent(event);									// Evento
			so.setStatus(sNewSts);								// Status
			so.setStartForecast(startForecast);					// Previsao de Inicio
			so.setEndForecast(endForecast);						// Previsao de Termino
			so.setLog(logSet);									// Log Inicial
			so.setPriority(event.getAlarm().getSeverity());		// Severidade
			so.setRemark(obs);									// Observação

			serviceOrderValidator.validate(so, err, new User(), "add");
			
			if(!err.hasErrors()){
				
				int id = soService.add(so, authentication);
				result.put("result"	,messageSource.getMessage("response.Ok", null, locale));
				result.put("soId"	, id);
				
				return result;
				
			}else{
				
				//Erros nas validacoes de negocio
				result.put("result"	,messageSource.getMessage("response.so.Failure", null, locale)+
						errorMessageHandler.toStringList(err, locale));
				
				return result;
			}
			
		//Query Errors
		} catch (QueryException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return result;

		//Not Found Objects
		} catch (NullPointerException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.so.NullPointer", null, locale));
			
			return result;
		
		//Erros genericos
		} catch (GenericJDBCException e){
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return result;
			
		} 
	}

	@RequestMapping("/getAllowedStatus")
	@ResponseBody
	public Map<String, Object> getAllowedStatus(
			Authentication authentication,
			HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("loggedUser");
		
		List<ServiceOrderStatus> status = ruleService.getStatusByRole(user.getRole());
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", status);
		
		return result;
		
	}

	/**
	 * CRUD Operations for: ServiceOrder
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map addSo(@RequestBody ServiceOrder so, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result"	,"");
		result.put("id"		, 0);
		
		try{
			result.put("id"		, soService.add(so, authentication));			
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map updateSo(@RequestBody ServiceOrder so, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		Errors errors = new BindException(so, "serviceorder");
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		serviceOrderValidator.validate(	so														,	// Object to validate
										errors													,	// Binding Errors Object
										(User) request.getSession().getAttribute("loggedUser")	,	// Logged User
										"edit");													// Action
		//Check if any errors were found
		if(errors.hasErrors()){
			
			//TODO verificar como retornar o erro do objeto BindException
			result.put("message"	,"Mudanca de estado não permitida. Siga o fluxo da OS.");
			
		}else{
			
			try{
				soService.edit(so, authentication);
			}catch(Exception e){
				result.put("message",e.getMessage());
			}
		}
		
		return result;
	}
	
	/**
	 * CRUD Operations for: ServiceOrderJob
	 */
	@RequestMapping("/job/add.action")
	@ResponseBody
	public Map addJob(@RequestBody ServiceOrderJob job, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			jobService.add(job, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/job/update.action")
	@ResponseBody
	public Map updateJob(@RequestBody ServiceOrderJob job, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			jobService.edit(job, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/job/delete.action")
	@ResponseBody
	public Map deleteJob(@RequestBody ServiceOrderJob job, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			jobService.delete(job, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	/**
	 * CRUD Operations for: ServiceOrderType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map addType(@RequestBody ServiceOrderType sotype, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soTypeService.add(sotype, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/type/update.action")
	@ResponseBody
	public Map updateType(@RequestBody ServiceOrderType sotype, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soTypeService.edit(sotype, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/type/delete.action")
	@ResponseBody
	public Map deleteType(@RequestBody ServiceOrderType sotype, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			soTypeService.delete(sotype, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * CRUD Operations for: SO Status Service
	 */
	@RequestMapping("/status/add.action")
	@ResponseBody
	public Map<String,Object> addStatus(@RequestBody ServiceOrderStatus sostatus, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soStatusService.add(sostatus, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/status/update.action")
	@ResponseBody
	public Map updateStatus(@RequestBody ServiceOrderStatus sostatus, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soStatusService.edit(sostatus, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/status/delete.action")
	@ResponseBody
	public Map deleteStatus(@RequestBody ServiceOrderStatus sostatus, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			soStatusService.delete(sostatus, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	/**
	 * CRUD Operations for: SO Status Rules
	 */
	@RequestMapping("/rules/add.action")
	@ResponseBody
	public Map addRule(@RequestBody StatusRule rule, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			ruleService.add(rule, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/rules/update.action")
	@ResponseBody
	public Map updateRule(@RequestBody StatusRule rule, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			ruleService.edit(rule, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/rules/delete.action")
	@ResponseBody
	public Map deleteStatus(@RequestBody StatusRule rule,
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			ruleService.delete(rule, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}


}
