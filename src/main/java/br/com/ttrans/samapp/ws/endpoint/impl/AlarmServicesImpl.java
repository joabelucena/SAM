package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
	
	private static final String USR_NORM_INSERT = "";
	
	@WebMethod(exclude = true)
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}
	
	@Override
	public void AlarmAllCurrent(AlarmAllCurrent payload) {
		// TODO Auto-generated method stub
		System.out.println("AlarmAllCurrent");
		
	}

	@Override
	public void AlarmAdd(AlarmAdd payload) {
		// TODO Auto-generated method stub
		System.out.println("AlarmAdd");

		logger.info("Quantidade de Conexoes Ativas: " + connections.size());
	}

	@Override
	public void AlarmUpdateState(AlarmUpdateState payload) {
		
		if(connections.containsKey(payload.getSessionInstanceId())){
			logger.info("Sessão ativa");
		}else{
			logger.info("SessionID: " + payload.getSessionInstanceId() + " is not currently active.");
		}
		
	}

	@Override
	public void AlarmDelete(AlarmDelete payload) {
		// TODO Auto-generated method stub
		System.out.println("AlarmDelete");
	}

}
