package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Service_Order_Jobs")
public class ServiceOrderJob {
	
	@Id
	private String soj_id;
	
	@Column
	private String soj_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public ServiceOrderJob(){};
	public ServiceOrderJob(String soj_id, String soj_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.soj_id = soj_id;
		this.soj_description = soj_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getSoj_id() {
		return soj_id;
	}

	public void setSoj_id(String soj_id) {
		this.soj_id = soj_id;
	}

	public String getSoj_description() {
		return soj_description;
	}

	public void setSoj_description(String soj_description) {
		this.soj_description = soj_description;
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
