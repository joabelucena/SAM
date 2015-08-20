package br.com.ttrans.samapp.ws.endpoint;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import br.com.ttrans.samapp.ws.payload.ActiveRequest;
import br.com.ttrans.samapp.ws.payload.AliveRequest;
import br.com.ttrans.samapp.ws.payload.ConnectionRequest;
import br.com.ttrans.samapp.ws.payload.DisconnectionRequest;
import br.com.ttrans.samapp.ws.payload.SessionDetailRequest;

@Endpoint
public class SystemServicesEndpoint {
	
	private static final String NAMESPACE_URI = "http://localhost/systemService";
	
	@Autowired
	private Map<Integer,ConnectionRequest> connections;
	
	@PayloadRoot(localPart = "ConnectionRequest", namespace = NAMESPACE_URI)
	public void handleConnectionRequest(@RequestPayload ConnectionRequest request)
		throws Exception {
		
		Integer hash = request.getCreatorId().hashCode()+request.getTimeStamp().hashCode();
		
		//Add Connection + HashCode
		connections.put(hash,request);
		
		System.out.println("teste");
		
	}
	
	@PayloadRoot(localPart = "SessionDetailRequest", namespace = NAMESPACE_URI)
	public void handleSessionDetailRequest(@RequestPayload SessionDetailRequest request)
		throws Exception {
		
	}
	
	@PayloadRoot(localPart = "ActiveRequest", namespace = NAMESPACE_URI)
	public void handleActiveRequest(@RequestPayload ActiveRequest request)
		throws Exception {
		
	}
	
	@PayloadRoot(localPart = "DisconnectionRequest", namespace = NAMESPACE_URI)
	public void handleDisconnectionRequest(@RequestPayload DisconnectionRequest request)
		throws Exception {
		
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AliveRequest")
	public void handleAliveRequest(@RequestPayload AliveRequest request)
		throws Exception {
		
	}
	
}
