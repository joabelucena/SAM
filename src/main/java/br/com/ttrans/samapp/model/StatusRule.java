package br.com.ttrans.samapp.model;

import java.io.Serializable;

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
public class StatusRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="sru_id")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_STATUS_RULES")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="sru_role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="sru_cur_status_id")
	private ServiceOrderStatus curstatus;
	
	@ManyToOne
	@JoinColumn(name="sru_nxt_status_id")
	private ServiceOrderStatus nxtstatus;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public StatusRule(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
