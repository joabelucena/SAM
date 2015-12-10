package br.com.ttrans.samapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.KPI;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.service.KPIService;

@Repository
public class KPIServiceImpl implements KPIService {
	
	@Autowired
	private ServiceOrderDao orderDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	/**
	 * Defines how many months will be considered for retrieving the KPIs.
	 */
	private static final int PAST_MONTHS = 12;
	
	/**
	 * @see KPIServiceImpl
	 */
	@Transactional
	public Map<String, Set<KPI>> loadAll() {
		
		Map<String, Set<KPI> > data = new HashMap<String, Set<KPI> >();
		
		List<Equipment> equipments = equipmentDao.loadData();
		
		/**
		 * Iterates over all equipments.
		 */
		for(Equipment equipment : equipments){
			
			Set<KPI> kpi = new HashSet<KPI>();
			
			/**
			 * Loads KPI from a predetermined date 			
			 */
			for(int i = PAST_MONTHS; i >= 1; i--){
				
				//Return the first day from the past 'i' month.
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.MONTH, -i);
				
				cal.set(Calendar.DAY_OF_MONTH	, 1);
				cal.set(Calendar.HOUR			, 0);
				cal.set(Calendar.MINUTE			, 0);
				cal.set(Calendar.SECOND			, 0);
				cal.set(Calendar.MILLISECOND	, 0);
				
				Date reference = cal.getTime();
				
				//Loads an equipment's KPIs from specific month 
				kpi.add(this.getKPI(equipment, reference));
				
			}
			
			//Loads equipment KPI data
			data.put(equipment.getId(), kpi);
			
		}
		
		return data;
	}
	
	@Transactional
	public KPI getKPI(Equipment equipment) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = new Date();
		
		try {
			date = sdf.parse("08/11/2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.getKPI(equipment, date);
		
	}

	@Transactional
	public KPI getKPI(Equipment equipment, Date reference) {
		
		List<ServiceOrder> orders = new ArrayList<ServiceOrder>(orderDao.loadKPIData(equipment, new Date(), new Date()));
		
		KPI kpi = new KPI();
		
		Collections.sort(orders, new Comparator<ServiceOrder>() {
		    public int compare(ServiceOrder o1, ServiceOrder o2) {
		        Date d1 = o1.getDatetime();
		        Date d2 = o2.getDatetime();
		        
		        return (d1.compareTo(d2));
		    }
		});
		
		int totalTime, brokenTime;
		
		totalTime = brokenTime = 0;
		
		for(ServiceOrder order : orders){
			
		}
		
		kpi.setBreakCount(orders.size());
		kpi.setBrokenTime(brokenTime);
		kpi.setTotalTime(totalTime);
		kpi.setReference(reference);
		
		return kpi;
	}

}
