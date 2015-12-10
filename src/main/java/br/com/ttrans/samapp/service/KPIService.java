package br.com.ttrans.samapp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.KPI;

/**
 * KPI Service for retrieving the Key Process Indicators
 * 
 * @author Joabe Lucena
 *
 */
public interface KPIService {
	
	/**
	 * Loads all Indicators data
	 * @return A list containing all equipments and its indicators
	 */
	public Map<String, Set<KPI> > loadAll();
	
	/**
	 * Retrieves indicators by equipment.
	 * 
	 * @param equipment Equipment to retrieve the indicators
	 * @return An object containing the indicators
	 */
	public KPI getKPI(Equipment equipment);
	
	/**
	 * Retrieves the MTBF and MTTR indicators by equipment on a specific period.
	 * 
	 * @param equipment Equipment to retrieve the indicators
	 * @param reference Initial date to analyze
	 * 
	 */
	public KPI getKPI(Equipment equipment, Date reference);
	
	
}
