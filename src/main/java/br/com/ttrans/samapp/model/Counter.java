package br.com.ttrans.samapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alarm_Counter")
public class Counter {

	@EmbeddedId
	private CounterId id;

	@Column(name="aco_counter")
	private int counter = 1;
	
	@Column(name="aco_last_reset")
	private Date resetDate;
	
	public Counter(){}
	
	public Counter(CounterId id) {
		super();
		this.id = id;
	}


	public int getCounter() {
		return counter;
	}

	public CounterId getId() {
		return id;
	}

	public void setId(CounterId id) {
		this.id = id;
	}

	public Date getResetDate() {
		return resetDate;
	}

	public void setResetDate(Date resetDate) {
		this.resetDate = resetDate;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	@Embeddable
	public static class CounterId implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@ManyToOne
		@JoinColumn(name="aco_alarm_id")
		private Alarm alarm;
		
		@ManyToOne
		@JoinColumn(name="aco_equipment_id")
		private Equipment equipment;
		
		public CounterId(){}
		
		public CounterId(Alarm alarm, Equipment equipment) {
			super();
			this.alarm = alarm;
			this.equipment = equipment;
		}

		public Alarm getAlarm() {
			return alarm;
		}

		public void setAlarm(Alarm alarm) {
			this.alarm = alarm;
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
			result = prime * result + ((alarm == null) ? 0 : alarm.hashCode());
			result = prime * result
					+ ((equipment == null) ? 0 : equipment.hashCode());
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
			CounterId other = (CounterId) obj;
			if (alarm == null) {
				if (other.alarm != null)
					return false;
			} else if (!alarm.equals(other.alarm))
				return false;
			if (equipment == null) {
				if (other.equipment != null)
					return false;
			} else if (!equipment.equals(other.equipment))
				return false;
			return true;
		}
		
	}
}

