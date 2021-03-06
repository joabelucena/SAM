package br.com.ttrans.samapp.ws.cli;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.SessionDetail;

@WebService
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface SystemServiceClientEndpoint {
	
	static final String NAMESPACE_URI = "http://maestro.thalesgroup.com/wsdl/system/xsd";
	
	@Oneway @WebMethod(operationName = "sessionDetail", action="sessionDetail") public void sessionDetail(@WebParam(targetNamespace = NAMESPACE_URI, name = "SessionDetail") SessionDetail payload);
	
	@Oneway @WebMethod(operationName = "disconnection", action="disconnection") public void disconnection(@WebParam(targetNamespace = NAMESPACE_URI, name = "Disconnection") Disconnection payload);
	
}
