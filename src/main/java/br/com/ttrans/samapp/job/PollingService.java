package br.com.ttrans.samapp.job;

import java.text.SimpleDateFormat;
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
 * Sam Keep Alive function.
 * 
 * For implementing this function, uncomment its bean on 'job-servlet.xml' file
 * 
 * @author Joabe Lucena
 *
 */
public class PollingService {

	private static final Logger logger = LoggerFactory.getLogger(PollingService.class);

	private static final long MAESTRO_ALIVE_RATE = 5000L;

	private Map<String, Session> sessions;
	
	@Autowired
	private DAO dao;

	public Map<String, Session> getSessions() {
		return sessions;
	}

	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}

	@Scheduled(fixedRate = MAESTRO_ALIVE_RATE)
	private void maestroAlive() {

		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");

		Set<Entry<String, Session>> ids = sessions.entrySet();

		for (Entry<String, Session> entry : ids) {

			logger.debug("SessionID: " + entry.getKey());
			logger.debug("Alive: " + formatter.format(entry.getValue().getAlive()));
			logger.debug("Current Date: " + formatter.format(new Date()));
			logger.debug("Difrence: " + (new Date().getTime() - entry.getValue().getAlive().getTime()));

			if ((new Date().getTime() - entry.getValue().getAlive().getTime()) > MAESTRO_ALIVE_RATE) {

				try {
					disconnect(entry.getKey(), entry.getValue());
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				logger.debug("true");
			} else {
				logger.debug("false");
			}

			logger.debug("*************************************");
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
