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
@Table(name="Task_Monitor_Items")
@SequenceGenerator(name="INC_TASK_ITEMS",sequenceName="GEN_TMI_ID")
public class TaskCondition {

	@Id
	@Column(name="tmi_id")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="INC_TASK_ITEMS")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="tmi_task_id")
	private Task task;
	
	@Column(name="tmi_seq")
	private String seq;
	
	@Column(name="tmi_logic_op")
	private String logicOper;
	
	@Column(name="tmi_field")
	private String field; 
	
	@Column(name="tmi_relational_op")
	private String relOper;
	
	@Column(name="tmi_value")
	private int value;

	public TaskCondition(){}
	
	public TaskCondition(int id, Task task, String seq,
			String logicOper, String field, String relOper, int value) {
		super();
		this.id = id;
		this.task = task;
		this.seq = seq;
		this.logicOper = logicOper;
		this.field = field;
		this.relOper = relOper;
		this.value = value;
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

}
