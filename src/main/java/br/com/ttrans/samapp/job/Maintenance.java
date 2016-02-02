package br.com.ttrans.samapp.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.library.MailClient;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.KPI;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.KPIService;
import br.com.ttrans.samapp.service.TaskService;

@Component
public class Maintenance {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MailClient mail;
	
	@Autowired
	private KPIService kpiService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	private static final Logger logger = LoggerFactory.getLogger(Maintenance.class);
	
	/**
	 * Process all maintenance tasks
	 * 
	 * Runs every day @ 23:30
	 */
	@Scheduled(cron="0 30 23 1/1 * ?")
	public void checkRules(){
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Processing Tasks.. Please Wait......");
		
		try {
			taskService.processAll();	
		} catch (Exception e) {
			logger.error("** The following error(s) were found: ");
			logger.error(e.getMessage());
			
			mail.sendMail(new String[]{"joabelucena@gmail.com"}, 
					null, 
					null, 
					"Erro na execução das tarefas.", 
					e.getMessage());
		}
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Done");
	}
	
	/**
	 * Runs KPI's calcs and update equipment's MTBF calc field.
	 */
//	@Scheduled(cron="0 0 0 ? 1/1 MON#1")
	@Scheduled(cron="0 0 23 1/1 * ?")
	public void KPI(){
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Processing KPIs.. Please Wait......");
		
		Set<KPI> kpis = kpiService.loadAll();
		
		for (KPI kpi : kpis) {
			
			Equipment equipment = kpi.getEquipment();
			
			equipment.setMtbfCalc((double) kpi.getMTBF());
			
		}
		
		logger.info(new SimpleDateFormat("dd/mm/YYYY hh:mm:ss a").format(new Date()) + " - Done");
	}

}