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
	private String ost_id;
	
	@ManyToOne
	@JoinColumn(name = "ost_model_id")
	private EquipmentModel model;
	
	@Column
	private String ost_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public OperationalState(){}
	public OperationalState(String id){
		this.ost_id = id;
	}
	public OperationalState(String ost_id, EquipmentModel model,
			String ost_description, String usr_insert, String usr_update,
			String deleted) {
		super();
		this.ost_id = ost_id;
		this.model = model;
		this.ost_description = ost_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getOst_id() {
		return ost_id;
	}

	public void setOst_id(String ost_id) {
		this.ost_id = ost_id;
	}

	public EquipmentModel getModel() {
		return model;
	}

	public void setModel(EquipmentModel model) {
		this.model = model;
	}

	public String getOst_description() {
		return ost_description;
	}

	public void setOst_description(String ost_description) {
		this.ost_description = ost_description;
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
