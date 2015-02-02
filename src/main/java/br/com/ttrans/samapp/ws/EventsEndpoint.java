package br.com.ttrans.samapp.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.model.Event;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.xpath.XPath;

@Endpoint
@SuppressWarnings("deprecation")
public class EventsEndpoint {
	
	private static final String NAMESPACE_URI = "http://201.64.138.34/eventsService";
	
	private XPath equipmentID;
	private XPath equipmentModel;
	private XPath equipmentSite;
	private XPath equipmentState;
	private XPath eventCode;
	private XPath eventDate;
	private XPath eventTime;
	
	private String eventDatetime;
	
	private EventService eventService;
	
	@Autowired
	public EventsEndpoint(EventService eventService)
		throws JDOMException {
	  this.eventService = eventService;
	  
	  Namespace namespace = Namespace.getNamespace("even", NAMESPACE_URI);
	  
	  equipmentID = XPath.newInstance("//even:equipment_id");
	  equipmentID.addNamespace(namespace);
	  
	  equipmentModel = XPath.newInstance("//even:equipment_model");
	  equipmentModel.addNamespace(namespace);
	  
	  equipmentSite = XPath.newInstance("//even:equipment_site");
	  equipmentSite.addNamespace(namespace);
	  
	  equipmentState = XPath.newInstance("//even:equipment_state");
	  equipmentState.addNamespace(namespace);
	  
	  eventCode = XPath.newInstance("//even:event_code");
	  eventCode.addNamespace(namespace);
	  
	  eventDate = XPath.newInstance("//even:date");
	  eventDate.addNamespace(namespace);
	  
	  eventTime = XPath.newInstance("//even:time");
	  eventTime.addNamespace(namespace);
	  		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "EventRequest")
	public void handleEventRequest(@RequestPayload Element eventRequest)
		throws Exception {
		
		Event event = new Event();
		
		String eve_equipment_id = equipmentID.valueOf(eventRequest);		
		String eve_model = equipmentModel.valueOf(eventRequest);
		String eve_site = equipmentSite.valueOf(eventRequest);
		String eve_oper_state_id = equipmentState.valueOf(eventRequest);
		String eve_alarm_id = eventCode.valueOf(eventRequest);
		
		eventDatetime = eventDate.valueOf(eventRequest).toString() + " " + eventTime.valueOf(eventRequest).toString();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date eve_datetime = dateFormat.parse(eventDatetime);
		
		String usr_insert = "SAM_WS";

		event.setEve_equipment_id(eve_equipment_id);
		event.setEve_model(eve_model);
		event.setEve_site(eve_site);
		event.setEve_oper_state_id(eve_oper_state_id);
		event.setEve_alarm_id(eve_alarm_id);
		event.setEve_datetime(eve_datetime);
		event.setUsr_insert(usr_insert);
				
		eventService.add(event);
	}
	
}