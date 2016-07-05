package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.ws.bo.system.*;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

@WebService(serviceName="SystemServices",
			portName="SystemPort",
			targetNamespace="http://maestro.thalesgroup.com/wsdl/system/xsd",
			endpointInterface="br.com.ttrans.samapp.ws.endpoint.SystemEndpoint")
public class SystemServicesImpl implements SystemEndpoint {

	@Resource
	WebServiceContext wsContext;
	
	private static final Logger logger = LoggerFactory.getLogger(SystemServicesImpl.class);

	private Map<String, Connection> connections;
	
	@Autowired
	private DAO dao;

	@WebMethod(exclude = true)
	public void setConnections(Map<String, Connection> connections) {
		this.connections = connections;
	}
	
	@WebMethod(exclude = true)
	public  Map<String, Connection> getConnections() {
		return this.connections;
	}

	@Override
	public void Connection(Connection payload) {
		
		//Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);

		//Generates connection id
		String hash = String.valueOf(payload.getCreatorId().hashCode() + payload.getTimeStamp().hashCode());
		
		final SessionDetail session = new SessionDetail(payload.getCreatorId(),
														hash,
														payload.getTimeStamp());
		
		final String urlWsdl = dao.getMv("SYS_WSDLMSYS", "")
				.replace("<host>", req.getRemoteAddr());
		
		if(urlWsdl.isEmpty()){
			logger.info("Não foi encontrado o parâmetro 'SYS_WSDLMSYS' contendo a localização do WSDL do serviço SystemServices.");
		}else{
			
			
			Thread call = new Thread(){
				
				public void run(){
					try {
						SystemServiceClient.SessionDetail(urlWsdl, session);
						
						logger.info("*************************");
						logger.info("** Retornado com sucesso para: " + urlWsdl + " **");
						logger.info("** Id: " + session.getSessionInstanceId());
						logger.info("** creatorId: " + session.getCreatorId());
						logger.info("** timeStamp: " + session.getTimeStamp());
						logger.info("*************************");
						
					} catch (Exception e) {
						logger.error("Não foi possivel chamar SessionDetails() para a URL: " + urlWsdl + ". Detalhes do Erro:");
						logger.error(e.getMessage());
					}	
				}				
			};
			
			call.start();
		}

		// Add Connection + HashCode
		connections.put(hash, payload);
		
		logger.info("Quantidade de Conexoes Ativas: " + connections.size());
		logger.info("IP cliente: " + req.getRemoteAddr());

		logger.info("*************************");
		logger.info("** Nova conexão criada **");
		logger.info("** Id: " + hash);
		logger.info("** creatorId: " + connections.get(hash).getCreatorId());
		logger.info("** timeStamp: " + connections.get(hash).getTimeStamp());
		logger.info("*************************");
	}
	
	
	@Override
	public void SessionDetail(SessionDetail payload) {
		
		logger.info("*************************");
		logger.info("** Detalhes da Seção **");
		logger.info("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.info("** creatorId: " + payload.getCreatorId());
		logger.info("** timeStamp: " + payload.getTimeStamp());
		logger.info("*************************");
		
	}


	@Override
	public void Alive(Alive payload) {
		
		if (connections.containsKey(payload.getSessionInstanceId())) {
			
			if (payload.getConnectionStatus() == 1) {
				
				logger.error("ERRO DE COMUNICAÇÂO COM O SAM: " + payload.getCreatorId());
				
			}
		}
		
	}


	@Override
	public void Active(Active payload) {
		
		
		logger.info("*************************");
		logger.info("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.info("** creatorId: " + payload.getCreatorId());
		logger.info("** timeStamp: " + payload.getTimeStamp());
		logger.info("*************************");
		
	}


	@Override
	public void Disconnection(Disconnection payload) {
		
		//Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);

		
		logger.info("DESCONEXÃO PEDIDA PELO SISTEMA");
		logger.info("*************************");
		logger.info("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.info("** creatorId: " + payload.getCreatorId());
		logger.info("** timeStamp: " + payload.getTimeStamp());
		logger.info("*************************");
		
		//Removes connection
		connections.remove(payload.getSessionInstanceId());
		
		logger.info("Quantidade de Conexoes Ativas: " + connections.size());
		logger.info("IP cliente: " + req.getRemoteAddr());
		
	}
	
}
