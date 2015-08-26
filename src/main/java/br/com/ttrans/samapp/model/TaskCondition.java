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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Task_Monitor_Items")
@SequenceGenerator(name="INC_TASK_ITEMS",sequenceName="GEN_TMI_ID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskCondition {

	@Id
	@Column(name="tmi_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_TASK_ITEMS")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="tmi_task_id")
	@JsonBackReference(value="conditions")
	private Task task;
	
	@Column(name="tmi_seq")
	private String seq;
	
	@Column(name="tmi_logic_op")
	private String logicOper;
	
	@Column(name="tmi_type")
	private String type;
	
	@Column(name="tmi_field")
	private String field;
	
	@Column(name="tmi_relational_op")
	private String relOper;
	
	@Column(name="tmi_value")
	private int value;
	
	@Column(updatable=false, name = "usr_insert")
	private String insert;
	
	@Column(insertable=false, name = "usr_update")
	private String update;
	
	public TaskCondition(){
		super();
	}
	
	public TaskCondition(int id, Task task, String seq, String logicOper,
			String type, String field, String relOper, int value,
			String insert, String update) {
		super();
		this.id = id;
		this.task = task;
		this.seq = seq;
		this.logicOper = logicOper;
		this.type = type;
		this.field = field;
		this.relOper = relOper;
		this.value = value;
		this.insert = insert;
		this.update = update;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getLogicOper() {
		return logicOper;
	}

	public void setLogicOper(String logicOper) {
		this.logicOper = logicOper;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getRelOper() {
		return relOper;
	}

	public void setRelOper(String relOper) {
		this.relOper = relOper;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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
