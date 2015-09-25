package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Service_Order_Status")
@SequenceGenerator(name="INC_SERVICE_ORDER_STATUS",sequenceName="GEN_SOS_ID")
public class ServiceOrderStatus {
	
	@Id
	@Column(name="sos_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator="INC_SERVICE_ORDER_STATUS")
	private int id;
	
	@Column(name="sos_description")
	private String desc;
	
	@Column(name="sos_log_remark")
	private String remark;
	
	@OneToOne
	@JoinColumn(name="sos_feat_id", referencedColumnName = "id")
	private SystemFeature feature;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public ServiceOrderStatus(){}
	public ServiceOrderStatus(String desc){
		super();
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public SystemFeature getFeature() {
		return feature;
	}
	public void setFeature(SystemFeature feature) {
		this.feature = feature;
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
