package br.com.ttrans.samapp.ws.endpoint.impl;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

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
import br.com.ttrans.samapp.library.DateBuilder;
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

	private Map<String, String> systems;

	@Autowired
	private DAO dao;

	@WebMethod(exclude = true)
	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}

	@WebMethod(exclude = true)
	public Map<String, Session> getSessions() {
		return this.sessions;
	}

	@WebMethod(exclude = true)
	public void setSystems(Map<String, String> systems) {
		this.systems = systems;
	}

	@WebMethod(exclude = true)
	public Map<String, String> getSystems() {
		return this.systems;
	}

	@Override
	public void Connection(final Connection payload) {

		// Retrieves Http Request
		final HttpServletRequest req = (HttpServletRequest) context.getMessageContext()
				.get(MessageContext.SERVLET_REQUEST);

		final IP ip = new IP(req.getRemoteAddr());

		// Generates sessionInstanceId
		final String sessionInstanceId = UUID.randomUUID().toString();

		final SessionDetail session = new SessionDetail(SAM_CREATOR_ID, sessionInstanceId, payload.getTimeStamp());

		final String creatorId = payload.getCreatorId();

		logger.debug(payload.toString());

		logger.debug("Client IP: " + ip);

		Set<Entry<String, Session>> ids = sessions.entrySet();

		for (Entry<String, Session> entry : ids) {

			if (entry.getValue().getConnection().getCreatorId().equals(payload.getCreatorId()))
				sessions.remove(entry.getKey());
		}

		Thread call = new Thread() {

			public void run() {
				try {
					SystemServiceClient.SessionDetail(creatorId, session);

					// Add Connection + HashCode
					sessions.put(sessionInstanceId, new Session(payload, new Date(), ip));

					logger.debug("New connection established: " + sessionInstanceId);

				} catch (Exception e) {

					/*
					 * Exceptions are handled on SystemServiceClient class. This
					 * try-catch stands for controlling the Sessions Map
					 * 
					 */
					return;

				}
			}
		};

		call.start();

	}

	@Override
	public void SessionDetail(SessionDetail payload) {

		logger.debug(payload.toString());
	}

	@Override
	public void Alive(final Alive payload) {

		// Retrieves Http Request
		final HttpServletRequest req = (HttpServletRequest) context.getMessageContext()
				.get(MessageContext.SERVLET_REQUEST);

		logger.debug(payload.toString());

		if (sessions.containsKey(payload.getSessionInstanceId())) {

			switch (payload.getConnectionStatus()) {

			case 0:

				// Updates session last alive
				sessions.get(payload.getSessionInstanceId()).setAlive(new Date());

				break;

			case 1:
				// TODO Check what must be implemented when connection status is
				// 1
				logger.error("Erro de comunicação com o SAM: ");
				logger.debug(payload.toString());
				break;

			default:
				assert false : "Wrong data type";
			}

		} else {

			logger.debug("Session " + payload.getSessionInstanceId() + " is not active.");

			final String urlWsdl = dao.getMv("SYS_WSDLMSYS", "").replace("<host>", req.getRemoteAddr());

			if (urlWsdl.isEmpty()) {
				logger.error(
						"Não foi encontrado o parâmetro 'SYS_WSDLMSYS' contendo a localização do WSDL do serviço SystemServices.");
			} else {

				Thread call = new Thread() {

					public void run() {
						try {

							Disconnection disconnection = new Disconnection(SAM_CREATOR_ID,
									payload.getSessionInstanceId(),
									DateBuilder.newXMLGregorianCalendarDate(new Date()));

							SystemServiceClient.Disconnection(urlWsdl, disconnection);

						} catch (Exception e) {
							logger.error("Não foi possivel chamar SessionDetails() para a URL: " + urlWsdl
									+ ". Detalhes do Erro:");
							logger.error(e.getMessage());
						}
					}
				};

				call.start();
			}

		}

	}

	@Override
	public void Active(Active payload) {

		logger.debug(payload.toString());

	}

	@Override
	public void Disconnection(Disconnection payload) {

		logger.debug(payload.toString());

		// Removes connection
		sessions.remove(payload.getSessionInstanceId());

	}

}
