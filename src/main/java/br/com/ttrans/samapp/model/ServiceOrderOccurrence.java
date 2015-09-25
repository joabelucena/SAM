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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Service_Order_Occurrences")
@SequenceGenerator(name = "INC_SERVICE_ORDER_OCCURRENCES", sequenceName = "GEN_SOO_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceOrderOccurrence {

	@Id
	@Column(name="soo_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SERVICE_ORDER_OCCURRENCES")
	private int id;

	@ManyToOne
	@JoinColumn(name = "soo_service_id")
	private ServiceOrderJob service;

	@ManyToOne
	@JoinColumn(name = "soo_technician_id")
	private Technician technician;

	@Column(name="soo_event_type")
	private int type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="soo_start")
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="soo_end")
	private Date end;

	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public ServiceOrderOccurrence() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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
