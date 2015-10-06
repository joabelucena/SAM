package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Documents_Type")
@SequenceGenerator(name="INC_DOCUMENT_TYPE",sequenceName="GEN_DTY_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentType {
	
	@Id
	@Column(name="dty_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_DOCUMENT_TYPE")
	private int id;
	
	@Column(name="dty_description")
	private String desc;
	
	@Column(name="dty_extension")
	private String extension;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public DocumentType(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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
