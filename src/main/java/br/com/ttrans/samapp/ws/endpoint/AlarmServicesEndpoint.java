package br.com.ttrans.samapp.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.ws.alarm.AlarmAddRequest;
import br.com.ttrans.samapp.ws.alarm.AlarmAllCurrentRequest;
import br.com.ttrans.samapp.ws.alarm.AlarmDeleteRequest;
import br.com.ttrans.samapp.ws.alarm.AlarmUpdateStateRequest;

@Endpoint
public class AlarmServicesEndpoint {
	
	private static final String USR_WS_MAESTRO = "SAM_MAESTRO_WS";
	
	private static final String NAMESPACE_URI = "http://localhost/";
	
	@Autowired
	private EventService eventService;
	
	@PayloadRoot(localPart = "AlarmAddRequest", namespace = NAMESPACE_URI)
	public void handleAlarmAddRequest(@RequestPayload AlarmAddRequest request )
		throws Exception {
		
		Event event = new Event();
		
		event.setEquipment(new Equipment(request.getObjectId()));
		event.setModel("");
		event.setSite("");
		event.setAlarm(new Alarm(request.getAlarmInstanceId()));
		event.setDatetime(request.getApparitionDate());
		event.setInsert(USR_WS_MAESTRO);
		
		eventService.add(event);
		
	}
	
	@PayloadRoot(localPart = "AlarmAllCurrentRequest", namespace = NAMESPACE_URI)
	public void handleAlarmAllCurrentRequest(@RequestPayload AlarmAllCurrentRequest request )
		throws Exception {
		
		System.out.println("*** AlarmAllCurrent ***");
		
	}
	
	@PayloadRoot(localPart = "AlarmUpdateStateRequest", namespace = NAMESPACE_URI)
	public void handleAlarmUpdateStateRequest(@RequestPayload AlarmUpdateStateRequest request )
		throws Exception {
		
		System.out.println("*** AlarmUpdateState ***");
		
	}
	
	@PayloadRoot(localPart = "AlarmDeleteRequest", namespace = NAMESPACE_URI)
	public void handleAlarmDeleteRequest(@RequestPayload AlarmDeleteRequest request )
		throws Exception {
		
		System.out.println("*** AlarmDelete ***");
		
	}

}
