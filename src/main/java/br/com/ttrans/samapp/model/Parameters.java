package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name="parameters")
@Entity
@SequenceGenerator(name = "INC_PARAMETER", sequenceName = "GEN_PAR_ID")
/*
@org.hibernate.annotations.Entity(	 dynamicInsert = true
									,dynamicUpdate = true , selectBeforeUpdate = true)
*/
public class Parameters {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_PARAMETER")
	private int par_id;
	
	@Size(max=10)
	@Column
	private String par_name;
	
	@Size(max=1)
	@Column
	private String par_type;
	
	@Column
	private String par_value;
	
	@Column
	private String par_desc;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";
	
	public Parameters(){}
	public Parameters(int par_id, String par_name, String par_type, String par_value, 
					 String par_desc, String usr_insert, String usr_update, String deleted){
		
		super();
		this.par_id = par_id;
		this.par_name = par_name;
		this.par_type = par_type;
		this.par_value = par_value;
		this.par_desc = par_desc;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}
	
	public int getPar_id() {
		return par_id;
	}
	public void setPar_id(int par_id) {
		this.par_id = par_id;
	}
	public String getPar_name() {
		return par_name;
	}
	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}
	public String getPar_type() {
		return par_type;
	}
	public void setPar_type(String par_type) {
		this.par_type = par_type;
	}
	public String getPar_value() {
		return par_value;
	}
	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}
	public String getPar_desc() {
		return par_desc;
	}
	public void setPar_desc(String par_desc) {
		this.par_desc = par_desc;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
	
	
}