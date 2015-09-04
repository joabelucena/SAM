package br.com.ttrans.samapp.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.service.TaskService;

@Component
public class Maintenance {
	
	@Autowired
	private TaskService service;
	
	private static final Logger logger = LoggerFactory.getLogger(Maintenance.class);
	
	/**
	 * Runs every day @ 23:30
	 */
	@Scheduled(cron="0 30 23 1/1 * ?")
	public void checkRules(){
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Processing Tasks.. Please Wait......");
		
		try {
			service.processAll();	
		} catch (Exception e) {
			logger.error("** The following error(s) were found: ");
			logger.error(e.getMessage());
		}
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Done");
	}

}