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
@Table(name="Equipments_Type")
@SequenceGenerator(name="INC_EQUIPMENT_TYPE",sequenceName="GEN_ETY_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentType {
	
	@Id
	@Column(name="ety_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENT_TYPE")
	private int id;
	
	@Column(name="ety_description")
	private String desc;
	
	@Column(name="ety_daily_hours")
	private Float dailyHours;

	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public EquipmentType(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Float getDailyHours() {
		return dailyHours;
	}

	public void setDailyHours(Float dailyHours) {
		this.dailyHours = dailyHours;
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
