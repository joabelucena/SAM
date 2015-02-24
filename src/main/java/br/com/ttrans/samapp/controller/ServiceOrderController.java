package br.com.ttrans.samapp.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@RequestMapping(value = "/changeStatus/{soId}/{stsId}", method = RequestMethod.POST)
	public ResponseEntity<String> changeStatus(
			@PathVariable("soId") int soId
			,@PathVariable("stsId") int stsId
			,Authentication authentication) {
		
		String obs = "Aguardar a compra do componente xpto";
		
		//Retorna OS
		ServiceOrder so = soService.get(soId);
		
		Errors result = new BindException(so, "serviceorder");
		
		//Status Atual
		ServiceOrderStatus curStatus = so.getStatus();
		
		//Status Novo
		ServiceOrderStatus newStatus = soStatusService.get(stsId);	
		
		
		//Monta Objeto do Log
		ServiceOrderLog log = new ServiceOrderLog(so.getStatus()
												, newStatus
												, authentication.getName()
												, new Date()
												, obs
												, authentication.getName());
		//Atribui ordem de servico no Log
		log.setServiceorder(so);
		
		//Muda Status da OS
		so.setStatus(newStatus);
		
		//Adiciona novo registro no log
		so.getLog().add(log);
		
		
		List aut = (List) authentication.getAuthorities();

		//Percorre os perfis do usuario para verificar se algum possui permissao pra realizar a alteração
		for(int i = 0; i < aut.size() ;i++){
			
			StatusRule rule = new StatusRule();
			
			rule.setCurstatus(curStatus);
			rule.setNxtstatus(newStatus);
			rule.setRole(roleService.findByDesc(aut.get(i).toString()));
			rule.setSru_log_remark(!obs.isEmpty() ? "S" : "N");
			
			statusRuleValidator.validate(rule, result, "edit");
			
			if(!result.hasErrors()){			
				soService.edit(so);
				return new ResponseEntity<String>(HttpStatus.OK);
			}			
		}
		
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}