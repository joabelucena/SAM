package br.com.ttrans.samapp.ws.cli;

import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.ws.client.core.WebServiceTemplate;

import br.com.ttrans.samapp.ws.system.SessionDetail;

public class SystemServiceClient {

	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	
	public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }
	
    public void sessionDetails(SessionDetail session, String ipAddress) {

    	String envelope = getSessionDetailEnvelope();
    	String url = "http://" + ipAddress + ":8080/samappjs/systemService";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println( sdf.format(cal.getTime()).toString() );
        	    	
    	envelope = envelope.replace(":CREATOR_ID"	, session.getCreatorId());
    	envelope = envelope.replace(":INSTANCE_ID"	, session.getSessionInstanceId());
    	envelope = envelope.replace(":TIME_STAMP"	, sdf.format(cal.getTime()).toString());
    	    	
        StreamSource source = new StreamSource(new StringReader(envelope));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(url, source, result);
    }
    
    private String getSessionDetailEnvelope(){
    	
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append("<sys:SessionDetailRequest xmlns:sys='http://localhost/systemService'>");

    	builder.append("<sys:creatorId>:CREATOR_ID</sys:creatorId>");
		builder.append("<sys:sessionInstanceId>:INSTANCE_ID</sys:sessionInstanceId>");
		builder.append("<sys:timeStamp>:TIME_STAMP</sys:timeStamp>");
		builder.append("</sys:SessionDetailRequest>");
		
		return builder.toString();		
    	
    }
    
}
