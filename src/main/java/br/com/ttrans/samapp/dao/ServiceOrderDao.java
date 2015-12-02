package br.com.ttrans.samapp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.ServiceOrder;


public interface ServiceOrderDao {
	public int add(ServiceOrder serviceorder, Authentication authentication);
	public void edit(ServiceOrder serviceorder, Authentication authentication);
	public void delete(ServiceOrder serviceorder, Authentication authentication);
	public ServiceOrder get(int id);
	public List<ServiceOrder> loadData();
	public List<ServiceOrder> loadKPIData(Equipment equipment, Date d1, Date d2);
}
