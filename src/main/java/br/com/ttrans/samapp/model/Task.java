package br.com.ttrans.samapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Task_Monitor_Header")
//@SequenceGenerator(name="INC_TASK_HEADER",sequenceName="GEN_TMH_ID")
public class Task {

	@Id
	@Column(name="tmh_id")
//	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_TASK_HEADER")
	private String id = new String();
	
	@Column(name="tmh_desc")
	private String desc = new String();
	
	@Column(name="tmh_active")
	private String active = new String();
	
	@ManyToOne
	@JoinColumn(name="tmh_alarm_id")
	private Alarm alarm = new Alarm();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="task_equipment",
			joinColumns=@JoinColumn(name="task_id"),
			inverseJoinColumns=@JoinColumn(name="equipment_id"))
	@OrderBy(clause="equipment_id")
	private Set<Equipment> equipments = new HashSet<Equipment>();

//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "task", targetEntity = TaskCondition.class)
//	@JsonManagedReference(value="task")
//	private List<TaskCondition> conditions = new ArrayList<TaskCondition>();
	
	@Column(updatable=false, name = "usr_insert")
	private String insert = new String();
	
	@Column(insertable=false, name = "usr_update")
	private String update = new String();

	public Task(){}
	
	public Task(String id, String desc, String active, Alarm alarm, Set<Equipment> equipments, String insert,
			String update) {
		super();
		this.id = id;
		this.desc = desc;
		this.active = active;
		this.alarm = alarm;
		this.equipments = equipments;
		this.insert = insert;
		this.update = update;
	}



//	public Task(String id, String desc, String active, Alarm alarm, List<Equipment> equipments,
//			List<TaskCondition> conditions, String insert, String update) {
//		super();
//		this.id = id;
//		this.desc = desc;
//		this.active = active;
//		this.alarm = alarm;
//		this.equipments = equipments;
//		this.conditions = conditions;
//		this.insert = insert;
//		this.update = update;
//	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
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

//	public List<TaskCondition> getConditions() {
//		return conditions;
//	}
//
//	public void setConditions(List<TaskCondition> conditions) {
//		this.conditions = conditions;
//	}

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
