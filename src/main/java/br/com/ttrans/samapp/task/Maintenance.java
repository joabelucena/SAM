package br.com.ttrans.samapp.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class Maintenance {
	
	private static final Logger logger = LoggerFactory.getLogger(Maintenance.class);
	
	@Scheduled(fixedRate=20000)
	public void checkRules(){
		logger.info(new SimpleDateFormat("hh:mm:ss a").format(new Date()) + " - Verificando as regras para gerar evento......");
	}

}