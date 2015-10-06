package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Documents")
@SequenceGenerator(name="INC_DOCUMENT",sequenceName="GEN_DOC_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
	
	@Id
	@Column(name="doc_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_DOCUMENT")
	private int id;
	
	@Column(name="doc_description")
	private String desc;
	
	@Column(name="doc_url")
	private String url;
	
	@ManyToOne
	@JoinColumn(name="doc_type_id")
	private DocumentType type;
	
	@ManyToOne
	@JoinColumn(name="doc_equipment_id")
	@JsonBackReference
	private Equipment equipment;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public Document(){}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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
