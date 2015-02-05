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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Sites")
@SequenceGenerator(name = "INC_SITE", sequenceName = "GEN_SIT_ID")
public class Site {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SITE")
	private int sit_id;

	@Column
	private String sit_description;

	@Column
	private String sit_shortname;

	@ManyToOne
	@JoinColumn(name = "sit_parent_id")
	private Site parent;

	@ManyToOne
	@JoinColumn(name = "sit_type_id")
	private SiteType type;

	@Column(updatable = false)
	private String usr_insert;

	@Column(insertable = false)
	private String usr_update;

	@Column(columnDefinition = "char(1)")
	private String deleted = "";

	public Site() {}
	public Site(int sit_id, String sit_description, String sit_shortname,
			Integer sit_parent_id, SiteType type, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.sit_id = sit_id;
		this.sit_description = sit_description;
		this.sit_shortname = sit_shortname;
		this.type = type;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSit_id() {
		return sit_id;
	}

	public void setSit_id(int sit_id) {
		this.sit_id = sit_id;
	}

	public String getSit_description() {
		return sit_description;
	}

	public void setSit_description(String sit_description) {
		this.sit_description = sit_description;
	}

	public String getSit_shortname() {
		return sit_shortname;
	}

	public void setSit_shortname(String sit_shortname) {
		this.sit_shortname = sit_shortname;
	}

	public Site getParent() {
		return parent;
	}

	public void setParent(Site parent) {
		this.parent = parent;
	}

	public SiteType getType() {
		return type;
	}

	public void setType(SiteType type) {
		this.type = type;
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