package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Alarms_Type")
@SequenceGenerator(name="INC_ALARM_TYPE",sequenceName="GEN_ALM_ID")
public class AlarmType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_ALARM_TYPE")
	private int aty_id;
	
	@Column
	private String aty_description;	
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public AlarmType(){}
	public AlarmType(int aty_id, String aty_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.aty_id = aty_id;
		this.aty_description = aty_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getAty_id() {
		return aty_id;
	}

	public void setAty_id(int aty_id) {
		this.aty_id = aty_id;
	}

	public String getAty_description() {
		return aty_description;
	}

	public void setAty_description(String aty_description) {
		this.aty_description = aty_description;
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
