package br.com.ttrans.samapp.library;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum YesNo {
	YES("S"), NO("N");

	private static final Map<String, YesNo> lookup = new HashMap<String, YesNo>();

	static {
		for (YesNo s : EnumSet.allOf(YesNo.class))
			lookup.put(s.getCode(), s);
	}

	private String code;

	private YesNo(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static YesNo get(String code) {
		return lookup.get(code);
	}

}
