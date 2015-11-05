package br.com.ttrans.samapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Many to many class + extra column for Equipments.
 * @author Joabe Lucena 
 */
@Entity
@Table(name="task_equipment")
@JsonIgnoreProperties(ignoreUnknown = true)
@AssociationOverrides({
	@AssociationOverride(name = "id.task", 
		joinColumns = @JoinColumn(name = "task_id")),
	@AssociationOverride(name = "id.equipment", 
		joinColumns = @JoinColumn(name = "equipment_id")) })
public class TaskEquipment {
	
	@EmbeddedId
	private TaskEquipmentId id;
	
	@Column(name="cutoff_date")
	private Date proccess;
	
	
	public TaskEquipmentId getId() {
		return id;
	}

	public void setId(TaskEquipmentId id) {
		this.id = id;
	}

	public Date getProccess() {
		return proccess;
	}

	public void setProccess(Date proccess) {
		this.proccess = proccess;
	}
	
	@Transient
	public Task getTask() {
		return this.getId().getTask();
	}

	public void setTask(Task task) {
		this.getId().setTask(task);
	}
	
	@Transient
	public Equipment getEquipment() {
		return this.getId().getEquipment();
	}

	public void setEquipment(Equipment equipment) {
		this.getId().setEquipment(equipment);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskEquipment other = (TaskEquipment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	/**
	 * Embeddable class for TaskEquipment id
	 * @author Joabe Lucena
	 *
	 */
	@Embeddable
	public static class TaskEquipmentId implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@ManyToOne
		private Task task;
		

		@ManyToOne
		private Equipment equipment;
		
		public TaskEquipmentId() {
			super();
		}

		public TaskEquipmentId(Task task, Equipment equipment) {
			super();
			this.task = task;
			this.equipment = equipment;
		}

		public Task getTask() {
			return task;
		}

		public void setTask(Task task) {
			this.task = task;
		}

		public Equipment getEquipment() {
			return equipment;
		}

		public void setEquipment(Equipment equipment) {
			this.equipment = equipment;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((equipment == null) ? 0 : equipment.hashCode());
			result = prime * result + ((task == null) ? 0 : task.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TaskEquipmentId other = (TaskEquipmentId) obj;
			if (equipment == null) {
				if (other.equipment != null)
					return false;
			} else if (!equipment.equals(other.equipment))
				return false;
			if (task == null) {
				if (other.task != null)
					return false;
			} else if (!task.equals(other.task))
				return false;
			return true;
		}
	}
	/**** FIM ID ****/
}
