package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.ttrans.samapp.ws.bo.system.SessionDetail;

public class SystemServiceClient {
	
	/**
	 * Atributo: targetNamespace.
	 * Encontrado em Santos: http://tempuri.org/
	 * Correto: http://maestro.thalesgroup.com/wsdl/system/xsd
	 */
	private static final String TARGET_NAMESPACE = "http://tempuri.org/";

	public static void SessionDetail(String URL, SessionDetail session) {

		URL url;
		
		try {
			url = new URL(URL);

			QName qName = new QName(TARGET_NAMESPACE, "SystemServices");
			
			/**
			 * Tag: <port>.
			 * Encontrado em Santos: BasicHttpBinding_SystemPortType
			 * Correto: SystemPort
			 */
			QName qPort = new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType");
			
			Service service = Service.create(url, qName);
			
			/**
			 * A classe SystemServiceClientEndpoint foi criada pois o método SessionDetail se encontra minúsculo
			 * no EPS e o NameSpace divergente (tempuri.org). Caso sejam padronizados/corrigidos esses pontos, poderá ser 
			 * usada a classe br.com.ttrans.samapp.ws.endpoint.SystemEndpoint.
			 */
			SystemServiceClientEndpoint system = service.getPort(qPort, SystemServiceClientEndpoint.class);			
			
			system.sessionDetail(session);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
