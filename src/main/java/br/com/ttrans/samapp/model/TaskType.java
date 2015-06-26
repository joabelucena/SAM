package br.com.ttrans.samapp.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TaskType{
	
	ALARM("AL"), ALARM_TYPE("AT"), MTBF("MT");

	private static final Map<String, TaskType> lookup = new HashMap<String, TaskType>();

	static {
		for (TaskType s : EnumSet.allOf(TaskType.class))
			lookup.put(s.getCode(), s);
	}

	private String code;

	private TaskType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static TaskType get(String code) {
		return lookup.get(code);
	}
}
