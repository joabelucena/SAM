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

@Entity
@Table(name="Service_Order_Log")
@SequenceGenerator(name="INC_SERVICE_ORDER_LOG",sequenceName="GEN_SOL_ID")
public class ServiceOrderLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_SERVICE_ORDER_LOG") 
	private int sol_id;
	
	@ManyToOne
	@JoinColumn(name="sol_service_order_id")
	private ServiceOrder serviceorder;
	
	@ManyToOne
	@JoinColumn(name="sol_pre_status_id")
	private ServiceOrderStatus prevstatus;
	
	@ManyToOne
	@JoinColumn(name="sol_cur_status_id")
	private ServiceOrderStatus curstatus;
	
	@Column
	private String sol_user_id;
	
	@Column
	private Date sol_datetime;
	
	@Column
	private String sol_remarks;	
	
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";

	public ServiceOrderLog(){}
	
	public ServiceOrderLog(ServiceOrderStatus prevstatus, ServiceOrderStatus curstatus,
			String sol_user_id, Date sol_datetime, String sol_remarks,
			String usr_insert){
		super();
		this.prevstatus = prevstatus;
		this.curstatus = curstatus;
		this.sol_user_id = sol_user_id;
		this.sol_datetime = sol_datetime;
		this.sol_remarks = sol_remarks;
		this.usr_insert = usr_insert;
	}
	
	public ServiceOrderLog(int sol_id, ServiceOrder serviceorder,
			ServiceOrderStatus prevstatus, ServiceOrderStatus curstatus,
			String sol_user_id, Date sol_datetime, String sol_remarks,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sol_id = sol_id;
		this.serviceorder = serviceorder;
		this.prevstatus = prevstatus;
		this.curstatus = curstatus;
		this.sol_user_id = sol_user_id;
		this.sol_datetime = sol_datetime;
		this.sol_remarks = sol_remarks;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSol_id() {
		return sol_id;
	}

	public void setSol_id(int sol_id) {
		this.sol_id = sol_id;
	}

	public ServiceOrder getServiceorder() {
		return serviceorder;
	}

	public void setServiceorder(ServiceOrder serviceorder) {
		this.serviceorder = serviceorder;
	}

	public ServiceOrderStatus getPrevstatus() {
		return prevstatus;
	}

	public void setPrevstatus(ServiceOrderStatus prevstatus) {
		this.prevstatus = prevstatus;
	}

	public ServiceOrderStatus getCurstatus() {
		return curstatus;
	}

	public void setCurstatus(ServiceOrderStatus curstatus) {
		this.curstatus = curstatus;
	}

	public String getSol_user_id() {
		return sol_user_id;
	}

	public void setSol_user_id(String sol_user_id) {
		this.sol_user_id = sol_user_id;
	}

	public Date getSol_datetime() {
		return sol_datetime;
	}

	public void setSol_datetime(Date sol_datetime) {
		this.sol_datetime = sol_datetime;
	}

	public String getSol_remarks() {
		return sol_remarks;
	}

	public void setSol_remarks(String sol_remarks) {
		this.sol_remarks = sol_remarks;
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
