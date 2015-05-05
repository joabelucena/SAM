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
@Table(name = "Sites")
@SequenceGenerator(name = "INC_SITE", sequenceName = "GEN_SIT_ID")
public class Site {

	@Id
	@Column(name="sit_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SITE")
	private int id;

	@Column(name="sit_description")
	private String desc;

	@Column(name="sit_shortname")
	private String shortname;

	@ManyToOne
	@JoinColumn(name = "sit_parent_id")
	private Site parent;

	@ManyToOne
	@JoinColumn(name = "sit_type_id")
	private SiteType type;

	@ManyToOne
	@JoinColumn(name = "sit_station_id")
	private ServiceStation station;

	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;


	public Site() {}


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


	public String getShortname() {
		return shortname;
	}


	public void setShortname(String shortname) {
		this.shortname = shortname;
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


	public ServiceStation getStation() {
		return station;
	}


	public void setStation(ServiceStation station) {
		this.station = station;
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