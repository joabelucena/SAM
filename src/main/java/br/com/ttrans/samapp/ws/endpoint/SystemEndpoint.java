package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.ws.bo.system.*;

@WebInfo(name = "SystemServices",
			description = "Serviço interface Maestro Thales para gerenciamento de sessão.",
			url = "/services/Maestro/SystemServices")
@WebService
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface SystemEndpoint {
	static final String NAMESPACE_URI = "http://maestro.thalesgroup.com/wsdl/system/xsd";
	
	@WebMethod(operationName = "connection") public void Connection(@WebParam(targetNamespace = NAMESPACE_URI) Connection payload);
	
	@WebMethod(operationName = "sessionDetail") public void SessionDetail(@WebParam(targetNamespace = NAMESPACE_URI) SessionDetail payload);
	
	@WebMethod(operationName = "alive") public void Alive(@WebParam(targetNamespace = NAMESPACE_URI) Alive payload);
	
	@WebMethod(operationName = "active") public void Active(@WebParam(targetNamespace = NAMESPACE_URI) Active payload);
	
	@WebMethod(operationName = "disconnection") public void Disconnection(@WebParam(targetNamespace = NAMESPACE_URI) Disconnection payload);	

}
