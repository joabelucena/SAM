package br.com.ttrans.samapp.ws.endpoint;

import org.jdom2.Element;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import br.com.ttrans.samapp.ws.payload.ConnectionRequest;



@Endpoint
public class SystemServicesEndpoint {
	
	private static final String NAMESPACE_URI = "http://localhost/systemService";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ConnectionRequest")
	public void handleConnectionRequest(@RequestPayload ConnectionRequest conn)
		throws Exception {
		
		System.out.println("teste");
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SessionDetailRequest")
	public void handleSessionDetailRequest(@RequestPayload Element el)
		throws Exception {
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActiveRequest")
	public void handleActiveRequest(@RequestPayload Element el)
		throws Exception {
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "DisconnectionRequest")
	public void handleDisconnectionRequest(@RequestPayload Element el)
		throws Exception {
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AliveRequest")
	public void handleAliveRequest(@RequestPayload Element el)
		throws Exception {
		
	}
	
}
