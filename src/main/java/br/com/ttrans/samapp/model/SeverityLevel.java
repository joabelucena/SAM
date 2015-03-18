package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Severity_level")
@SequenceGenerator(name = "INC_SEVERITY_LEVEL", sequenceName = "GEN_SLE_ID")
public class SeverityLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SEVERITY_LEVEL")
	private String sle_id;

	@Column
	private String sle_description;

	@Column(updatable = false)
	private String usr_insert;

	@Column(insertable = false)
	private String usr_update;

	@Column(columnDefinition = "char(1)")
	private String deleted = "";

	public SeverityLevel() {
	}

	public SeverityLevel(String sle_id, String sle_description,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sle_id = sle_id;
		this.sle_description = sle_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getSle_id() {
		return sle_id;
	}

	public void setSle_id(String sle_id) {
		this.sle_id = sle_id;
	}

	public String getSle_description() {
		return sle_description;
	}

	public void setSle_description(String sle_description) {
		this.sle_description = sle_description;
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