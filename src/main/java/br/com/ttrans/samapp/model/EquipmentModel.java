package br.com.ttrans.samapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Equipments_Model")
@SequenceGenerator(name="INC_EQUIPMENT_MODEL",sequenceName="GEN_EMO_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentModel {
	
	@Id
	@Column(name="emo_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENT_MODEL")
	private int id;
	
	@Column(name="emo_description")
	private String desc;
	
	@ManyToOne
	@JoinColumn(name = "emo_protocol_id")
	private EquipmentProtocol protocol;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="doc_model_id", referencedColumnName = "emo_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Document> documents;
	
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public EquipmentModel(){}

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

	public EquipmentProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(EquipmentProtocol protocol) {
		this.protocol = protocol;
	}
	
	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
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
