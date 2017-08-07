package br.com.ttrans.samapp.model;

import java.util.Date;

public class KPI {

	private Equipment equipment;

	private Double totalTime;

	private Double brokenTime;

	private int breakCount;

	private Date from;

	private Date to;

	public KPI() {
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Double getTotalTime() {
		return this.totalTime;
	}

	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}

	public Double getBrokenTime() {
		return brokenTime;
	}

	public void setBrokenTime(Double brokenTime) {
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
	 * MTBF (Mean Time Between Failures) - Calculation function
	 * 
	 * @return MTBF value
	 */
	public Double getMTBF() {
		return this.breakCount == 0f ? 0f : (this.totalTime - this.brokenTime)
				/ this.breakCount;
	}

	/**
	 * MTTR (Mean Time To Repair) - Calculation function
	 * 
	 * @return MTTR value
	 */
	public Double getMTTR() {
		return this.breakCount == 0f ? 0f : (this.brokenTime) / this.breakCount;
	}

}
