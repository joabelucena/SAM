package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.service.AlarmService;
import br.com.ttrans.samapp.ws.bo.HelloWorldBo;

@WebInfo(name="HelloService"
		,description="Este serviço provê um amigável Hello para quem o executa kkk oh la, não ta dando assim"		
		,url="/services/hello")
@WebService
@Component
public class HelloWorldWS{
	
	@Autowired
	private AlarmService service;
	
	//DI via Spring
	private HelloWorldBo helloWorldBo;

	@WebMethod(exclude=true)
	public void setHelloWorldBo(HelloWorldBo helloWorldBo) {
		this.helloWorldBo = helloWorldBo;
	}
	
//	@WebMethod(exclude=true)
//	public void setService(AlarmService service) {
//		this.service = service;
//	}
	
	@WebMethod(operationName="getHelloWorld")
	public String getHelloWorld() {
		
		return helloWorldBo.getHelloWorld();
		
	}
	
	
	@WebMethod(operationName="getAlarm")
	public Alarm getAlarm(@WebParam(name="alarmId") String id) {
//		List<Alarm> alarms = service.loadData();
//		
//		System.out.println(alarms.get(0).getDesc());
//		
//		System.out.println("Passou =)");
		
		Alarm alarm = service.get(id);
		
		return alarm;
		
	}
 
}