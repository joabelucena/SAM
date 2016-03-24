package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ttrans.samapp.ws.bo.alarm.AlarmAdd;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmAllCurrent;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmDelete;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmDetail;
import br.com.ttrans.samapp.ws.bo.alarm.AlarmUpdateState;
import br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint;

@WebService(serviceName="AlarmServices",
portName="AlarmPort",
endpointInterface="br.com.ttrans.samapp.ws.endpoint.AlarmEndpoint")
public class AlarmServicesImpl implements AlarmEndpoint {

	@Resource
	WebServiceContext wsContext;
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmServicesImpl.class);	
	
	@Override
	public void AlarmAllCurrent(AlarmAllCurrent payload) {
		// TODO Auto-generated method stub
		List<AlarmDetail> alarms = payload.getAlarmList();
		

	}

	@Override
	public void AlarmAdd(AlarmAdd payload) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AlarmUpdateState(AlarmUpdateState payload) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AlarmDelete(AlarmDelete payload) {
		// TODO Auto-generated method stub

	}

}
