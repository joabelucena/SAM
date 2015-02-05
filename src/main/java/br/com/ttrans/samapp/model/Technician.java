package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Technicians")
public class Technician {
	
	@Id
	private String tec_id;
	
	@Column
	private String tec_name;
	
	@ManyToOne
	@JoinColumn(name = "tec_site_id")
	private Site site;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public Technician(){}
	public Technician(String tec_id, String tec_name, Site site,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.tec_id = tec_id;
		this.tec_name = tec_name;
		this.site = site;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getTec_id() {
		return tec_id;
	}

	public void setTec_id(String tec_id) {
		this.tec_id = tec_id;
	}

	public String getTec_name() {
		return tec_name;
	}

	public void setTec_name(String tec_name) {
		this.tec_name = tec_name;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
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
