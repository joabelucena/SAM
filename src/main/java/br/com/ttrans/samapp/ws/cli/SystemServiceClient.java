package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.ttrans.samapp.ws.bo.system.SessionDetail;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

public class SystemServiceClient {
	
	private static final String URI = "http://impl.endpoint.ws.samapp.ttrans.com.br/";

	public static void SessionDetail(String URL, SessionDetail session) {

		URL url;
		
		try {
			url = new URL(URL);

			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName(URI, "SystemServicesImplService");

			Service service = Service.create(url, qname);

			SystemEndpoint system = service.getPort(SystemEndpoint.class);
			
			system.SessionDetail(session);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// private final WebServiceTemplate webServiceTemplate = new
	// WebServiceTemplate();
	//
	// public void setDefaultUri(String defaultUri) {
	// webServiceTemplate.setDefaultUri(defaultUri);
	// }
	//
	// public void sessionDetails(SessionDetail session, String ipAddress) {
	//
	// String envelope = getSessionDetailEnvelope();
	// String url = "http://" + ipAddress + ":8080/SAM/systemService";
	//
	// Calendar cal = Calendar.getInstance();
	// SimpleDateFormat sdf = new
	// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	// System.out.println( sdf.format(cal.getTime()).toString() );
	//
	// envelope = envelope.replace(":CREATOR_ID" , session.getCreatorId());
	// envelope = envelope.replace(":INSTANCE_ID" ,
	// session.getSessionInstanceId());
	// envelope = envelope.replace(":TIME_STAMP" ,
	// sdf.format(cal.getTime()).toString());
	//
	// StreamSource source = new StreamSource(new StringReader(envelope));
	// StreamResult result = new StreamResult(System.out);
	// webServiceTemplate.sendSourceAndReceiveToResult(url, source, result);
	// }
	//
	// private String getSessionDetailEnvelope(){
	//
	// StringBuilder builder = new StringBuilder();
	//
	// builder.append("<sys:SessionDetailRequest
	// xmlns:sys='http://localhost/systemService'>");
	//
	// builder.append("<sys:creatorId>:CREATOR_ID</sys:creatorId>");
	// builder.append("<sys:sessionInstanceId>:INSTANCE_ID</sys:sessionInstanceId>");
	// builder.append("<sys:timeStamp>:TIME_STAMP</sys:timeStamp>");
	// builder.append("</sys:SessionDetailRequest>");
	//
	// return builder.toString();
	//
	// }
	//
}
