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
@SequenceGenerator(name="INC_FORECAST",sequenceName="GEN_SOF_ID")
@Table(name="Service_Order_Forecast")
public class ServiceOrderForecast {
	
	@Id
	@Column(name="sof_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_FORECAST")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="sof_severity_id")
	private SeverityLevel severity;
	
	@ManyToOne
	@JoinColumn(name="sof_sub_system_id")	
	private SubSystem system;
	
	@Column(name="sof_start_forecast")
	private int startForecast;
	
	@Column(name="sof_end_forecast")
	private int endForecast;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public ServiceOrderForecast(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SeverityLevel getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityLevel severity) {
		this.severity = severity;
	}

	public SubSystem getSystem() {
		return system;
	}

	public void setSystem(SubSystem system) {
		this.system = system;
	}

	public int getStartForecast() {
		return startForecast;
	}

	public void setStartForecast(int startForecast) {
		this.startForecast = startForecast;
	}

	public int getEndForecast() {
		return endForecast;
	}

	public void setEndForecast(int endForecast) {
		this.endForecast = endForecast;
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
