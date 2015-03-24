package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Service_Station")
@SequenceGenerator(name = "INC_SERVICE_STATION", sequenceName = "GEN_SST_ID")
public class ServiceStation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_SERVICE_STATION") 
	private int sst_id;
	
	@Column
	private String sst_description;
	

	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public ServiceStation(){}
	public ServiceStation(int sst_id, String sst_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sst_id = sst_id;
		this.sst_description = sst_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSst_id() {
		return sst_id;
	}

	public void setSst_id(int sst_id) {
		this.sst_id = sst_id;
	}

	public String getSst_description() {
		return sst_description;
	}

	public void setSst_description(String sst_description) {
		this.sst_description = sst_description;
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
