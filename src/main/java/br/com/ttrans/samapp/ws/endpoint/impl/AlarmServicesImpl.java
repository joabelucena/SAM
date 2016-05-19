package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmAdd;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmAllCurrent;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmDelete;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmUpdateState;
import br.com.ttrans.samapp.ws.bo.system.Connection;
import br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint;

@WebService(serviceName="AlarmServices",
portName="AlarmPort",
endpointInterface="br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint")
public class AlarmServicesImpl implements AlarmEndpoint {

	@Resource
	private WebServiceContext wsContext;
	
	@Autowired
	private EventService service;
	
	private Map<String, Connection> connections;
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmServicesImpl.class);
	
	private static final String USR_MAESTRO = "SAM_MAESTRO";
	
	@WebMethod(exclude = true)
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}
	
	@Override
	public void AlarmAllCurrent(AlarmAllCurrent payload) {
		// TODO Auto-generated method stub
		logger.info("AlarmAllCurrent");
		
	}

	@Override
	public void AlarmAdd(AlarmAdd payload) {
		
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if(connections.containsKey(payload.getSessionInstanceId())){
			
			/*
			 * Confirmar atributos de identificação de equipamento e alarme
			 */
			Event e = new Event(
					payload.getAlarmInstanceId(),
					new Equipment(payload.getObjectId()), 
					new Alarm(payload.getTextMessageId()), 
					new Date(), 
					USR_MAESTRO);
			
			service.add(e);
			
		}else{
			logger.info(name + " - " + "SessionID: " + payload.getSessionInstanceId() + " is not currently active.");
		}
		
	}

	@Override
	public void AlarmUpdateState(AlarmUpdateState payload) {
		
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if(connections.containsKey(payload.getSessionInstanceId())){
			
			System.out.println("TESTE");
			service.recognize(payload.getAlarmInstanceId(), USR_MAESTRO);
		}else{
			logger.info(name + " - " + "SessionID: " + payload.getSessionInstanceId() + " is not currently active.");
		}
		
	}

	@Override
	public void AlarmDelete(AlarmDelete payload) {
		System.out.println("CHEGOU");
//		String name = new Object(){}.getClass().getEnclosingMethod().getName();
//		
//		System.out.println("CHEGOU");
//
//		if(connections.containsKey(payload.getSessionInstanceId())){
//			
//			System.out.println("TESTE");
//			service.normalize(payload.getAlarmInstanceId(), USR_MAESTRO);
//		}else{
//			logger.info(name + " - " + "SessionID: " + payload.getSessionInstanceId() + " is not currently active.");
//		}
		
	}

}
