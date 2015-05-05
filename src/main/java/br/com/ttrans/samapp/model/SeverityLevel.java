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
	@Column(name="sle_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SEVERITY_LEVEL")
	private String id;

	@Column(name="sle_description")
	private String desc;

	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public SeverityLevel(String id){
		super();
		this.id = id;
	}
	public SeverityLevel() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInsert() {
		return insert;
	}
	public void setInsert(String insert) {
		this.insert = insert;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}

	
}