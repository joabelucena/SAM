package br.com.ttrans.samapp.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class KeepAlive {
	
	private static final Logger logger = LoggerFactory.getLogger(KeepAlive.class);
	
	@Scheduled(fixedRate=10000)
	private void imAlive(){
		logger.info("I'm Sam and I'm Alive =)");
	}

}
