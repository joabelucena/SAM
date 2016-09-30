package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Date;
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
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.ws.bo.system.Active;
import br.com.ttrans.samapp.ws.bo.system.Alive;
import br.com.ttrans.samapp.ws.bo.system.Connection;
import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.Session;
import br.com.ttrans.samapp.ws.bo.system.SessionDetail;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

@WebService(serviceName = "SystemServices", portName = "SystemPort", targetNamespace = "http://maestro.thalesgroup.com/wsdl/system/xsd", endpointInterface = "br.com.ttrans.samapp.ws.endpoint.SystemEndpoint")
@Component
public class SystemServicesImpl implements SystemEndpoint {

	@Resource
	WebServiceContext context;

	private static final Logger logger = LoggerFactory.getLogger(SystemServicesImpl.class);

	private Map<String, Session> sessions;

	@Autowired
	private DAO dao;

	@WebMethod(exclude = true)
	public void setSessions(Map<String, Session> connections) {
		this.sessions = connections;
	}

	@WebMethod(exclude = true)
	public Map<String, Session> getSessions() {
		return this.sessions;
	}

	@Override
	public void Connection(Connection payload) {

		// Retrieves Http Request
		HttpServletRequest req = (HttpServletRequest) context.getMessageContext().get(MessageContext.SERVLET_REQUEST);

		// Generates connection id
		String hash = String.valueOf(payload.getCreatorId().hashCode() + payload.getTimeStamp().hashCode());

		final SessionDetail session = new SessionDetail(SAM_CREATOR_ID, hash, payload.getTimeStamp());

		final String urlWsdl = dao.getMv("SYS_WSDLMSYS", "").replace("<host>", req.getRemoteAddr());

		if (urlWsdl.isEmpty()) {
			logger.error(
					"Não foi encontrado o parâmetro 'SYS_WSDLMSYS' contendo a localização do WSDL do serviço SystemServices.");
		} else {

			Thread call = new Thread() {

				public void run() {
					try {
						SystemServiceClient.SessionDetail(urlWsdl, session);

						logger.debug("*************************");
						logger.debug("** Retornado com sucesso para: " + urlWsdl + " **");
						logger.debug("** Id: " + session.getSessionInstanceId());
						logger.debug("** creatorId: " + session.getCreatorId());
						logger.debug("** timeStamp: " + session.getTimeStamp());
						logger.debug("*************************");

					} catch (Exception e) {
						logger.error("Não foi possivel chamar SessionDetails() para a URL: " + urlWsdl
								+ ". Detalhes do Erro:");
						logger.error(e.getMessage());
					}
				}
			};

			call.start();
		}

		// Add Connection + HashCode
		sessions.put(hash, new Session(payload, new Date(), new IP(req.getRemoteAddr())));

		logger.debug("** Quantidade de Conexoes Ativas: " + sessions.size());
		logger.debug("** IP cliente: " + req.getRemoteAddr());

		logger.debug("*************************");
		logger.debug("** Nova conexão criada **");
		logger.debug("** Id: " + hash);
		logger.debug("** creatorId: " + sessions.get(hash).getConnection().getCreatorId());
		logger.debug("** timeStamp: " + sessions.get(hash).getConnection().getTimeStamp());
		logger.debug("*************************");
	}

	@Override
	public void SessionDetail(SessionDetail payload) {

		logger.debug("*************************");
		logger.debug("** Detalhes da Seção **");
		logger.debug("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.debug("** creatorId: " + payload.getCreatorId());
		logger.debug("** timeStamp: " + payload.getTimeStamp());
		logger.debug("*************************");

	}

	@Override
	public void Alive(Alive payload) {

		if (sessions.containsKey(payload.getSessionInstanceId())) {

			switch (payload.getConnectionStatus()) {

			case 0:
				
				// Updates session last alive
				sessions.get(payload.getSessionInstanceId())
					.setAlive(new Date());
				
				break;

			case 1:
				// TODO Check what must be implemented in this case				
				logger.error("Erro de comunicação com o SAM: " + payload.getCreatorId());
				break;

			default:
				assert false : "Wrong data type";
			}

		}

	}

	@Override
	public void Active(Active payload) {

		logger.debug("*************************");
		logger.debug("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.debug("** creatorId: " + payload.getCreatorId());
		logger.debug("** timeStamp: " + payload.getTimeStamp());
		logger.debug("*************************");

	}

	@Override
	public void Disconnection(Disconnection payload) {

		logger.debug("*************************");
		logger.debug("** Desconexão solicitada pelo sistema");
		logger.debug("*************************");
		logger.debug("** SessionInstanceId: " + payload.getSessionInstanceId());
		logger.debug("** creatorId: " + payload.getCreatorId());
		logger.debug("** timeStamp: " + payload.getTimeStamp());
		logger.debug("*************************");

		// Removes connection
		sessions.remove(payload.getSessionInstanceId());
		
	}

}
