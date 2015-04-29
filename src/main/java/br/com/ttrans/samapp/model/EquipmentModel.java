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

@Entity
@Table(name="Equipments_Model")
@SequenceGenerator(name="INC_EQUIPMENT_MODEL",sequenceName="GEN_EMO_ID")
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

	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

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
