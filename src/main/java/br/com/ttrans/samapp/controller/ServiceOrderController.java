package br.com.ttrans.samapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.hibernate.QueryException;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.service.RoleService;
import br.com.ttrans.samapp.service.ServiceOrderService;
import br.com.ttrans.samapp.service.ServiceOrderStatusService;
import br.com.ttrans.samapp.service.ServiceOrderTypeService;
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
	private EquipmentService equipmentService;

	@Autowired
	private StatusRuleValidator statusRuleValidator;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(ServiceOrderController.class);
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", soService.loadData());
		
		return result;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<String> newSo(
			@RequestParam(value = "eveId"			, required = true) long eveId,
			@RequestParam(value = "startForecast"	, required = true) String startForecast,
			@RequestParam(value = "endForecast"		, required = true) String endForecast,
			@RequestParam(value = "type"			, required = true) String type,
			@RequestParam(value = "obs"				, required = true) String obs,
			Authentication authentication,
			Locale locale) {

		//Retorna evento
		Event event = eventService.get(eveId);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
		
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
			ServiceOrder so = new ServiceOrder();

			log.setServiceorder(so);
			logSet.add(log);

			so.setEquipment(event.getEquipment());					// Equipamento
			so.setType(soTypeService.findByName(type));				// Tipo de Os (VERIFICAR)
			so.setEvent(event);										// Evento
			so.setStatus(sNewSts);									// Status
			so.setSor_start_forecast(formato.parse(startForecast));	// Previsao de Inicio
			so.setSor_end_forecast(formato.parse(endForecast));		// Previsao de Termino
			so.setLog(logSet);										// Log Inicial
			so.setPriority(event.getAlarm().getSeverity());			// Severidade
			so.setSor_remarks(obs);									// Observação

			soService.add(so, authentication);

			return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale)
											, HttpStatus.OK);
			
			
		
		// Date Format
		}catch (ParseException e){
			logger.error(e.getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("response.so.ParseException", null, locale)
											, HttpStatus.OK);
		
		//Query Errors
		} catch (QueryException e) {
			
			logger.error(e.getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
					, HttpStatus.OK);

		//Not Found Objects
		} catch (NullPointerException e) {
			
			logger.error(e.getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
					, HttpStatus.OK);
		
		//Erros genericos
		} catch (GenericJDBCException e){
			
			logger.error(e.getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
					, HttpStatus.OK);
			
		} catch(Exception e){
			
			logger.error(e.getMessage());
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
					, HttpStatus.OK);
			
		}

	}

	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	public ResponseEntity<String> changeStatus(
			@RequestParam(value = "soId"	, required = true)	int soId,
			@RequestParam(value = "stsId"	, required = true)	int stsId,
			@RequestParam(value = "stop"	, required = false)	String stop,
			@RequestParam(value = "obs"		, required = true)	String obs,
			Authentication authentication,
			Locale locale) {

		// Retorna OS
		ServiceOrder so = soService.get(soId);

		Errors result = new BindException(so, "serviceorder");

		// Status Atual
		ServiceOrderStatus curStatus = so.getStatus();

		// Status Novo
		ServiceOrderStatus newStatus = soStatusService.get(stsId);

		// Monta Objeto do Log
		ServiceOrderLog log = new ServiceOrderLog(so.getStatus(),				// Status De
													newStatus,					// Status Para
													authentication.getName(),	// Usuario
													new Date(),					// Data/Hore
													obs,						// Observacao
													authentication.getName());	// Usuario inserção (USR_INSERT)
		
		// Atribui ordem de servico no Log
		log.setServiceorder(so);

		// Muda Status da OS
		so.setStatus(newStatus);

		// Adiciona novo registro no log
		so.getLog().add(log);

		List aut = (List) authentication.getAuthorities();

		// Percorre os perfis do usuario para verificar se algum possui
		// permissao pra realizar a alteração
		for (int i = 0; i < aut.size(); i++) {

			StatusRule rule = new StatusRule();

			rule.setCurstatus(curStatus);
			rule.setNxtstatus(newStatus);
			rule.setRole(roleService.findByDesc(aut.get(i).toString()));
			rule.setSru_log_remark(!obs.isEmpty() ? "S" : "N");

			statusRuleValidator.validate(rule, result, "edit");

			if (!result.hasErrors()) {

				try {

					soService.edit(so,authentication);
					return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale)
							, HttpStatus.OK);

				} catch (QueryException e) {

					logger.error(e.getMessage());
					return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
							, HttpStatus.OK);
				}
			}
		}

		return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale)
				, HttpStatus.OK);
	}
}
