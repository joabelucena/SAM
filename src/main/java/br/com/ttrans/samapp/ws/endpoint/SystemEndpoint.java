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
	
	public static final String NAMESPACE_URI = "http://maestro.thalesgroup.com/wsdl/system/xsd";
	
	public static final String SAM_CREATOR_ID = "2";
	
	@Oneway @WebMethod(operationName = "Connection"		, action="Connection") public void Connection(@WebParam(targetNamespace = NAMESPACE_URI) Connection payload);
	
	@Oneway @WebMethod(operationName = "SessionDetail"	, action="SessionDetail") public void SessionDetail(@WebParam(targetNamespace = NAMESPACE_URI) SessionDetail payload);
	
	@Oneway @WebMethod(operationName = "Alive"			, action="Alive") public void Alive(@WebParam(targetNamespace = NAMESPACE_URI) Alive payload);
	
	@Oneway @WebMethod(operationName = "Active"			, action="Active") public void Active(@WebParam(targetNamespace = NAMESPACE_URI) Active payload);
	
	@Oneway @WebMethod(operationName = "Disconnection"	, action="Disconnection") public void Disconnection(@WebParam(targetNamespace = NAMESPACE_URI) Disconnection payload);
	
}
