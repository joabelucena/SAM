package br.com.ttrans.samapp.job;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.library.DateBuilder;
import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.Session;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

/**
 * This class stands for scheduling common tasks. It is implemented on a separated servlet
 * under 'job-servlet.xml' setup.
 * 
 * @author Joabe Lucena
 * 
 * @see job-servlet.xml
 *
 */
public class PollingService {

	private static final Logger logger = LoggerFactory.getLogger(PollingService.class);

	private static final long MAESTRO_SYSTEM_ALIVE_PERIOD = 5000L;

	private Map<String, Session> sessions;
	
	@Autowired
	private DAO dao;

	public Map<String, Session> getSessions() {
		return sessions;
	}

	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}

	@Scheduled(fixedRate = MAESTRO_SYSTEM_ALIVE_PERIOD)
	private void maestroAlive() {
		
		Set<Entry<String, Session>> ids = sessions.entrySet();
		
		logger.debug("Active connections: " + sessions.size());
		
		long gap;

		for (Entry<String, Session> entry : ids) {
			
			logger.debug("" + entry);
			logger.debug("Time GAP: " + (gap = (new Date().getTime() - entry.getValue().getAlive().getTime())));

			if (gap > MAESTRO_SYSTEM_ALIVE_PERIOD) {

				try {
					disconnect(entry.getKey(), entry.getValue());
				} catch (DatatypeConfigurationException e) {
					logger.error("Problema ao desconectar sistema. Detalhes do erro: ");
					logger.error(e.getMessage());
				}
			}
		}
	}

	private void disconnect(final String sessionID, Session session) throws DatatypeConfigurationException {
		
		final String urlWsdl = dao.getMv("SYS_WSDLMSYS", "").replace("<host>", session.getIP());

		final Disconnection disconnection = new Disconnection(SystemEndpoint.SAM_CREATOR_ID, sessionID,
				DateBuilder.newXMLGregorianCalendarDate(new Date()));
		
		if (urlWsdl.isEmpty()) {
			logger.info(
					"Não foi encontrado o parâmetro 'SYS_WSDLMSYS' contendo a localização do WSDL do serviço SystemServices.");
			return;
		}
		
		final Session rollback = sessions.remove(sessionID);
		
		/*
		 * Checks if it was removed from sessions collection before calling
		 * Disconnection service
		 */
		if (rollback != null) {

			Thread call = new Thread() {

				public void run() {
					try {

						SystemServiceClient.Disconnection(urlWsdl, disconnection);

						logger.debug("Desconexão por Timeout");

					} catch (Exception e) {
						logger.error("Não foi possivel chamar Disconnection() para a URL: " + urlWsdl
								+ ". Detalhes do Erro:");
						logger.error(e.getMessage());
						
						sessions.put(sessionID, rollback);
					}
				}
			};

			call.start();

		} else {
			logger.info("não removeu");
		}
	}

	// @Scheduled(fixedRate=10000)
	// private void imAlive(){
	// logger.info("I'm Sam and I'm Alive =)");
	// }

}
