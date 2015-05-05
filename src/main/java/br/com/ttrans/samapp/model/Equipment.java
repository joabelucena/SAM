package br.com.ttrans.samapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Equipments")
public class Equipment {

	@Id
	@Column(name="equ_id")
	private String id;

	@Column(name="equ_fixed_asset")
	private String fixedAsset;

	@Column(name="equ_service_tag")
	private String service_tag;

	@Column(name="equ_ip")
	private String ip;
	
	@ManyToOne
	@JoinColumn(name = "equ_type_id")
	private EquipmentType type;

	@ManyToOne
	@JoinColumn(name = "equ_model_id")
	private EquipmentModel model;

	@ManyToOne
	@JoinColumn(name = "equ_manufacturer_id")
	private EquipmentManufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "equ_site_id")
	private Site site;

	@ManyToOne
	@JoinColumn(name = "equ_counter_type_id")
	private Counter counter;

	@ManyToOne
	@JoinColumn(name = "equ_system_id")
	private SubSystem system;

	@Column
	private String equ_warranty;

	@Column(name="equ_counter_qt")
	private String counterQt;

	@Column(name="equ_oid")
	private String oid;

	@Column(name="equ_mtbf_prev")
	private Double mtbfPrev;

	@Column(name="equ_mtbf_calc")
	private Double mtbfCalc;

	@Column(name="equ_mtbf_manf")
	private Double mtbfManf;

	@Column(name="equ_install_date")
	private Date installDate;

	@Column(name="equ_manufacture_date")
	private Date manufactureDate;

	@Column(name="equ_acquired_date")
	private Date acquiredDate;

	@Column(name="equ_remark")
	private String remark;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;

	public Equipment(){}
	public Equipment(String id){
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFixedAsset() {
		return fixedAsset;
	}
	public void setFixedAsset(String fixedAsset) {
		this.fixedAsset = fixedAsset;
	}
	public String getService_tag() {
		return service_tag;
	}
	public void setService_tag(String service_tag) {
		this.service_tag = service_tag;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public EquipmentType getType() {
		return type;
	}
	public void setType(EquipmentType type) {
		this.type = type;
	}
	public EquipmentModel getModel() {
		return model;
	}
	public void setModel(EquipmentModel model) {
		this.model = model;
	}
	public EquipmentManufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(EquipmentManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Counter getCounter() {
		return counter;
	}
	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	public SubSystem getSystem() {
		return system;
	}
	public void setSystem(SubSystem system) {
		this.system = system;
	}
	public String getEqu_warranty() {
		return equ_warranty;
	}
	public void setEqu_warranty(String equ_warranty) {
		this.equ_warranty = equ_warranty;
	}
	public String getCounterQt() {
		return counterQt;
	}
	public void setCounterQt(String counterQt) {
		this.counterQt = counterQt;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Double getMtbfPrev() {
		return mtbfPrev;
	}
	public void setMtbfPrev(Double mtbfPrev) {
		this.mtbfPrev = mtbfPrev;
	}
	public Double getMtbfCalc() {
		return mtbfCalc;
	}
	public void setMtbfCalc(Double mtbfCalc) {
		this.mtbfCalc = mtbfCalc;
	}
	public Double getMtbfManf() {
		return mtbfManf;
	}
	public void setMtbfManf(Double mtbfManf) {
		this.mtbfManf = mtbfManf;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public Date getAcquiredDate() {
		return acquiredDate;
	}
	public void setAcquiredDate(Date acquiredDate) {
		this.acquiredDate = acquiredDate;
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
}
