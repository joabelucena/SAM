package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Alarms_Filter")
@SequenceGenerator(name="INC_ALARMS_FILTER",sequenceName="GEN_AFI_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmFilter {
	
	@Id
	@Column(name="afi_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_ALARMS_FILTER")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="afi_alarm_id")
	private Alarm alarm;
	
	@ManyToOne
	@JoinColumn(name="afi_equipment_id")	
	private Equipment equipment;	
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public AlarmFilter(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	
}
