package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Alarms")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alarm {
	
	@Id
	@Column(name="alm_id")
	private String id;
	
	@Column(name="alm_description")
	private String desc;
	
	@ManyToOne
	@JoinColumn(name = "alm_norm_id")
	private Alarm normAlarm; 
	
	@ManyToOne
	@JoinColumn(name = "alm_group_id")
	private AlarmGroup group;
	
	@ManyToOne
	@JoinColumn(name = "alm_type_id")
	private AlarmType type;
	
	@ManyToOne
	@JoinColumn(name = "alm_model_id")
	private EquipmentModel model;
	
	@ManyToOne
	@JoinColumn(name = "alm_severity_id")
	private SeverityLevel severity;
	
	@Column(name="alm_counter_inc")
	private String counterInc;
	
	//Pode ser Manualizado manualmente (Y-Sim/N-Nao)
	@Column(name="alm_norm_man")
	private String manNorm;
	
	//Eh Normalizador (Y-Sim/N-Nao)
	@Column(name="alm_norm_alm")
	private String isNorm;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public Alarm(){}
	public Alarm(String id){
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Alarm getNormAlarm() {
		return normAlarm;
	}
	public void setNormAlarm(Alarm normAlarm) {
		this.normAlarm = normAlarm;
	}
	public AlarmGroup getGroup() {
		return group;
	}
	public void setGroup(AlarmGroup group) {
		this.group = group;
	}
	public AlarmType getType() {
		return type;
	}
	public void setType(AlarmType type) {
		this.type = type;
	}
	public EquipmentModel getModel() {
		return model;
	}
	public void setModel(EquipmentModel model) {
		this.model = model;
	}
	public SeverityLevel getSeverity() {
		return severity;
	}
	public void setSeverity(SeverityLevel severity) {
		this.severity = severity;
	}
	public String getCounterInc() {
		return counterInc;
	}
	public void setCounterInc(String counterInc) {
		this.counterInc = counterInc;
	}
	public String getManNorm() {
		return manNorm;
	}
	public void setManNorm(String manNorm) {
		this.manNorm = manNorm;
	}
	public String getIsNorm() {
		return isNorm;
	}
	public void setIsNorm(String isNorm) {
		this.isNorm = isNorm;
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