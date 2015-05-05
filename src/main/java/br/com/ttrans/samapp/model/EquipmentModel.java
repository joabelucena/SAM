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
