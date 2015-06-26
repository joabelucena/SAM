package br.com.ttrans.samapp.library;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RelationalOperator {
	
	EQUAL("=="), GREATER(">"), LESS("<"), GREATER_OR_EQUAL(">="), LESS_OR_EQUAL("<=");

	private static final Map<String, RelationalOperator> lookup = new HashMap<String, RelationalOperator>();

	static {
		for (RelationalOperator s : EnumSet.allOf(RelationalOperator.class))
			lookup.put(s.getCode(), s);
	}

	private String code;

	private RelationalOperator(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static RelationalOperator get(String code) {
		return lookup.get(code);
	}

}