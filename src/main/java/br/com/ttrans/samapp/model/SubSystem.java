package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sub_System")
public class SubSystem {
	
	@Id
	private String ssy_id;
	
	@Column
	private String ssy_description;

	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public SubSystem(){}
	public SubSystem(String ssy_id, String ssy_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.ssy_id = ssy_id;
		this.ssy_description = ssy_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getSsy_id() {
		return ssy_id;
	}

	public void setSsy_id(String ssy_id) {
		this.ssy_id = ssy_id;
	}

	public String getSsy_description() {
		return ssy_description;
	}

	public void setSsy_description(String ssy_description) {
		this.ssy_description = ssy_description;
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
