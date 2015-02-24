package br.com.ttrans.samapp.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.service.ServiceOrderService;
import br.com.ttrans.samapp.service.ServiceOrderStatusService;
import br.com.ttrans.samapp.service.ServiceOrderTypeService;
import br.com.ttrans.samapp.validator.impl.StatusRuleValidator;

@Controller
@RequestMapping("/so")
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
	
	@RequestMapping(value = "/new/{eveId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void newSo(
			@PathVariable("eveId") int eveId
			,Authentication authentication) {
		
		String obs = "Aguardar a compra do componente xpto";
		
		Event event = eventService.getEvent(eveId);
		
		ServiceOrderLog log = new ServiceOrderLog(soStatusService.findByName("NOVA")
												, soStatusService.findByName("NOVA")
												, authentication.getName()
												, new Date()
												, obs
												, authentication.getName());
		
		Set<ServiceOrderLog> logSet = new HashSet<ServiceOrderLog>();
		
		if(!event.equals(null)){
			
			ServiceOrder so = new ServiceOrder();
			
			log.setServiceorder(so);
			logSet.add(log);
			
			so.setEquipment(event.getEquipment());				// Equipamento
			so.setType(soTypeService.findByName("CORRETIVA"));	// Tipo de Os (VERIFICAR)
			so.setEvent(event);									// Evento
			so.setStatus(soStatusService.findByName("NOVA"));	// Status
			so.setSor_start_forecast(new Date ());				// Previsao de Inicio (VERIFICAR)
			so.setSor_end_forecast(new Date ());				// Previsao de Termino (VERIFICAR)
			so.setLog(logSet);									// Log Inicial
			so.setUsr_insert(authentication.getName());			// Usuario Inserção
			so.setPriority(event.getAlarm().getSeverity());		// Severidade
			so.setSor_remarks(obs);								// Observação
			so.setSor_equipment_stop("S");						// Equipamento parado
			
			soService.add(so);
			
		}
	}
	
	@RequestMapping(value = "/changeStatus/{eveId}/{oldSts}/{newSts}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void changeStatus(
			@PathVariable("eveId") int eveId
			,@PathVariable("oldSts") int oldSts
			,@PathVariable("newSts") int newSts
			,Authentication authentication) {
		
		String obs = "Aguardar a compra do componente xpto";
		
		ServiceOrder so = soService.get(eveId);
		
		ServiceOrderLog log = new ServiceOrderLog(soStatusService.findByName("NOVA")
												, soStatusService.findByName("NOVA")
												, authentication.getName()
												, new Date()
												, obs
												, authentication.getName());
		
		Set<ServiceOrderLog> logSet = new HashSet<ServiceOrderLog>();
		
		if(true){			
			soService.add(so);			
		}
	}
	
}