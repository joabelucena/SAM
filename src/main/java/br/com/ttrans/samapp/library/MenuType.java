package br.com.ttrans.samapp.library;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MenuType {
	
	PANEL_RENDER("PN"), SPAGO_REPORT("SR"), EXTERNAL_URL("EU"), INTERNAL_URL("IU");

	private static final Map<String, MenuType> lookup = new HashMap<String, MenuType>();

	static {
		for (MenuType s : EnumSet.allOf(MenuType.class))
			lookup.put(s.getCode(), s);
	}

	private String code;

	private MenuType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static MenuType get(String code) {
		return lookup.get(code);
	}

}