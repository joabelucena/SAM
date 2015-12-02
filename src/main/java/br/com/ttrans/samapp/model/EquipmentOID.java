package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Equipments_OID")
@SequenceGenerator(name="INC_EQUIPMENT_OID",sequenceName="GEN_EOI_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentOID {
	
	@Id
	@Column(name="eoi_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENT_OID")
	private int id;
	
	@Column(name="eoi_oid")
	private String oid;
	
	@Column(name="eoi_alarm_id")
	private String alarm;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public EquipmentOID(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOID() {
		return oid;
	}

	public void setOID(String oid) {
		this.oid = oid;
	}
	
	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
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
