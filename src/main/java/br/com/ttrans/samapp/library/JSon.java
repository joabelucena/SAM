package br.com.ttrans.samapp.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.service.RoleService;

@Component
public class JSon {

	@Autowired
	private RoleService service;
	
	/*
	 * public String toJson(List<Site> site) throws JSONException{
	 * 
	 * JSONArray array = new JSONArray(); JSONObject json = new JSONObject();
	 * 
	 * for(int i = 0; i < site.size(); i++){ array.put(toJson(site.get(i))); }
	 * 
	 * json.put("success", true); json.put("children", array);
	 * 
	 * return json.toString(); }
	 */

	public String toJson(List<Menu> menu, List roles) throws JSONException {

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		List<Role> rolesList = new ArrayList<Role>(roles.size());
		
		for(int i = 0; i < roles.size();i++){
			rolesList.add(service.findByDesc(roles.get(i).toString()));
		}
		
		
		//menu.get(1).getRoles().contains()
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getChildren().size() > 0) {
				for(Role role : rolesList){
					if(menu.get(i).getRoles().contains(role)){
						array.put(toJson(menu.get(i),rolesList));						
						break;
					}
				}		
			}
		}
		
		// json.put("items", true);
		json.put("items", array);

		return json.toString();
	}

	protected static JSONObject toJson(Menu menu, List<Role> roles) throws JSONException {
		
		boolean lJSon = true;
		
		/*
		//Percorre Roles do Menu
		for(Role role : roles){
			if(menu.getRoles().contains(role)){
				lJSon = true;
				break;
			}
		}
		*/

		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		if (menu != null && lJSon) {

			// comum
			json.put("iconCls", menu.getIconCls());
			if (menu.getParent() != null)
				json.put("menu_id", menu.getParent().getId());
				json.put("id", menu.getId());

			// pai
			if (menu.getChildren().size() > 0) {

				json.put("title", menu.getText());
				
				Iterator it = menu.getChildren().iterator();
				
				while(it.hasNext()){
					
					Menu child = (Menu)it.next();
					
					for(Role role : roles){
						if(child.getRoles().contains(role)){
							array.put(toJson(child , roles));
							break;
						}
					}
				}


			// filho
			} else {
				json.put("classname", menu.getClassName());
				json.put("text", menu.getText());
			}
		}

		if (array.length() > 0) {
			json.put("items", array);
		}
		
		return json;
	}
}