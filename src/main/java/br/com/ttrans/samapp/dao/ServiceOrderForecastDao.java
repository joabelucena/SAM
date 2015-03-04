package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderForecast;

public interface ServiceOrderForecastDao {
	public void add(ServiceOrderForecast forecast, Authentication authentication);
	public void edit(ServiceOrderForecast forecast, Authentication authentication);
	public void delete(ServiceOrderForecast forecast, Authentication authentication);
	public ServiceOrderForecast get(int id);
	public List loadData();
}
