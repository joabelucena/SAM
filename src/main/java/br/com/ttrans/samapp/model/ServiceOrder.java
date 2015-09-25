package br.com.ttrans.samapp.model;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Service_Order")
@SequenceGenerator(name = "INC_SERVICE_ORDER", sequenceName = "GEN_SOR_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceOrder {

	@Id
	@Column(name="sor_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_SERVICE_ORDER")
	private int id;

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="soo_service_order_id", referencedColumnName = "sor_id")
	@Cascade({CascadeType.SAVE_UPDATE})	
	private Set<ServiceOrderOccurrence> occurrences;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="sol_service_order_id", referencedColumnName = "sor_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<ServiceOrderLog> log;

	@ManyToOne
	@JoinColumn(name = "sor_technician_id")
	private Technician technician;

	@ManyToOne
	@JoinColumn(name = "sor_priority_id")
	private SeverityLevel priority;

	@ManyToOne
	@JoinColumn(name = "sor_equipment_id")
	private Equipment equipment;

	@Column(name="sor_start_forecast")
	private Date startForecast;

	@Column(name="sor_start")
	private Date start;

	@Column(name="sor_end_forecast")
	private Date endForecast;

	@Column(name="sor_end")
	private Date end;

	@Column(name="sor_remarks")
	private String remark;
	
	@Transient
	private String logRemark;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public ServiceOrder(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<ServiceOrderOccurrence> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Set<ServiceOrderOccurrence> occurrences) {
		this.occurrences = occurrences;
	}

	public Set<ServiceOrderLog> getLog() {
		return log;
	}

	public void setLog(Set<ServiceOrderLog> log) {
		this.log = log;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public SeverityLevel getPriority() {
		return priority;
	}

	public void setPriority(SeverityLevel priority) {
		this.priority = priority;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Date getStartForecast() {
		return startForecast;
	}

	public void setStartForecast(Date startForecast) {
		this.startForecast = startForecast;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEndForecast() {
		return endForecast;
	}

	public void setEndForecast(Date endForecast) {
		this.endForecast = endForecast;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
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
