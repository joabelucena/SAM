package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Alarms")
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
	private int counterInc;
	
	//Pode ser Manualizado manualmente (1-Sim/2-Nao)
	@Column(name="alm_norm_man")
	private int manNorm;
	
	//Eh Normalizador (1-Sim/2-Nao)
	@Column(name="alm_norm_alm")
	private int isNorm;
	
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
	public int getCounterInc() {
		return counterInc;
	}
	public void setCounterInc(int counterInc) {
		this.counterInc = counterInc;
	}
	public int getManNorm() {
		return manNorm;
	}
	public void setManNorm(int manNorm) {
		this.manNorm = manNorm;
	}
	public int getIsNorm() {
		return isNorm;
	}
	public void setIsNorm(int isNorm) {
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