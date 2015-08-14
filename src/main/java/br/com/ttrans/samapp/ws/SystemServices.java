package br.com.ttrans.samapp.ws;

import java.util.Map;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;


@Endpoint
public class SystemServices {
	
	private static final String NAMESPACE_URI = "http://localhost/systemService";
	
	public SystemServices(){
		super();
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "Connection")
	public void handleConnectionRequest(@RequestPayload Map<String, Object> connectionRequest)
		throws Exception {
		
		
		
	}

}
