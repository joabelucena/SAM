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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
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
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderJob;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.ServiceOrderType;
import br.com.ttrans.samapp.model.SeverityLevel;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.model.Users;
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
	private StatusRuleService soStatusRuleService;

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
		
		result.put("data", soStatusRuleService.loadData());
		
		return result;
	}
	
	@RequestMapping(value = "/newFromEvent", method = RequestMethod.POST)
	public ResponseEntity<Map> newFromEvent(
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
		ServiceOrderStatus sNewSts = soStatusService.findByName(dao.GetMv("SAM_SOSTATUS", "")); 

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
			log.setServiceorder(so);
			logSet.add(log);

			//OS
			so.setEquipment(event.getEquipment());				// Equipamento
			so.setType(soTypeService.findByName(type));			// Tipo de Os (VERIFICAR)
			so.setEvent(event);									// Evento
			so.setStatus(sNewSts);								// Status
			so.setStartForecast(startForecast);					// Previsao de Inicio
			so.setEndForecast(endForecast);						// Previsao de Termino
			so.setStoped("N");									// Equipamento Parado(nao)
			so.setLog(logSet);									// Log Inicial
			so.setPriority(event.getAlarm().getSeverity());		// Severidade
			so.setRemark(obs);									// Observação

			serviceOrderValidator.validate(so, err, "add");
			
			if(!err.hasErrors()){
				
				int id = soService.add(so, authentication);
				result.put("result"	,messageSource.getMessage("response.Ok", null, locale));
				result.put("soId"	, id);
				
				return new ResponseEntity<Map>(result, HttpStatus.OK);
				
			}else{
				
				//Erros nas validacoes de negocio
				result.put("result"	,messageSource.getMessage("response.so.Failure", null, locale)+
						errorMessageHandler.toStringList(err, locale));
				
				return new ResponseEntity<Map>( result, HttpStatus.OK);
			}
			
		//Query Errors
		} catch (QueryException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);

		//Not Found Objects
		} catch (NullPointerException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.so.NullPointer", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);
		
		//Erros genericos
		} catch (GenericJDBCException e){
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);
			
		} 
	}
	
	@RequestMapping(value = "/newFromSo", method = RequestMethod.POST)
	public ResponseEntity<Map> newFromSo(
			@RequestParam(value = "equipId"			, required = true) String equipId,
			@RequestParam(value = "startForecast"	, required = true) @DateTimeFormat(pattern="dd/MM/yyyy - HH:mm") Date startForecast,
			@RequestParam(value = "endForecast"		, required = true) @DateTimeFormat(pattern="dd/MM/yyyy - HH:mm") Date endForecast,
			@RequestParam(value = "type"			, required = true) int type,
			@RequestParam(value = "priorityId"		, required = true) String prioId,
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
		
		//Retorna status inicial cadastrado em parametro		
		ServiceOrderStatus sNewSts = soStatusService.findByName(dao.GetMv("SAM_SOSTATUS", "")); 

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
			log.setServiceorder(so);
			logSet.add(log);
			
			//OS
			so.setEquipment(new Equipment(equipId));	// Equipamento
			so.setType(new ServiceOrderType(type));		// Tipo de Os (VERIFICAR)
			so.setEvent(null);							// Evento
			so.setStatus(sNewSts);						// Status
			so.setStartForecast(startForecast);			// Previsao de Inicio
			so.setEndForecast(endForecast);				// Previsao de Termino
			so.setStoped("N");							// Equipamento Parado(NAO)
			so.setLog(logSet);							// Log Inicial
			so.setPriority(new SeverityLevel(prioId));	// Severidade
			so.setRemark(obs);							// Observação

			serviceOrderValidator.validate(so, err, "add");
			
			if(!err.hasErrors()){
				
				int id = soService.add(so, authentication);
				result.put("result"	,messageSource.getMessage("response.Ok", null, locale));
				result.put("soId"	, id);
				
				return new ResponseEntity<Map>(result, HttpStatus.OK);
				
			}else{
				
				//Erros nas validacoes de negocio
				result.put("result"	,messageSource.getMessage("response.so.Failure", null, locale)+
						errorMessageHandler.toStringList(err, locale));
				
				return new ResponseEntity<Map>( result, HttpStatus.OK);
			}
			
		//Query Errors
		} catch (QueryException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);

		//Not Found Objects
		} catch (NullPointerException e) {
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.so.NullPointer", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);
		
		//Erros genericos
		} catch (GenericJDBCException e){
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);
			
		}catch (UncategorizedSQLException e){
			
			logger.error(e.getMessage());
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			
			return new ResponseEntity<Map>(result , HttpStatus.OK);
			
		}
	}
	
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	public ResponseEntity<Map> changeStatus(
			@RequestParam(value = "soId"	, required = true)	int soId,
			@RequestParam(value = "stsId"	, required = true)	int stsId,
			@RequestParam(value = "stop"	, required = false)	String stop,
			@RequestParam(value = "obs"		, required = true)	String obs,
			Authentication authentication,
			Locale locale,
			HttpServletRequest request) {
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result"	,"");
		
		
		//Retorna usuario logado na secao
		Users user = (Users) request.getSession().getAttribute("loggedUser");

		// Retorna OS
		ServiceOrder so = soService.get(soId);

		Errors err = new BindException(so, "serviceorder");

		// Status Atual
		ServiceOrderStatus curStatus = so.getStatus();
		
		// Status Novo
		ServiceOrderStatus newStatus = soStatusService.get(stsId);

		// Monta Objeto do Log
		ServiceOrderLog log = new ServiceOrderLog(curStatus,				// Status De
													newStatus,				// Status Para
													user.getUsername(),		// Usuario
													new Date(),				// Data/Hore
													obs,					// Observacao
													user.getUsername());	// Usuario inserção (USR_INSERT)
		
		// Atribui ordem de servico no Log
		log.setServiceorder(so);

		// Muda Status da OS
		so.setStatus(newStatus);
		
		//Equipamneto parado
		so.setStoped(stop);

		// Adiciona novo registro no log
		so.getLog().add(log);

		// Percorre os perfis do usuario para verificar se algum possui
		// permissao pra realizar a alteração

		StatusRule rule = new StatusRule();

		rule.setCurstatus(curStatus);
		rule.setNxtstatus(newStatus);
		
		rule.setRole(user.getRole());
		rule.setRemark(!obs.isEmpty() ? "Y" : "N");

		statusRuleValidator.validate(rule, err, "edit");

		if (!err.hasErrors()) {

			try {

				soService.edit(so, authentication);
				result.put("result"	,messageSource.getMessage("response.Ok", null, locale));

			} catch (QueryException e) {

				logger.error(e.getMessage());
				result.put("result"	,messageSource.getMessage("response.Failure", null, locale));
			}
		}else{
			result.put("result"	,messageSource.getMessage("response.Failure", null, locale)+
					errorMessageHandler.toStringList(err, locale));
		}

		return new ResponseEntity<Map>(result , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<Map> get(
			@RequestParam(value = "soId"	, required = true)	int id,
			Authentication authentication,
			Locale locale,
			HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("result"	,"");
		result.put("so"		, 0);
		
		ServiceOrder so = soService.get(id);
		
		try{
			result.put("result"	, messageSource.getMessage("response.Ok", null, locale));
			result.put("so"		, so.getLog());
		}catch(Exception e){
			
			result.put("result"	, messageSource.getMessage("response.Failure", null, locale));
			logger.error(e.getMessage());
			
		}
		return new ResponseEntity<Map>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gettypes")
	public ResponseEntity<Map> getTypes(
			Authentication authentication, Locale locale,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "");
		result.put("type", "");
		
		List<ServiceOrderType> types = soTypeService.loadData();
		/*
		String[][] types = new String[vType.size()][2];

		for (int i = 0; i < types.length; i++) {
			types[i][0] = vType.get(i)[0].toString();
			types[i][1] = vType.get(i)[1].toString();
		}

		*/
		result.put("result",messageSource.getMessage("response.Ok", null, locale));
		result.put("type", types);
		
		return new ResponseEntity<Map>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getallowedstatus")
	public ResponseEntity<Map> getAllowedStatus(
			@RequestParam(value = "soId"	, required = true)	int soId,
			Authentication authentication,
			Locale locale,
			HttpServletRequest request) {
		
		ServiceOrder so = soService.get(soId);
		
		//Retorna usuario logado na secao
		Users user = (Users) request.getSession().getAttribute("loggedUser");
		
		List<ServiceOrderStatus> rulesResult = soStatusRuleService.getAllowedStatus(user.getRole(), so.getStatus());
		
		//Result Map
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("result", "");
		result.put("rule", "");
		
		result.put("result",messageSource.getMessage("response.Ok", null, locale));
		result.put("rule", rulesResult);
		
		return new ResponseEntity<Map>(result, HttpStatus.OK);
	}

	/*
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
	
	/*
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
	
	/*
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
	
	/*
	 * CRUD Operations for: SO Status Rules
	 */
	@RequestMapping("/rules/add.action")
	@ResponseBody
	public Map addRule(@RequestBody Map payload, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		/*Implementar isso no Status Rules*/
		
		StatusRule sorules = new StatusRule();
		
		sorules.setId((int) payload.get("id"));
		
		sorules.setRemark((String) payload.get("remark"));
		
		sorules.setCurstatus((ServiceOrderStatus) payload.get("curstatus"));
		
		sorules.setNxtstatus((ServiceOrderStatus) payload.get("nxtstatus"));
		
		sorules.setRole((Role) payload.get("role"));
				
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soStatusRuleService.add(sorules, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/rules/update.action")
	@ResponseBody
	public Map updateRule(@RequestBody StatusRule sorules, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			soStatusRuleService.edit(sorules, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/rules/delete.action")
	@ResponseBody
	public Map deleteStatus(@RequestBody StatusRule sorules,
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			soStatusRuleService.delete(sorules, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}


}
