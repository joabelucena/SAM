package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Equipments_Protocol")
@SequenceGenerator(name="INC_EQUIPMENTS_PROTOCOL",sequenceName="GEN_EPR_ID")
public class EquipmentProtocol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_EQUIPMENTS_PROTOCOL")
	private int epr_id;
	
	@Column
	private String epr_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";
		
	public EquipmentProtocol() {}
	public EquipmentProtocol(int epr_id, String epr_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.epr_id = epr_id;
		this.epr_description = epr_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}


	public int getEpr_id() {
		return epr_id;
	}

	public void setEpr_id(int epr_id) {
		this.epr_id = epr_id;
	}

	public String getEpr_description() {
		return epr_description;
	}

	public void setEpr_description(String epr_description) {
		this.epr_description = epr_description;
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
