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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Service_Order_Occurrences")
@SequenceGenerator(name = "INC_SERVICE_ORDER_OCCURRENCES", sequenceName = "GEN_SOO_ID")
public class ServiceOrderOccurrence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SERVICE_ORDER_OCCURRENCES")
	private int soo_id;

	@ManyToOne
	@JoinColumn(name = "soo_service_order_id")
	private ServiceOrder serviceorder;

	@ManyToOne
	@JoinColumn(name = "soo_service_id")
	private ServiceOrderJob service;

	@ManyToOne
	@JoinColumn(name = "soo_technician_id")
	private Technician technician;

	@Column
	private int soo_event_type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date soo_start;

	@Column
	private Date soo_end;

	@Column(updatable = false)
	private String usr_insert;

	@Column(insertable = false)
	private String usr_update;

	@Column(columnDefinition = "char(1)")
	private String deleted = "";

	public ServiceOrderOccurrence() {
	}

	public ServiceOrderOccurrence(int soo_id, ServiceOrder serviceorder,
			ServiceOrderJob service, Technician technician,
			int soo_event_type, Date soo_start, Date soo_end,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.soo_id = soo_id;
		this.serviceorder = serviceorder;
		this.service = service;
		this.technician = technician;
		this.soo_event_type = soo_event_type;
		this.soo_start = soo_start;
		this.soo_end = soo_end;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSoo_id() {
		return soo_id;
	}

	public void setSoo_id(int soo_id) {
		this.soo_id = soo_id;
	}

	public ServiceOrder getServiceorder() {
		return serviceorder;
	}

	public void setServiceorder(ServiceOrder serviceorder) {
		this.serviceorder = serviceorder;
	}

	public ServiceOrderJob getService() {
		return service;
	}

	public void setService(ServiceOrderJob service) {
		this.service = service;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public int getSoo_event_type() {
		return soo_event_type;
	}

	public void setSoo_event_type(int soo_event_type) {
		this.soo_event_type = soo_event_type;
	}

	public Date getSoo_start() {
		return soo_start;
	}

	public void setSoo_start(Date soo_start) {
		this.soo_start = soo_start;
	}

	public Date getSoo_end() {
		return soo_end;
	}

	public void setSoo_end(Date soo_end) {
		this.soo_end = soo_end;
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
