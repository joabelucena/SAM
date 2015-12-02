package br.com.ttrans.samapp.service;

import java.util.Date;
import java.util.List;

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
	public List<KPI> loadAll();
	
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
	 * @param d1 Initial date to analyze
	 * @param d2 End date to analyze
	 * @return An object containing the indicators
	 */
	public KPI getKPI(Equipment equipment, Date d1, Date d2);
	
	
}
