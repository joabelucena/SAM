package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Alarms_Group")
@SequenceGenerator(name="INC_ALARMS_GROUP",sequenceName="GEN_AGR_ID")
public class AlarmGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_ALARMS_GROUP")
	private int agr_id;
	
	@Column
	private String agr_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public AlarmGroup(){}
	public AlarmGroup(int agr_id, String agr_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.agr_id = agr_id;
		this.agr_description = agr_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getAgr_id() {
		return agr_id;
	}

	public void setAgr_id(int agr_id) {
		this.agr_id = agr_id;
	}

	public String getAgr_description() {
		return agr_description;
	}

	public void setAgr_description(String agr_description) {
		this.agr_description = agr_description;
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
