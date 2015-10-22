package br.com.ttrans.samapp.ws.endpoint;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.system.AliveRequest;
import br.com.ttrans.samapp.ws.system.ConnectionRequest;
import br.com.ttrans.samapp.ws.system.DisconnectionRequest;
import br.com.ttrans.samapp.ws.system.SessionDetail;
import br.com.ttrans.samapp.ws.system.SessionDetailRequest;

@Endpoint
public class SystemServicesEndpoint {
	
	private static final String NAMESPACE_URI = "http://localhost/systemService";
	
	@Autowired
	private Map<String,ConnectionRequest> connections;
	
	@PayloadRoot(localPart = "ConnectionRequest", namespace = NAMESPACE_URI)
	public void handleConnectionRequest(@RequestPayload ConnectionRequest request )
		throws Exception {
		
		String hash = String.valueOf(request.getCreatorId().hashCode()+request.getTimeStamp().hashCode());
		
		final SystemServiceClient systemServiceClient = new SystemServiceClient();
		
		//IP do Sistema que pede conexão
    	TransportContext context = TransportContextHolder.getTransportContext();
    	HttpServletConnection connection = (HttpServletConnection )context.getConnection();
    	HttpServletRequest req = connection.getHttpServletRequest();
    	final String ipAddress = req.getRemoteAddr();
    	
    	System.out.println(ipAddress);
    	
		//Add Connection + HashCode
		connections.put(hash,request);
		
		System.out.println("*************************");
		System.out.println("** Nova conexão criada **");
		System.out.println("** Id: " + hash);
		System.out.println("** creatorId: " + connections.get(hash).getCreatorId());
		System.out.println("** timeStamp: " + connections.get(hash).getTimeStamp());
		System.out.println("*************************");
				
		final SessionDetail session = new SessionDetail(connections.get(hash).getCreatorId(), hash, connections.get(hash).getTimeStamp());
		
		Thread call = new Thread(){
			
			public void run(){
				try {
					systemServiceClient.sessionDetails(session, ipAddress);	
				} catch (Exception e) {
					System.out.println("NÃO FOI POSSÍVEL INVOCAR SessionDetails()");
				}	
			}				
		};
		
		call.start();

	}
	
	@PayloadRoot(localPart = "DisconnectionRequest", namespace = NAMESPACE_URI)
	public void handleDisconnectionRequest(@RequestPayload DisconnectionRequest request)
		throws Exception {
		
		connections.remove(request.getSessionInstanceId());
		
		System.out.println("DESCONEXÃO PEDIDA PELO SISTEMA: " + request.getCreatorId());
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AliveRequest")
	public void handleAliveRequest(@RequestPayload AliveRequest request)
		throws Exception {
		
		if (connections.containsKey(request.getSessionInstanceId())) {
		
			if (request.getConnectionStatus() == '1') {
				
				System.out.println("ERRO DE COMUNICAÇÂO COM O SAM: " + request.getCreatorId());
				
			}
		}
	}
	
	@PayloadRoot(localPart = "SessionDetailRequest", namespace = NAMESPACE_URI)		
	public void handleSessionDetailRequest(@RequestPayload SessionDetailRequest request)		
			throws Exception {
		
		
		System.out.println("*************************");
		System.out.println("** Detalhes da Seção **");
		System.out.println("** SessionInstanceId: " + request.getSessionInstanceId());
		System.out.println("** creatorId: " + request.getCreatorId());
		System.out.println("** timeStamp: " + request.getTimeStamp());
		System.out.println("*************************");
		
	}
	
}
