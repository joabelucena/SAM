package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.ws.bo.system.*;

@WebInfo(name = "SystemServices",
			description = "Este serviço provê um amigável Hello para quem o executa kkk oh la, não ta dando assim",
			url = "/services/Maestro/SystemServices")
@WebService
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface SystemEndpoint {
	static final String NAMESPACE_URI = "http://samapp.ttrans.com.br/services/SystemServices/xsd";
	
	@WebMethod(operationName = "Connection") public void Connection(@WebParam(targetNamespace = NAMESPACE_URI) Connection payload);
	
	@WebMethod(operationName = "SessionDetail") public void SessionDetail(@WebParam(targetNamespace = NAMESPACE_URI) SessionDetail payload);
	
	@WebMethod(operationName = "Alive") public void Alive(@WebParam(targetNamespace = NAMESPACE_URI) Alive payload);
	
	@WebMethod(operationName = "Active") public void Active(@WebParam(targetNamespace = NAMESPACE_URI) Active payload);
	
	@WebMethod(operationName = "Disconnection") public void Disconnection(@WebParam(targetNamespace = NAMESPACE_URI) Disconnection payload);	

}
