package br.com.ttrans.samapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Service_Order")
@SequenceGenerator(name = "INC_SERVICE_ORDER", sequenceName = "GEN_SOR_ID")
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SERVICE_ORDER")
	private int sor_id;

	@ManyToOne
	@JoinColumn(name = "sor_type_id")
	private ServiceOrderType type;

	@ManyToOne
	@JoinColumn(name = "sor_status_id")
	private ServiceOrderStatus status;

	@ManyToOne
	@JoinColumn(name = "sor_event_id")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "sor_parent_id")
	private ServiceOrder parent;

	/*
	 * Occurrences Log
	 */
	@OneToMany(mappedBy = "serviceorder", targetEntity = ServiceOrderOccurrence.class, fetch = FetchType.LAZY)
	private List<ServiceOrderOccurrence> occurrences;

	@OneToMany(mappedBy = "serviceorder", targetEntity = ServiceOrderLog.class, fetch = FetchType.LAZY)
	private List<ServiceOrderLog> log;

	@ManyToOne
	@JoinColumn(name = "sor_technician_id")
	private Technician technician;

	@ManyToOne
	@JoinColumn(name = "sor_priority_id")
	private SeverityLevel severity;

	@ManyToOne
	@JoinColumn(name = "sor_equipment_id")
	private Equipment equipment;

	@Column
	private Date sor_start_forecast;

	@Column
	private Date sor_start;

	@Column
	private Date sor_end_forecast;

	@Column
	private Date sor_end;

	@Column
	private String sor_remarks;

	@Column
	private String sor_equipment_stop;

	@Column(updatable = false)
	private String usr_insert;

	@Column(insertable = false)
	private String usr_update;

	@Column(columnDefinition = "char(1)")
	private String deleted = "";

	public ServiceOrder() {
	}

	public ServiceOrder(int sor_id, ServiceOrderType type,
			ServiceOrderStatus status, Event event, ServiceOrder parent,
			Technician technician, SeverityLevel severity, Equipment equipment,
			Date sor_start_forecast, Date sor_start, Date sor_end_forecast,
			Date sor_end, String sor_remarks, String sor_equipment_stop,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sor_id = sor_id;
		this.type = type;
		this.status = status;
		this.event = event;
		this.parent = parent;
		this.technician = technician;
		this.severity = severity;
		this.equipment = equipment;
		this.sor_start_forecast = sor_start_forecast;
		this.sor_start = sor_start;
		this.sor_end_forecast = sor_end_forecast;
		this.sor_end = sor_end;
		this.sor_remarks = sor_remarks;
		this.sor_equipment_stop = sor_equipment_stop;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSor_id() {
		return sor_id;
	}

	public void setSor_id(int sor_id) {
		this.sor_id = sor_id;
	}

	public ServiceOrderType getType() {
		return type;
	}

	public void setType(ServiceOrderType type) {
		this.type = type;
	}

	public ServiceOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceOrderStatus status) {
		this.status = status;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public ServiceOrder getParent() {
		return parent;
	}

	public void setParent(ServiceOrder parent) {
		this.parent = parent;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public SeverityLevel getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityLevel severity) {
		this.severity = severity;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getSor_start_forecast() {
		return sor_start_forecast;
	}

	public void setSor_start_forecast(Date sor_start_forecast) {
		this.sor_start_forecast = sor_start_forecast;
	}

	public Date getSor_start() {
		return sor_start;
	}

	public void setSor_start(Date sor_start) {
		this.sor_start = sor_start;
	}

	public Date getSor_end_forecast() {
		return sor_end_forecast;
	}

	public void setSor_end_forecast(Date sor_end_forecast) {
		this.sor_end_forecast = sor_end_forecast;
	}

	public Date getSor_end() {
		return sor_end;
	}

	public void setSor_end(Date sor_end) {
		this.sor_end = sor_end;
	}

	public String getSor_remarks() {
		return sor_remarks;
	}

	public void setSor_remarks(String sor_remarks) {
		this.sor_remarks = sor_remarks;
	}

	public String getSor_equipment_stop() {
		return sor_equipment_stop;
	}

	public void setSor_equipment_stop(String sor_equipment_stop) {
		this.sor_equipment_stop = sor_equipment_stop;
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
