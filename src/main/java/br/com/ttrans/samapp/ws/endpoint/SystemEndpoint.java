package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.Oneway;
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
	
	@Oneway @WebMethod(operationName = "Connection") public void Connection(@WebParam(targetNamespace = NAMESPACE_URI) Connection payload);
	
	@Oneway @WebMethod(operationName = "SessionDetail") public void SessionDetail(@WebParam(targetNamespace = NAMESPACE_URI) SessionDetail payload);
	
	@Oneway @WebMethod(operationName = "Alive") public void Alive(@WebParam(targetNamespace = NAMESPACE_URI) Alive payload);
	
	@Oneway @WebMethod(operationName = "Active") public void Active(@WebParam(targetNamespace = NAMESPACE_URI) Active payload);
	
	@Oneway @WebMethod(operationName = "Disconnection") public void Disconnection(@WebParam(targetNamespace = NAMESPACE_URI) Disconnection payload);	

}
