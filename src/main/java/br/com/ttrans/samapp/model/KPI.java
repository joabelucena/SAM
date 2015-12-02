package br.com.ttrans.samapp.model;

import java.util.Date;

public class KPI {
	
	private Equipment equipment;
	
	private int totalTime;
	
	private int brokenTime;
	
	private int breakCount;
	
	private Date reference;
	
	public KPI() {
		super();
	}
	
	public KPI(Equipment equipment) {
		super();
		this.equipment = equipment;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getBrokenTime() {
		return brokenTime;
	}

	public void setBrokenTime(int brokenTime) {
		this.brokenTime = brokenTime;
	}

	public int getBreakCount() {
		return breakCount;
	}

	public void setBreakCount(int breakCount) {
		this.breakCount = breakCount;
	}

	public Date getReference() {
		return reference;
	}

	public void setReference(Date reference) {
		this.reference = reference;
	}
}
