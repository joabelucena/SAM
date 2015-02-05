package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Counter_Type")
@SequenceGenerator(name="INC_COUNTER_TYPE",sequenceName="GEN_CTY_ID")
public class Counter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_COUNTER_TYPE")
	private int cty_id;
	
	@Column
	private String cty_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public Counter(){}
	public Counter(int cty_id, String cty_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.cty_id = cty_id;
		this.cty_description = cty_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getCty_id() {
		return cty_id;
	}

	public void setCty_id(int cty_id) {
		this.cty_id = cty_id;
	}

	public String getCty_description() {
		return cty_description;
	}

	public void setCty_description(String cty_description) {
		this.cty_description = cty_description;
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
