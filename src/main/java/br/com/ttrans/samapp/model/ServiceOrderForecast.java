package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="INC_FORECAST",sequenceName="GEN_SOF_ID")
public class ServiceOrderForecast {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_FORECAST")
	private int sof_id;
	
	@ManyToOne
	@JoinColumn(name="sof_severity_id")
	private SeverityLevel severity;
	
	@ManyToOne
	@JoinColumn(name="sof_sub_system_id")	
	private SubSystem system;
	
	@Column
	private int sof_start_forecast;
	
	@Column
	private int sof_end_forecast;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";
	
	public ServiceOrderForecast(){}
	public ServiceOrderForecast(int sof_id, SeverityLevel severity,
			SubSystem system, int sof_start_forecast, int sof_end_forecast,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sof_id = sof_id;
		this.severity = severity;
		this.system = system;
		this.sof_start_forecast = sof_start_forecast;
		this.sof_end_forecast = sof_end_forecast;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}
	public int getSof_id() {
		return sof_id;
	}
	public void setSof_id(int sof_id) {
		this.sof_id = sof_id;
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
	public int getSof_start_forecast() {
		return sof_start_forecast;
	}
	public void setSof_start_forecast(int sof_start_forecast) {
		this.sof_start_forecast = sof_start_forecast;
	}
	public int getSof_end_forecast() {
		return sof_end_forecast;
	}
	public void setSof_end_forecast(int sof_end_forecast) {
		this.sof_end_forecast = sof_end_forecast;
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
