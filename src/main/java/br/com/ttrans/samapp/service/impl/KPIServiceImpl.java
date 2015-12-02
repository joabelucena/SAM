package br.com.ttrans.samapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.axis.types.Month;
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
	
	@Transactional
	public List<KPI> loadAll() {
		
		List<KPI> kpi = new ArrayList<KPI>();
		
		List<Equipment> equipments = equipmentDao.loadData();
		
		for(Equipment equipment : equipments){
			kpi.add(this.getKPI(equipment));
		}
		
		return kpi;
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
		
		return this.getKPI(equipment, date, new Date());
		
	}

	@Transactional
	public KPI getKPI(Equipment equipment, Date d1, Date d2) {
		Month mont = new Month(8);
		
		mont.setMonth(1);
		
		List<ServiceOrder> orders = new ArrayList<ServiceOrder>(orderDao.loadKPIData(equipment, d1, d2));
		
		KPI kpi = new KPI(equipment);
		
		Collections.sort(orders, new Comparator<ServiceOrder>() {
		    public int compare(ServiceOrder o1, ServiceOrder o2) {
		        Date d1 = o1.getDatetime();
		        Date d2 = o2.getDatetime();
		        
		        return (d1.compareTo(d2));
		    }
		});
		
		int totalTime, brokenTime = 0;
		
		for(ServiceOrder order : orders){
			
		}
		
		
		
		
		
		return null;
	}

}
