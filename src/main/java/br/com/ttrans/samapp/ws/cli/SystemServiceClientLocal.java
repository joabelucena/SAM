package br.com.ttrans.samapp.ws.cli;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ttrans.samapp.controller.HomeController;

@WebServiceClient(targetNamespace = "http://maestro.thalesgroup.com/wsdl/system")
public class SystemServiceClientLocal extends Service {
	
	private static final URL SOASERVICE_WSDL_LOCATION;
	private static final String TARGET_NAMESPACE = SystemServiceClientLocal.class.getAnnotation(WebServiceClient.class).targetNamespace();
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	static {
		 URL url = null;
		 
		 String wsdlLocation = SystemServiceClientLocal.class.getAnnotation(WebServiceClient.class).wsdlLocation();
	        try {
	            URL baseUrl;
	            baseUrl = SystemServiceClientLocal.class.getResource(".");
	            //WEB-INF/schemas/wsdl/SystemServices.wsdl
	            //WEB-INF/schemas/wsdl/SystemServices.wsdl
	            
	            url = new URL(baseUrl, wsdlLocation);
	        } catch (MalformedURLException e) {
	            logger.debug("Failed to create URL for the wsdl Location: '"+ wsdlLocation +"', retrying as a local file");
	            logger.debug(e.getMessage());
	        }
	        SOASERVICE_WSDL_LOCATION = url;
	}
	
	/**
	 * Constructor 1
	 * @param wsdlLocation
	 * @param serviceName
	 */
	public SystemServiceClientLocal(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
		
	}
	
	/**
	 * Default constructor
	 */
	public SystemServiceClientLocal() {
		super(SOASERVICE_WSDL_LOCATION, new QName(TARGET_NAMESPACE, "SystemServiceClientLocal"));
	}
	
	@WebEndpoint(name = "SystemServiceClientEndpoint")
    public SystemServiceClientEndpoint getSOAServiceSOAP() {
		return super.getPort(new QName(TARGET_NAMESPACE, "BasicHttpBinding_SystemPortType"), SystemServiceClientEndpoint.class);
    }
	
	@WebEndpoint(name = "SystemServiceClientEndpoint")
    public SystemServiceClientEndpoint getSOAServiceSOAP(String portName) {
		return super.getPort(new QName(TARGET_NAMESPACE, portName), SystemServiceClientEndpoint.class);
    }
    
	/**
	 * 
	 * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
	 * @return
	 */
    @WebEndpoint(name = "SystemServiceClientEndpoint")
    public SystemServiceClientEndpoint getSOAServiceSOAP(WebServiceFeature... features) {
        return super.getPort(new QName(TARGET_NAMESPACE, "SystemServiceClientEndpoint"), SystemServiceClientEndpoint.class, features);
    }
    
    

}
