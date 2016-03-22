package br.com.ttrans.samapp.ws.endpoint.impl;


import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import br.com.ttrans.samapp.ws.bo.system.*;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

@WebService(endpointInterface="br.com.ttrans.samapp.ws.endpoint.SystemEndpoint")
public class SystemServicesImpl implements SystemEndpoint {

	@Resource
	WebServiceContext wsContext;

	private Map<String, Connection> connections;

	private static final String NAMESPACE_URI = "http://samapp.ttrans.com.br/services/SystemServices";

	@WebMethod(exclude = true)
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}

	
	public void Connection(Connection payload) {
		
		//Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);

		//Generates connection id
		String hash = String.valueOf(payload.getCreatorId().hashCode() + payload.getTimeStamp().hashCode());
		
		//TODO Implementar chamada do client
//		final SystemServiceClient systemServiceClient = new SystemServiceClient();

		final String ipAddress = req.getRemoteAddr();
		
		final SessionDetail session = new SessionDetail();
		
		session.setCreatorId(payload.getCreatorId());
		session.setSessionInstanceId(hash);
		session.setTimeStamp(null);

		// Add Connection + HashCode
		connections.put(hash, payload);
		
		Thread call = new Thread(){
			
			public void run(){
				try {
					SystemServiceClient.SessionDetail("http://10.42.0.1:8080/SAM/services/Maestro/SystemServices?wsdl", session);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("NÃO FOI POSSÍVEL INVOCAR SessionDetails()");
				}	
			}				
		};
		
		call.start();
		
		
		

		System.out.println("Quantidade de Conexoes Ativas: " + connections.size());
		System.out.println("IP cliente: " + ipAddress);

		System.out.println("*************************");
		System.out.println("** Nova conexão criada **");
		System.out.println("** Id: " + hash);
		System.out.println("** creatorId: " + connections.get(hash).getCreatorId());
		System.out.println("** timeStamp: " + connections.get(hash).getTimeStamp());
		System.out.println("*************************");
	}
	
	
//	@WebMethod(operationName = "SessionDetail")
	public void SessionDetail(SessionDetail payload) {
		
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
