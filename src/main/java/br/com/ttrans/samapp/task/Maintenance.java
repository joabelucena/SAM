package br.com.ttrans.samapp.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class Maintenance {
	
	@Scheduled(fixedRate=10000)
	public void checkRules(){
		System.out.println(new SimpleDateFormat("hh:mm:ss a").format(new Date()) + " - Verificando as regras para gerar evento......");
	}

}
