package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Sites_Type")
@SequenceGenerator(name="INC_SITES_TYPE",sequenceName="GEN_STY_ID")
public class SiteType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_SITES_TYPE")
	private int sty_id;
	
	@Column
	private String sty_description;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public SiteType() {}
	public SiteType(int sty_id, String sty_description, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.sty_id = sty_id;
		this.sty_description = sty_description;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}
	public int getSty_id() {
		return sty_id;
	}
	public void setSty_id(int sty_id) {
		this.sty_id = sty_id;
	}
	public String getSty_description() {
		return sty_description;
	}
	public void setSty_description(String sty_description) {
		this.sty_description = sty_description;
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