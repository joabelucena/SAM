package br.com.ttrans.samapp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.library.DateHandle;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.KPI;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.service.KPIService;

@Repository
public class KPIServiceImpl implements KPIService {
	
	@Autowired
	private ServiceOrderDao orderDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private DAO dao;
	
	@Transactional
	public Set<KPI> loadAll() {
		
		Set<KPI> data = new HashSet<KPI>();
		
		List<Equipment> equipments = equipmentDao.loadData();
		
		/**
		 * Iterates over all equipments.
		 */
		for(Equipment equipment : equipments){
			
			//Loads equipment KPI data
			data.add(this.getKPI(equipment));

		}
		
		return data;
	}
	
	@Transactional
	public KPI getKPI(Equipment equipment) {
		
		//Retrieves the past X months SO info
		Integer months = Integer.parseInt(dao.getMv("SAM_MESMTBF", "3"));
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, (months * -1));
		
		cal.set(Calendar.DAY_OF_MONTH	, 1);
		cal.set(Calendar.HOUR_OF_DAY	, 0);
		cal.set(Calendar.MINUTE			, 0);
		cal.set(Calendar.SECOND			, 0);
		cal.set(Calendar.MILLISECOND	, 0);
		
		Date from = cal.getTime();
		Date to = new Date();
		
		return this.getKPI(equipment, from, to);
		
	}

	@Transactional
	public KPI getKPI(Equipment equipment, Date from, Date to) {
		
		/*
		 * Retrieves service order data
		 */
		List<ServiceOrder> orders = new ArrayList<ServiceOrder>(orderDao.loadKPIData(equipment, from , to));
		
		KPI kpi = new KPI();
		
		Collections.sort(orders, new Comparator<ServiceOrder>() {
		    public int compare(ServiceOrder o1, ServiceOrder o2) {
		        Date date1 = o1.getDatetime();
		        Date date2 = o2.getDatetime();
		        
		        return (date1.compareTo(date2));
		    }
		});
		
		Float brokenTime = 0f;
		
		for(ServiceOrder order : orders){
			
			Date d1, d2;
			
			d1 = new Date();
			d2 = new Date();
			
			/*
			 * Verifies if has a nested event and if so, consider its date 
			 * as start date
			 */
			if(order.getEvent() instanceof Event){
				d1 = order.getEvent().getDatetime();
			}else{
				d1 = order.getStart();
			}
			
			d2 = order.getEnd();
			
			/*
			 * Data truncation
			 */
			d1 = d1.before(from) ? from : d1;
			d2 = d2.after(to) ? to : d2;
					
			brokenTime += DateHandle.diff(d1, d2, equipment.getType().getDailyHours());
			
		}
		
		kpi.setEquipment(equipment);
		kpi.setBreakCount(orders.size());
		kpi.setBrokenTime(brokenTime);
		kpi.setFrom(from);
		kpi.setTo(to);
		kpi.setTotalTime(DateHandle.diff(from, to, equipment.getType().getDailyHours()));
		
		return kpi;
	}

}
