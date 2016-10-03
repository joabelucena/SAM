package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.SessionDetail;

public class SystemServiceClient {

	private static final String TARGET_NAMESPACE = "http://maestro.thalesgroup.com/wsdl/system";

	public static void SessionDetail(String URL, SessionDetail session) throws MalformedURLException {

		URL url = new URL(URL);

		//Service name
		QName qName = new QName(TARGET_NAMESPACE, "SystemServices");

		//Service port
		QName qPort = new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType");

		Service service = Service.create(url, qName);
		
		SystemServiceClientEndpoint system = service.getPort(qPort, SystemServiceClientEndpoint.class);

		system.sessionDetail(session);

	}

	public static void Disconnection(String URL, Disconnection disconnection) throws MalformedURLException {

		URL url = new URL(URL);

		//Service name
		QName qName = new QName(TARGET_NAMESPACE, "SystemServices");
		
		//Service port
		QName qPort = new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType");

		Service service = Service.create(url, qName);

		SystemServiceClientEndpoint system = service.getPort(qPort, SystemServiceClientEndpoint.class);

		system.disconnection(disconnection);
	}
}
