package br.com.ttrans.samapp.model;

import java.util.Date;

public class KPI {
	
	private Equipment equipment;
	
	private Float totalTime;
	
	private Float brokenTime;
	
	private int breakCount;
	
	private Date from;
	
	private Date to;
	
	public KPI() {}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Float getTotalTime(){
		return this.totalTime;
	}
	
	public void setTotalTime(Float totalTime){
		this.totalTime = totalTime;
	}

	public Float getBrokenTime() {
		return brokenTime;
	}

	public void setBrokenTime(Float brokenTime) {
		this.brokenTime = brokenTime;
	}

	public int getBreakCount() {
		return breakCount;
	}

	public void setBreakCount(int breakCount) {
		this.breakCount = breakCount;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
	
	/**
	 * MTBF (Mean Time Between Failures) calculation function 
	 * 
	 * @return MTBF value
	 */
	public Float getMTBF(){
		return this.breakCount == 0f ? 0f : (this.totalTime - this.brokenTime) / this.breakCount;
	}
	
	/**
	 * MTTR (Mean Time To Repair) calculation function
	 * 
	 * @return MTTR value
	 */
	public Float getMTTR(){
		return this.breakCount == 0f ? 0f : (this.brokenTime) / this.breakCount;
	}
		
}
