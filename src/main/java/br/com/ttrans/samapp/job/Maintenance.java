package br.com.ttrans.samapp.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class Maintenance {
	
	private static final Logger logger = LoggerFactory.getLogger(Maintenance.class);
	
	/**
	 * Runs every day @ 23:30
	 */
	@Scheduled(cron="0 30 23 1/1 * ? *")
	public void checkRules(){
		logger.info(new SimpleDateFormat("hh:mm:ss a").format(new Date()) + " - Processing Tasks.. Please Wait......");
		
		logger.info(new SimpleDateFormat("hh:mm:ss a").format(new Date()) + " - Done =)");
	}

}