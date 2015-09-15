package br.com.ttrans.samapp.library;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailClient {

	@Autowired
	private DAO dao;

	private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
	
	/**
	 * Sends an email according to the parameters.
	 * 
	 * @param cTo String vector with 'TO' field recipients list;
	 * @param cCC String vector with 'CC' field recipients list;
	 * @param cCCO String vector with 'CCO' field recipients list;
	 * @param subject Mail subject
	 * @param text HTML formated message
	 */
	public void sendMail(String[] cTo, String[] cCC, String[] cCCO, String subject, String text) {

		Properties props = new Properties();

		/**
		 * TLS
		 */
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", dao.getMv("SYS_MAILHOST"));
		props.put("mail.smtp.port", dao.getMv("SYS_MAILPORT"));

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(dao
								.getMv("SYS_MAILUSER"), dao
								.getMv("SYS_MAILPASS"));
					}
				});

		/** 
		 * Enables debugging console: session.setDebug(true);
		 */

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(dao.getMv("SYS_MAILUSER")));

			Address[] toUser = new Address[cTo.length];

			/**
			 * Loads user's list on message object
			 */
			for (int i = 0; i < cTo.length; i++) {
				toUser[i] = new InternetAddress(cTo[i]);
			}
			
			message.setRecipients(Message.RecipientType.TO, toUser);
			
			/**
			 * Check if there's any CC recipient and load it.
			 */
			if(cCC != null){
				
				Address[] ccUser = new Address[cCC.length];
				
				for (int i = 0; i < cCC.length; i++) {
					ccUser[i] = new InternetAddress(cCC[i]);
				}
				
				message.setRecipients(Message.RecipientType.CC, ccUser);				
			}
			
			/**
			 * Check if there's any BCC recipient and load it.
			 */
			if(cCCO != null){
				
				Address[] ccoUser = new Address[cCCO.length];
				
				for (int i = 0; i < cCCO.length; i++) {
					ccoUser[i] = new InternetAddress(cCCO[i]);
				}
				
				message.setRecipients(Message.RecipientType.BCC, ccoUser);
			}
			
			
			message.setSubject(subject);
			message.setContent(text, "text/html; charset=utf-8");

			Transport.send(message);

		} catch (MessagingException e) {
			logger.error("Error on sending email. Info: \n" 
					+ "TO: " + cTo.toString() + "\n"
					+ "SUBJECT: " + subject + "\n"
					+ "********************************* \n"
					+ "SERVER (SMTP): "	+ props.getProperty("mail.smtp.host") + "\n"
					+ "PORT (SMTP): "+ props.getProperty("mail.smtp.port")
					+ "********************************* \n"
					+ "Error message \n"
					+ e.getMessage());
			

		}
	}
}
