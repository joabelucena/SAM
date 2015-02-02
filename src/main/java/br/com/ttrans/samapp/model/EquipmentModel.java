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
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EQUIPMENT_MODEL")
	private int emo_id;
	
	@Column
	private String emo_description;
	
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
	public EquipmentModel(int emo_id, String emo_description,
			EquipmentProtocol protocol, String usr_insert, String usr_update,
			String deleted) {
		super();
		this.emo_id = emo_id;
		this.emo_description = emo_description;
		this.protocol = protocol;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getEmo_id() {
		return emo_id;
	}

	public void setEmo_id(int emo_id) {
		this.emo_id = emo_id;
	}

	public String getEmo_description() {
		return emo_description;
	}

	public void setEmo_description(String emo_description) {
		this.emo_description = emo_description;
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
