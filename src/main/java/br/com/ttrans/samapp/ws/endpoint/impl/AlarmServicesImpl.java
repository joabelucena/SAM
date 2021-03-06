package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmAdd;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmAllCurrent;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmDelete;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmDetail;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmUpdateState;
import br.com.ttrans.samapp.ws.bo.system.Session;
import br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint;

@WebService(serviceName="AlarmServices",
portName="AlarmPort",
targetNamespace="http://maestro.thalesgroup.com/wsdl/system/xsd",
endpointInterface="br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint")
@Component
public class AlarmServicesImpl implements AlarmEndpoint {

	@Resource
	private WebServiceContext wsContext;
	
	@Autowired
	private EventService service;
	
	private Map<String, Session> sessions;
	
	private Map<String, String> systems;
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmServicesImpl.class);
	
	private static final String USR_MAESTRO = "SAM_MAESTRO";
	
	@WebMethod(exclude = true)
	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}
	
	@WebMethod(exclude = true)
	public void setSystems(Map<String, String> systems) {
		this.systems = systems;
	}
	
	@Override
	public void AlarmAllCurrent(AlarmAllCurrent payload) {
		
		logger.debug(payload.toString());
		
		List<AlarmDetail> alarms = payload.getAlarmList();
		
		for (AlarmDetail alarmDetail : alarms) {
			this.AlarmAdd(new AlarmAdd(alarmDetail));
		}
		
	}

	@Override
	public void AlarmAdd(AlarmAdd payload) {
		
		logger.debug(payload.toString());
		
		String alarmId = systems.containsKey(payload.getCreatorId()) ? systems.get(payload.getCreatorId()).concat(payload.getTextMessageId())
																	: payload.getTextMessageId();
		
		if(sessions.containsKey(payload.getSessionInstanceId())){
			
			/**
			 * Confirmar atributos de identificação de equipamento e alarme
			 */
			Event e = new Event(
					payload.getAlarmInstanceId(),
					new Equipment(payload.getObjectId()),
					new Alarm(alarmId),
					new Date(), 
					USR_MAESTRO);
			
			try {
				service.add(e);
			} catch (Exception e2) {
				logger.error("Erro: " + e2.getMessage());
			}
			
		}else{
			logger.debug("Session " + payload.getSessionInstanceId() + " is not active.");
			logger.debug("Refused alarm:" + payload);
		}
		
	}

	@Override
	public void AlarmUpdateState(AlarmUpdateState payload) {
		
		logger.debug(payload.toString());
		
		if(sessions.containsKey(payload.getSessionInstanceId())){
			service.recognize(payload.getAlarmInstanceId(), USR_MAESTRO);
		}else{
			logger.debug("Session " + payload.getSessionInstanceId() + " is not active.");
		}
		
	}

	@Override
	public void AlarmDelete(AlarmDelete payload) {
		
		logger.debug(payload.toString());

		if (sessions.containsKey(payload.getSessionInstanceId())) {

			service.normalize(payload.getAlarmInstanceId(), USR_MAESTRO);
		} else {
			logger.debug("Session " + payload.getSessionInstanceId() + " is not active.");
		}

	}

}
