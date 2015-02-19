package br.com.ttrans.samapp.model;

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
	private String equ_id;

	@Column
	private String equ_fixed_asset;

	@Column
	private String equ_service_tag;

	@Column
	private String equ_ip;

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
	private String equ_solv_type;

	@Column
	private String equ_warranty;

	@Column
	private String equ_counter_qt;

	@Column
	private String equ_oid;

	@Column
	private String equ_mtbf_prev;

	@Column
	private String equ_mtbf_calc;

	@Column
	private String equ_mtbf_manf;

	@Column
	private String equ_install_date;

	@Column
	private String equ_manufacture_date;

	@Column
	private String equ_acquired_date;

	@Column
	private String equ_remark;

	@Column(updatable = false)
	private String usr_insert;

	@Column(insertable = false)
	private String usr_update;

	@Column(columnDefinition = "char(1)")
	private String deleted = "";

	public Equipment(){}
	public Equipment(String id){
		this.equ_id = id;
	}
	public Equipment(String equ_id, String equ_fixed_asset,
			String equ_service_tag, String equ_ip, EquipmentType type,
			EquipmentModel model, EquipmentManufacturer manufacturer,
			Site site, Counter counter, SubSystem system,
			String equ_solv_type, String equ_warranty, String equ_counter_qt,
			String equ_oid, String equ_mtbf_prev, String equ_mtbf_calc,
			String equ_mtbf_manf, String equ_install_date,
			String equ_manufacture_date, String equ_acquired_date,
			String equ_remark, String usr_insert, String usr_update,
			String deleted) {
		super();
		this.equ_id = equ_id;
		this.equ_fixed_asset = equ_fixed_asset;
		this.equ_service_tag = equ_service_tag;
		this.equ_ip = equ_ip;
		this.type = type;
		this.model = model;
		this.manufacturer = manufacturer;
		this.site = site;
		this.counter = counter;
		this.system = system;
		this.equ_solv_type = equ_solv_type;
		this.equ_warranty = equ_warranty;
		this.equ_counter_qt = equ_counter_qt;
		this.equ_oid = equ_oid;
		this.equ_mtbf_prev = equ_mtbf_prev;
		this.equ_mtbf_calc = equ_mtbf_calc;
		this.equ_mtbf_manf = equ_mtbf_manf;
		this.equ_install_date = equ_install_date;
		this.equ_manufacture_date = equ_manufacture_date;
		this.equ_acquired_date = equ_acquired_date;
		this.equ_remark = equ_remark;
		this.usr_insert = usr_insert;
		this.usr_update = usr_update;
		this.deleted = deleted;
	}

	public String getEqu_id() {
		return equ_id;
	}

	public void setEqu_id(String equ_id) {
		this.equ_id = equ_id;
	}

	public String getEqu_fixed_asset() {
		return equ_fixed_asset;
	}

	public void setEqu_fixed_asset(String equ_fixed_asset) {
		this.equ_fixed_asset = equ_fixed_asset;
	}

	public String getEqu_service_tag() {
		return equ_service_tag;
	}

	public void setEqu_service_tag(String equ_service_tag) {
		this.equ_service_tag = equ_service_tag;
	}

	public String getEqu_ip() {
		return equ_ip;
	}

	public void setEqu_ip(String equ_ip) {
		this.equ_ip = equ_ip;
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

	public String getEqu_solv_type() {
		return equ_solv_type;
	}

	public void setEqu_solv_type(String equ_solv_type) {
		this.equ_solv_type = equ_solv_type;
	}

	public String getEqu_warranty() {
		return equ_warranty;
	}

	public void setEqu_warranty(String equ_warranty) {
		this.equ_warranty = equ_warranty;
	}

	public String getEqu_counter_qt() {
		return equ_counter_qt;
	}

	public void setEqu_counter_qt(String equ_counter_qt) {
		this.equ_counter_qt = equ_counter_qt;
	}

	public String getEqu_oid() {
		return equ_oid;
	}

	public void setEqu_oid(String equ_oid) {
		this.equ_oid = equ_oid;
	}

	public String getEqu_mtbf_prev() {
		return equ_mtbf_prev;
	}

	public void setEqu_mtbf_prev(String equ_mtbf_prev) {
		this.equ_mtbf_prev = equ_mtbf_prev;
	}

	public String getEqu_mtbf_calc() {
		return equ_mtbf_calc;
	}

	public void setEqu_mtbf_calc(String equ_mtbf_calc) {
		this.equ_mtbf_calc = equ_mtbf_calc;
	}

	public String getEqu_mtbf_manf() {
		return equ_mtbf_manf;
	}

	public void setEqu_mtbf_manf(String equ_mtbf_manf) {
		this.equ_mtbf_manf = equ_mtbf_manf;
	}

	public String getEqu_install_date() {
		return equ_install_date;
	}

	public void setEqu_install_date(String equ_install_date) {
		this.equ_install_date = equ_install_date;
	}

	public String getEqu_manufacture_date() {
		return equ_manufacture_date;
	}

	public void setEqu_manufacture_date(String equ_manufacture_date) {
		this.equ_manufacture_date = equ_manufacture_date;
	}

	public String getEqu_acquired_date() {
		return equ_acquired_date;
	}

	public void setEqu_acquired_date(String equ_acquired_date) {
		this.equ_acquired_date = equ_acquired_date;
	}

	public String getEqu_remark() {
		return equ_remark;
	}

	public void setEqu_remark(String equ_remark) {
		this.equ_remark = equ_remark;
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
