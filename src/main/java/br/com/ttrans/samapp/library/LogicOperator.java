package br.com.ttrans.samapp.library;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum LogicOperator {

	AND("E"), OR("OU");

	private static final Map<String, LogicOperator> lookup = new HashMap<String, LogicOperator>();

	static {
		for (LogicOperator s : EnumSet.allOf(LogicOperator.class))
			lookup.put(s.getCode(), s);
	}

	private String code;

	private LogicOperator(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static LogicOperator get(String code) {
		return lookup.get(code);
	}
}
