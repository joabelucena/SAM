package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.ttrans.samapp.ws.bo.system.SessionDetail;

public class SystemServiceClient {
	/**
	 * Maestro targetNamespace: http://maestro.thalesgroup.com/wsdl/system
	 * VLTS Maestro Namespace: http://tempuri.org/
	 */
	private static final String TARGET_NAMESPACE = "http://tempuri.org/";

	public static void SessionDetail(String URL, SessionDetail session) {

		URL url;
		
		try {
			url = new URL(URL);

			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qName = new QName(TARGET_NAMESPACE, "SystemServices");
			
			QName qPort = new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType");
			
			Service service = Service.create(url, qName);
			
			SystemServiceClientEndpoint system = service.getPort(qPort, SystemServiceClientEndpoint.class);			
			
			system.sessionDetail(session);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
