package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Equipments_Manufacturer")
@SequenceGenerator(name="INC_EQUIPMENTS_MANUFACTURER",sequenceName="GEN_EMA_ID")
public class EquipmentManufacturer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENTS_MANUFACTURER")
	private int ema_id;
	
	@Column
	private String ema_description;	
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public EquipmentManufacturer(){}
	public EquipmentManufacturer(int ema_id, String ema_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.ema_id = ema_id;
		this.ema_description = ema_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getEma_id() {
		return ema_id;
	}

	public void setEma_id(int ema_id) {
		this.ema_id = ema_id;
	}

	public String getEma_description() {
		return ema_description;
	}

	public void setEma_description(String ema_description) {
		this.ema_description = ema_description;
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
