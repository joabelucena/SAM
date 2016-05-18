package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.ttrans.samapp.ws.bo.system.SessionDetail;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

public class SystemServiceClient {
	/**
	 * Maestro targetNamespace: http://maestro.thalesgroup.com/wsdl/system
	 */
	private static final String TARGET_NAMESPACE = "http://impl.endpoint.ws.samapp.ttrans.com.br/";

	public static void SessionDetail(String URL, SessionDetail session) {

		URL url;
		
		try {
			url = new URL(URL);

			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName(TARGET_NAMESPACE, "SystemServices");

			Service service = Service.create(url, qname);

			SystemEndpoint system = service.getPort(SystemEndpoint.class);
			
			system.SessionDetail(session);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
