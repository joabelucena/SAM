package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alarms")
public class Alarm {
	
	@Id
	private String alm_id;
	
	@Column
	private String alm_description;
	
	@ManyToOne
	@JoinColumn(name = "alm_parent_id")
	private Alarm alarm; 
	
	@ManyToOne
	@JoinColumn(name = "alm_group_id")
	private AlarmGroup group;
	
	@ManyToOne
	@JoinColumn(name = "alm_type_id")
	private AlarmType type;
	
	@ManyToOne
	@JoinColumn(name = "alm_model_id")
	private EquipmentModel model;
	
	@ManyToOne
	@JoinColumn(name = "alm_severity_id")
	private SeverityLevel severity;
	
	@Column
	private int alm_counter_inc;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";
	
	public Alarm(){}
	public Alarm(String alm_id){
		super();
		this.alm_id = alm_id;
	}
	public Alarm(String alm_id, String alm_description, Alarm alarm,
			AlarmGroup group, AlarmType type, EquipmentModel model,
			SeverityLevel severity, int alm_counter_inc, String usr_insert,
			String usr_update, String deleted) {
		super();
		this.alm_id = alm_id;
		this.alm_description = alm_description;
		this.alarm = alarm;
		this.group = group;
		this.type = type;
		this.model = model;
		this.severity = severity;
		this.alm_counter_inc = alm_counter_inc;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getAlm_id() {
		return alm_id;
	}

	public void setAlm_id(String alm_id) {
		this.alm_id = alm_id;
	}

	public String getAlm_description() {
		return alm_description;
	}

	public void setAlm_description(String alm_description) {
		this.alm_description = alm_description;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public AlarmGroup getGroup() {
		return group;
	}

	public void setGroup(AlarmGroup group) {
		this.group = group;
	}

	public AlarmType getType() {
		return type;
	}

	public void setType(AlarmType type) {
		this.type = type;
	}

	public EquipmentModel getModel() {
		return model;
	}

	public void setModel(EquipmentModel model) {
		this.model = model;
	}

	public SeverityLevel getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityLevel severity) {
		this.severity = severity;
	}

	public int getAlm_counter_inc() {
		return alm_counter_inc;
	}

	public void setAlm_counter_inc(int alm_counter_inc) {
		this.alm_counter_inc = alm_counter_inc;
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