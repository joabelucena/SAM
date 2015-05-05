package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Operational_State")
public class OperationalState {

	@Id
	@Column(name="ost_id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ost_model_id")
	private EquipmentModel model;
	
	@Column(name="ost_description")
	private String desc;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public OperationalState(){}
	
	public OperationalState(String id){
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EquipmentModel getModel() {
		return model;
	}
	public void setModel(EquipmentModel model) {
		this.model = model;
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
