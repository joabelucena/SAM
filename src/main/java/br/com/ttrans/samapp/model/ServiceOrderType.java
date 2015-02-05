package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Service_Order_Type")
@SequenceGenerator(name="INC_SERVICE_ORDER_TYPE",sequenceName="GEN_SOT_ID")
public class ServiceOrderType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "INC_SERVICE_ORDER_TYPE")
	private int sot_id;
	
	@Column
	private String sot_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public ServiceOrderType(){}
	public ServiceOrderType(int sot_id, String sot_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sot_id = sot_id;
		this.sot_description = sot_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSot_id() {
		return sot_id;
	}

	public void setSot_id(int sot_id) {
		this.sot_id = sot_id;
	}

	public String getSot_description() {
		return sot_description;
	}

	public void setSot_description(String sot_description) {
		this.sot_description = sot_description;
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
