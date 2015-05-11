package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceStation;

public interface ServiceStationDao {
	public void add(ServiceStation station, Authentication authentication);
	public void edit(ServiceStation station, Authentication authentication);
	public void delete(ServiceStation station, Authentication authentication);
	public List<ServiceStation> loadData();
}
