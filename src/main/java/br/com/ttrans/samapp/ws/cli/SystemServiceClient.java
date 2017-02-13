package br.com.ttrans.samapp.ws.cli;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.SessionDetail;

public class SystemServiceClient {
	
	@SuppressWarnings("unused")
	private static final String TARGET_NAMESPACE = "http://maestro.thalesgroup.com/wsdl/system";

	private static final Logger logger = LoggerFactory.getLogger(SystemServiceClient.class);
	
	public static void SessionDetail(String creatorId, SessionDetail session)
			throws IOException, MalformedURLException {

		final String wsdlLocation;
		final String portName;
		final String serviceName;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		try (InputStream input = classLoader.getResourceAsStream("maestro.properties")) {

			Properties prop = new Properties();
			
			prop.load(input);

			wsdlLocation = prop.getProperty("creatorId.".concat(creatorId));
			portName = prop.getProperty("portName.".concat(creatorId));
			serviceName = prop.getProperty("serviceName.".concat(creatorId));
			
		} catch (IOException e) {

			logger.error(
					"Não foi possivel abrir o arquivo de configuração 'maestro.properties'." + ". Detalhes do Erro:");

			e.printStackTrace();

			// This catch provides try-with-resources
			throw e;
		}

		try {

			WebServiceClient annotation = SystemServiceClientLocal.class.getAnnotation(WebServiceClient.class);

			// URL baseUrl = SystemServiceClientLocal.class.getResource("");

			URL url = new URL(wsdlLocation);
			
			SystemServiceClientLocal serviceObj = new SystemServiceClientLocal(url,
					new QName(annotation.targetNamespace(), serviceName));

			SystemServiceClientEndpoint system = serviceObj.getSOAServiceSOAP(portName);

			system.sessionDetail(session);

		} catch (MalformedURLException e) {
			logger.error("Não foi possivel realizar a chamada de SessionDetails() para a URL: " + wsdlLocation
					+ ". Detalhes do Erro:");

			e.printStackTrace();

			throw e;
		}

	}

	public static void Disconnection(String creatorId, Disconnection disconnection) throws IOException, MalformedURLException {
		final String wsdlLocation;
		final String portName;
		final String serviceName;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		try (InputStream input = classLoader.getResourceAsStream("maestro.properties")) {

			Properties prop = new Properties();
			
			prop.load(input);

			wsdlLocation = prop.getProperty("creatorId.".concat(creatorId));
			portName = prop.getProperty("portName.".concat(creatorId));
			serviceName = prop.getProperty("serviceName.".concat(creatorId));
			
		} catch (IOException e) {

			logger.error(
					"Não foi possivel abrir o arquivo de configuração 'maestro.properties'." + ". Detalhes do Erro:");

			e.printStackTrace();

			// This catch provides try-with-resources
			throw e;
		}

		try {

			WebServiceClient annotation = SystemServiceClientLocal.class.getAnnotation(WebServiceClient.class);

			// URL baseUrl = SystemServiceClientLocal.class.getResource("");

			URL url = new URL(wsdlLocation);
			
			SystemServiceClientLocal serviceObj = new SystemServiceClientLocal(url,
					new QName(annotation.targetNamespace(), serviceName));

			SystemServiceClientEndpoint system = serviceObj.getSOAServiceSOAP(portName);

			system.disconnection(disconnection);

		} catch (MalformedURLException e) {
			logger.error("Não foi possivel realizar a chamada de SessionDetails() para a URL: " + wsdlLocation
					+ ". Detalhes do Erro:");

			e.printStackTrace();

			throw e;
		}
	}
	
	
	/**
	 * Implementation with URL
	 * 
	 * @param URL
	 * @param disconnection
	 * @throws MalformedURLException
	 */
//	public static void Disconnection(String URL, Disconnection disconnection) throws MalformedURLException {
//
//		URL url = new URL(URL);
//
//		// Service name
//		QName qName = new QName(TARGET_NAMESPACE, "SystemServices");
//
//		// Service port
//		QName qPort = new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType");
//
//		Service service = Service.create(url, qName);
//
//		SystemServiceClientEndpoint system = service.getPort(qPort, SystemServiceClientEndpoint.class);
//
//		system.disconnection(disconnection);
//	}
}
