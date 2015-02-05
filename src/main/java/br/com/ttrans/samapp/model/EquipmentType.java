package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Equipments_Type")
@SequenceGenerator(name="INC_EQUIPMENT_TYPE",sequenceName="GEN_ETY_ID")
public class EquipmentType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENT_TYPE")
	private int ety_id;
	
	@Column
	private String ety_description;

	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public EquipmentType(){}
	public EquipmentType(int ety_id, String ety_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.ety_id = ety_id;
		this.ety_description = ety_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getEty_id() {
		return ety_id;
	}

	public void setEty_id(int ety_id) {
		this.ety_id = ety_id;
	}

	public String getEty_description() {
		return ety_description;
	}

	public void setEty_description(String ety_description) {
		this.ety_description = ety_description;
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
