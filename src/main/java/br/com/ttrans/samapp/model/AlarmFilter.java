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

@Entity
@Table(name="Alarms_Filter")
@SequenceGenerator(name="INC_ALARMS_FILTER",sequenceName="GEN_AFI_ID")
public class AlarmFilter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_ALARMS_FILTER")
	private int afi_id;
	
	@ManyToOne
	@JoinColumn(name="afi_alarm_id")
	private Alarm alarm;
	
	@ManyToOne
	@JoinColumn(name="afi_equipment_id")	
	private Equipment equipment;	
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public AlarmFilter(){}
	public AlarmFilter(int afi_id, Alarm alarm, Equipment equipment,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.afi_id = afi_id;
		this.alarm = alarm;
		this.equipment = equipment;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getAfi_id() {
		return afi_id;
	}

	public void setAfi_id(int afi_id) {
		this.afi_id = afi_id;
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

	public String getUsr_insert() {
		return usr_insert;
	}

	public void setUsr_insert(String usr_insert) {
		this.usr_insert = usr_insert;
	}

	public String getUsr_update() {
		return usr_update;
	}

	public void setUsr_update(String usr_update) {
		this.usr_update = usr_update;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
