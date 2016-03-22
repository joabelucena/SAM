package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import br.com.ttrans.samapp.ws.bo.system.*;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

@WebService(name = "SystemServices")
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public class SystemServicesImpl implements SystemEndpoint {

	@Resource
	WebServiceContext wsContext;

	private Map<String, Connection> connections;

	private static final String NAMESPACE_URI = "http://samapp.ttrans.com.br/services/SystemServices";

	@WebMethod(exclude = true)
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}

	@WebMethod(operationName = "Connection")
	public void Connection(@WebParam(targetNamespace = NAMESPACE_URI) Connection payload) {
		
		//Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);

		//Generates connection id
		String hash = String.valueOf(payload.getCreatorId().hashCode() + payload.getTimeStamp().hashCode());
		
		//TODO Implementar chamada do client
		final SystemServiceClient systemServiceClient = new SystemServiceClient();

		final String ipAddress = req.getRemoteAddr();

		// Add Connection + HashCode
		connections.put(hash, payload);

		System.out.println("Quantidade de Conexoes Ativas: " + connections.size());
		System.out.println("IP cliente: " + ipAddress);

		System.out.println("*************************");
		System.out.println("** Nova conexão criada **");
		System.out.println("** Id: " + hash);
		System.out.println("** creatorId: " + connections.get(hash).getCreatorId());
		System.out.println("** timeStamp: " + connections.get(hash).getTimeStamp());
		System.out.println("*************************");
	}
	
	
	@WebMethod(operationName = "SessionDetail")
	public void SessionDetail(@WebParam(targetNamespace = NAMESPACE_URI) SessionDetail payload) {
		
		//Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
		
		System.out.println("*************************");
		System.out.println("** Detalhes da Seção **");
		System.out.println("** SessionInstanceId: " + payload.getSessionInstanceId());
		System.out.println("** creatorId: " + payload.getCreatorId());
		System.out.println("** timeStamp: " + payload.getTimeStamp());
		System.out.println("*************************");

		
	}
	
	
	
	
	
}
