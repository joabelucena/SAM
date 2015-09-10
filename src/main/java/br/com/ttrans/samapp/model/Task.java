package br.com.ttrans.samapp.model;

import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Task_Monitor_Header")
@SequenceGenerator(name="INC_TASK_HEADER",sequenceName="GEN_TMH_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

	@Id
	@Column(name="tmh_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_TASK_HEADER")
	private Integer id;
	
	@Column(name="tmh_desc")
	private String desc;
	
	@Column(name="tmh_active")
	private String active;
	
	@ManyToOne
	@JoinColumn(name="tmh_alarm_id")
	private Alarm alarm;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name="task_equipment",
			joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	private Set<Equipment> equipments;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="tmi_task_id", referencedColumnName = "tmh_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<TaskCondition> conditions;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public Task(){}

	public Task(Integer id, String desc, String active, Alarm alarm,
			Set<Equipment> equipments, Set<TaskCondition> conditions,
			String insert, String update) {
		super();
		this.id = id;
		this.desc = desc;
		this.active = active;
		this.alarm = alarm;
		this.equipments = equipments;
		this.conditions = conditions;
		this.insert = insert;
		this.update = update;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public Set<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Set<TaskCondition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<TaskCondition> conditions) {
		this.conditions = conditions;
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
