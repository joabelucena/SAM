package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Service_Order_Status")
@SequenceGenerator(name="INC_SERVICE_ORDER_STATUS",sequenceName="GEN_SOS_ID")
public class ServiceOrderStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="INC_SERVICE_ORDER_STATUS")
	private int sos_id;
	
	@Column
	private String sos_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public ServiceOrderStatus(){}
	public ServiceOrderStatus(String sos_description){
		super();
		this.sos_description = sos_description;
	}
	public ServiceOrderStatus(int sos_id, String sos_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sos_id = sos_id;
		this.sos_description = sos_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSos_id() {
		return sos_id;
	}

	public void setSos_id(int sos_id) {
		this.sos_id = sos_id;
	}

	public String getSos_description() {
		return sos_description;
	}

	public void setSos_description(String sos_description) {
		this.sos_description = sos_description;
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
