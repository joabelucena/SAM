package br.com.ttrans.samapp.library;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.service.RoleService;

@SuppressWarnings("rawtypes")
@Component
public class JSon {

	@Autowired
	private RoleService service;
	
	public String toJson(Set<Menu> menus) throws JSONException {

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		Iterator i = menus.iterator();
		
		while(i.hasNext()){
			
			Menu menu = (Menu) i.next();

			//Processa somente os primeiros niveis de Menu
			if (menu.getParent() == null){
				array.put(toJson(menu));
			}
		}

		json.put("items", array);

		return json.toString();
	}

	protected static JSONObject toJson(Menu menu) throws JSONException {
		
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		if (menu != null) {

			// comum
			json.put("iconCls", menu.getIconCls());
			if (menu.getParent() != null)
				json.put("menu_id", menu.getParent().getId());
				json.put("id", menu.getId());

			// pai
			if (menu.getItems().size() > 0) {

				json.put("title", menu.getTitle());
				
				Iterator it = menu.getItems().iterator();
				
				while(it.hasNext()){
					
					Menu child = (Menu)it.next();
					
					array.put(toJson(child ));
					
				}

			// filho
			} else {
				json.put("classname", menu.getClassName());
				json.put("text", menu.getTitle());
				json.put("url", menu.getUrl());
				json.put("type", menu.getType().getCode());
			}
		}

		if (array.length() > 0) {
			json.put("items", array);
		}
		
		return json;
	}
}