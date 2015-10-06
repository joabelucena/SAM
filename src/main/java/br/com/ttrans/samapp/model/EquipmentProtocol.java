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
@Table(name="Equipments_Protocol")
@SequenceGenerator(name="INC_EQUIPMENTS_PROTOCOL",sequenceName="GEN_EPR_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentProtocol {
	
	@Id
	@Column(name="epr_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_EQUIPMENTS_PROTOCOL")
	private int id;
	
	@Column(name="epr_description")
	private String desc;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
		
	public EquipmentProtocol() {}

	public EquipmentProtocol(int id) {
		super();
		this.id = id;
	}

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
