package br.com.ttrans.samapp.model;

import java.io.Serializable;
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
public class ServiceOrderLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sol_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_SERVICE_ORDER_LOG") 
	private int id;
	
	@ManyToOne
	@JoinColumn(name="sol_service_order_id")
	private ServiceOrder serviceorder;
	
	@ManyToOne
	@JoinColumn(name="sol_pre_status_id")
	private ServiceOrderStatus prevstatus;
	
	@ManyToOne
	@JoinColumn(name="sol_cur_status_id")
	private ServiceOrderStatus curstatus;
	
	@Column(name="sol_user_id")
	private String userId;
	
	@Column(name="sol_datetime")
	private Date datetime;
	
	@Column(name="sol_remarks")
	private String remark;	
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public ServiceOrderLog(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
