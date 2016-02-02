package br.com.ttrans.samapp.service;

import java.util.Date;
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
//	public Map<String, KPI > loadAll();
	public Set<KPI> loadAll();
	
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
	 * @param from Initial date to consider for retrieving KPI
	 * @param to End date to consider for retrieving KPI
	 *  
	 */
	public KPI getKPI(Equipment equipment, Date from, Date to);
	
	
}
