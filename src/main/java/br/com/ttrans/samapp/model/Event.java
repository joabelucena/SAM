package br.com.ttrans.samapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class has no relations because this one has to accept any incoming entry.
 * 
 * @author Joabe
 * 
 */
@Entity
@Table(name="events")
@SequenceGenerator(name = "INC_EVENTS", sequenceName="GEN_EVE_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	@Id
	@Column(name="eve_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_EVENTS")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="eve_equipment_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Equipment equipment = new Equipment();
	
	@ManyToOne
	@JoinColumn(name="eve_alarm_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Alarm alarm; 
	
	@ManyToOne
	@JoinColumn(name="eve_oper_state_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private OperationalState state;
	
	@Column(name="eve_datetime")
	private Date datetime;

	@Transient
	@Column(insertable=false, updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String date;
	
	@Transient
	@Column(insertable=false, updatable=false)
	@DateTimeFormat(pattern="hh:mm:ss")
	private String time;
	
	@Column(name="eve_solv_user")
	private String solvUser;
	
	@Column(name="eve_solv_date")
	private Date solvDate;
	
	@Column(name="eve_reco_user")
	private String recoUser;
	
	@Column(name="eve_reco_date")
	private Date recoDate;
	
	@Column(name="eve_site")
	private String site;

	@Column(name="eve_model")
	private String model;

	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public Event(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public OperationalState getState() {
		return state;
	}

	public void setState(OperationalState state) {
		this.state = state;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSolvUser() {
		return solvUser;
	}

	public void setSolvUser(String solvUser) {
		this.solvUser = solvUser;
	}

	public Date getSolvDate() {
		return solvDate;
	}

	public void setSolvDate(Date solvDate) {
		this.solvDate = solvDate;
	}

	public String getRecoUser() {
		return recoUser;
	}

	public void setRecoUser(String recoUser) {
		this.recoUser = recoUser;
	}

	public Date getRecoDate() {
		return recoDate;
	}

	public void setRecoDate(Date recoDate) {
		this.recoDate = recoDate;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
