package br.com.ttrans.samapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="events")
@SequenceGenerator(name = "INC_EVENTS", sequenceName="GEN_EVE_ID")
public class Event {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EVENTS")
	private int eve_id;
	
	@NotEmpty
	@NotNull
	@Column
	private String eve_equipment_id;
	
	@NotEmpty
	@NotNull
	@Column
	private String eve_alarm_id;
	
	@Column
	private String eve_oper_state_id;
	
	@Column
	private Date eve_datetime;

	@Column(insertable=false, updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String eve_date;
	
	@Column(insertable=false, updatable=false)
	@DateTimeFormat(pattern="hh:mm:ss")
	private String eve_time;
	
	@Column
	private String eve_solv_user;
	
	@Column
	private Date eve_solv_date;
	
	@Column
	private String eve_reco_user;
	
	@Column
	private Date eve_reco_date;
	
	@Column
	private String eve_site;

	@Column
	private String eve_model;

	@Column
	private String usr_insert;

	@Column
	private String usr_update;

	@Column(columnDefinition="char(1)")
	private String deleted="";

	public Event(){}
	public Event(int eve_id, String eve_equipment_id, String eve_alarm_id,
			String eve_oper_state_id, Date eve_datetime,
			String eve_solv_user, Date eve_solv_date, String eve_reco_user,
			Date eve_reco_date, String eve_site, String eve_model,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.eve_id = eve_id;
		this.eve_equipment_id = eve_equipment_id;
		this.eve_alarm_id = eve_alarm_id;
		this.eve_oper_state_id = eve_oper_state_id;
		this.eve_datetime = eve_datetime;
		this.eve_solv_user = eve_solv_user;
		this.eve_solv_date = eve_solv_date;
		this.eve_reco_user = eve_reco_user;
		this.eve_reco_date = eve_reco_date;
		this.eve_site = eve_site;
		this.eve_model = eve_model;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getEve_id() {
		return eve_id;
	}

	public void setEve_id(int eve_id) {
		this.eve_id = eve_id;
	}

	public String getEve_equipment_id() {
		return eve_equipment_id;
	}

	public void setEve_equipment_id(String eve_equipment_id) {
		this.eve_equipment_id = eve_equipment_id;
	}

	public String getEve_alarm_id() {
		return eve_alarm_id;
	}

	public void setEve_alarm_id(String eve_alarm_id) {
		this.eve_alarm_id = eve_alarm_id;
	}

	public String getEve_oper_state_id() {
		return eve_oper_state_id;
	}
	public void setEve_oper_state_id(String eve_oper_state_id) {
		this.eve_oper_state_id = eve_oper_state_id;
	}

	public Date getEve_datetime() {
		return eve_datetime;
	}
	
	public void setEve_datetime(Date eve_datetime) {
		this.eve_datetime = eve_datetime;
	}

	public String getEve_solv_user() {
		return eve_solv_user;
	}

	public void setEve_solv_user(String eve_solv_user) {
		this.eve_solv_user = eve_solv_user;
	}

	public Date getEve_solv_date() {
		return eve_solv_date;
	}

	public void setEve_solv_date(Date eve_solv_date) {
		this.eve_solv_date = eve_solv_date;
	}

	public String getEve_reco_user() {
		return eve_reco_user;
	}

	public void setEve_reco_user(String eve_reco_user) {
		this.eve_reco_user = eve_reco_user;
	}

	public Date getEve_reco_date() {
		return eve_reco_date;
	}

	public void setEve_reco_date(Date eve_reco_date) {
		this.eve_reco_date = eve_reco_date;
	}

	public String getEve_site() {
		return eve_site;
	}

	public void setEve_site(String eve_site) {
		this.eve_site = eve_site;
	}

	public String getEve_model() {
		return eve_model;
	}

	public void setEve_model(String eve_model) {
		this.eve_model = eve_model;
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
	public String getEve_date() {
		return eve_date;
	}
	public void setEve_date(String eve_date) {
		this.eve_date = eve_date;
	}
	public String getEve_time() {
		return eve_time;
	}
	public void setEve_time(String eve_time) {
		this.eve_time = eve_time;
	}

}
