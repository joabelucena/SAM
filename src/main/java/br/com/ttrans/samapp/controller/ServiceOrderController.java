package br.com.ttrans.samapp.controller;

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
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.service.ServiceOrderService;

@Controller
@RequestMapping("/so")
public class ServiceOrderController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value = "/new/{eveId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void doActions(
			@PathVariable("eveId") int eveId
			,Authentication authentication) {
		
		String obs = "Aguardar a compra do componente xpto";
		
		Event event = eventService.getEvent(eveId);
		
		if(!event.equals(null)){
			
			ServiceOrder so = new ServiceOrder();
			
			so.setEquipment(event.getEquipment());
			
		}
		
		
		
	}

}
