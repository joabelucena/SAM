package br.com.ttrans.samapp.job;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.ttrans.samapp.library.DateBuilder;
import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.ws.bo.system.Disconnection;
import br.com.ttrans.samapp.ws.bo.system.Session;
import br.com.ttrans.samapp.ws.cli.SystemServiceClient;
import br.com.ttrans.samapp.ws.endpoint.SystemEndpoint;

/**
 * This class stands for scheduling common tasks. It is implemented on a
 * separated servlet under 'job-servlet.xml' setup.
 * 
 * @author Joabe Lucena
 * 
 * @see job-servlet.xml
 *
 */
public class PollingService {

	private static final Logger logger = LoggerFactory.getLogger(PollingService.class);

	private static final long MAESTRO_SYSTEM_ALIVE_PERIOD = 30000L;

	private Map<String, Session> sessions;

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

			logger.debug(entry.toString());
			logger.debug("Time GAP: " + (gap = (new Date().getTime() - entry.getValue().getAlive().getTime())));

			if (gap > MAESTRO_SYSTEM_ALIVE_PERIOD) {
				
				String sessionInstanceId = entry.getKey();
				Session session = entry.getValue();

				try {
					disconnect(sessionInstanceId, session);
				} catch (DatatypeConfigurationException e) {
					logger.error("A problem occurred while disconnecting system: " + session.getIP() + ". Error details below: ");
					logger.error(e.getMessage());
				}
			}
		}
	}

	private void disconnect(final String sessionID, Session session) throws DatatypeConfigurationException {

		final IP ip = session.getIP();

		final String creatorId = session.getConnection().getCreatorId();

		final Disconnection disconnection = new Disconnection(SystemEndpoint.SAM_CREATOR_ID, sessionID,
				DateBuilder.newXMLGregorianCalendarDate(new Date()));

		final Session rollback = sessions.remove(sessionID);

		/*
		 * Checks if it was removed from sessions collection before calling
		 * Disconnection service
		 */
		if (rollback != null) {

			Thread call = new Thread() {

				public void run() {
					try {

						SystemServiceClient.Disconnection(creatorId, disconnection);

						logger.debug("Timeout disconnection: " + ip);

					} catch (Exception e) {
						logger.error("It wasn't possible to call Disconnecton() for: " + ip + ". Error details below:");
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
