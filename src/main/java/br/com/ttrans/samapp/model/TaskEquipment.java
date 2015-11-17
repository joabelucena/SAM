package br.com.ttrans.samapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Many to many class + extra column for Equipments.
 * @author Joabe Lucena 
 */
@Entity
@Table(name="task_monitor_equipments")
@SequenceGenerator(name="INC_TASK_EQUIP",sequenceName="GEN_TME_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskEquipment {
	
	@Id
	@Column(name = "tme_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_TASK_EQUIP")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "tme_equipment_id")
	private Equipment equipment;
	
	@Column(name="tme_cutoff_date")
	private Date proccess;
	
	public TaskEquipment(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getProccess() {
		return proccess;
	}

	public void setProccess(Date proccess) {
		this.proccess = proccess;
	}
	
}
