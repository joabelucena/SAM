package br.com.ttrans.samapp.ws.endpoint;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.ws.bo.Connection;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;

@WebInfo(name="SystemServices"
,description="Este serviço provê um amigável Hello para quem o executa kkk oh la, não ta dando assim"		
,url="/services/Maestro/SystemServices")
@WebService
@SOAPBinding(style = Style.RPC)
public class SystemEndpoint {
	
	
	@Resource
	WebServiceContext wsContext;
	
	
	private Map<String, Connection> connections;
		
	@WebMethod(exclude=true)
	public void setLala(Map<String, Connection> connections){
		this.connections = connections;
	}
	
	@WebMethod(operationName="Connection")
	public void Connection(Connection payload) {
		
		
		
		String hash = String.valueOf(payload.getCreatorId().hashCode()+payload.getTimeStamp().hashCode());
		
		final SystemServiceClient systemServiceClient = new SystemServiceClient();
		
		//IP do Sistema que pede conexão
		MessageContext mc = wsContext.getMessageContext();
	    HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
    	final String ipAddress = req.getRemoteAddr();
    	
    	System.out.println(ipAddress);
    	
		//Add Connection + HashCode
		connections.put(hash,payload);
		
		System.out.println("*************************");
		System.out.println("** Nova conexão criada **");
		System.out.println("** Id: " + hash);
		System.out.println("** creatorId: " + connections.get(hash).getCreatorId());
		System.out.println("** timeStamp: " + connections.get(hash).getTimeStamp());
		System.out.println("*************************");
	}
 

}
