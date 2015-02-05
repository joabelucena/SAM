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
@Table(name="Status_Rules")
@SequenceGenerator(name="INC_STATUS_RULES",sequenceName="GEN_SRU_ID")
public class StatusRule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_STATUS_RULES")
	private int sru_id;
	
	@ManyToOne
	@JoinColumn(name="sru_role_id")
	private Role role;
		
	@ManyToOne
	@JoinColumn(name="sru_cur_status_id")
	private ServiceOrderStatus curstatus;
	
	@ManyToOne
	@JoinColumn(name="sru_nxt_status_id")
	private ServiceOrderStatus nxtstatus;
	
	@Column
	private String sru_log_remark;
	
	@Column(updatable=false)
	private String usr_insert;
	
	@Column(insertable=false)
	private String usr_update;
	
	@Column(columnDefinition="char(1)")
	private String deleted="";
	
	public StatusRule(){}
	public StatusRule(int sru_id, Role role, ServiceOrderStatus curstatus,
			ServiceOrderStatus nxtstatus, String sru_log_remark,
			String usr_insert, String usr_update, String deleted) {
		super();
		this.sru_id = sru_id;
		this.role = role;
		this.curstatus = curstatus;
		this.nxtstatus = nxtstatus;
		this.sru_log_remark = sru_log_remark;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public int getSru_id() {
		return sru_id;
	}

	public void setSru_id(int sru_id) {
		this.sru_id = sru_id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ServiceOrderStatus getCurstatus() {
		return curstatus;
	}

	public void setCurstatus(ServiceOrderStatus curstatus) {
		this.curstatus = curstatus;
	}

	public ServiceOrderStatus getNxtstatus() {
		return nxtstatus;
	}

	public void setNxtstatus(ServiceOrderStatus nxtstatus) {
		this.nxtstatus = nxtstatus;
	}

	public String getSru_log_remark() {
		return sru_log_remark;
	}

	public void setSru_log_remark(String sru_log_remark) {
		this.sru_log_remark = sru_log_remark;
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
