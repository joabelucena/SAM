package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="parameters")
@Entity
@SequenceGenerator(name = "INC_PARAMETER", sequenceName = "GEN_PAR_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameter {
	
	@Id
	@Column(name="par_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_PARAMETER")
	private int id;
	
	@Size(max=10)
	@Column(name="par_name")
	private String name;
	
	@Size(max=1)
	@Column(name="par_type")
	private String type;
	
	@Column(name="par_value")
	private String value;
	
	@Column(name="par_desc")
	private String desc;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public Parameter(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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