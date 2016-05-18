package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.ws.bo.alarm.*;

@WebInfo(name = "AlarmServices",
			description = "Serviço interface Maestro Thales para recebimento de alarmes.",
			url = "/services/Maestro/AlarmServices")
@WebService
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface AlarmEndpoint {

	static final String NAMESPACE_URI = "http://maestro.thalesgroup.com/wsdl/alarm/xsd";
	
	@WebMethod(operationName = "alarmAllCurrent") 	public void AlarmAllCurrent(@WebParam(targetNamespace = NAMESPACE_URI) AlarmAllCurrent payload);
	
	@WebMethod(operationName = "alarmAdd")			public void AlarmAdd(@WebParam(targetNamespace = NAMESPACE_URI) AlarmAdd payload);
	
	@WebMethod(operationName = "alarmUpdateState") 	public void AlarmUpdateState(@WebParam(targetNamespace = NAMESPACE_URI) AlarmUpdateState payload);
	
	@WebMethod(operationName = "alarmDelete") 		public void AlarmDelete(@WebParam(targetNamespace = NAMESPACE_URI) AlarmDelete payload);

}
