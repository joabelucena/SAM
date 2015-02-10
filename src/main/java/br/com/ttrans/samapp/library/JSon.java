package br.com.ttrans.samapp.library;

import java.util.List;

import org.hibernate.QueryException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Site;

public class JSon {

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

	public String toJson(List<Menu> menu) throws JSONException {

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();

		for (int i = 0; i < menu.size(); i++) {
			if(menu.get(i).getChildren().size() > 0){
				array.put(toJson(menu.get(i)));
				//System.out.println(menu.get(i).getText());
			}
		}

		//json.put("items", true);
		json.put("items", array);

		return json.toString();
	}

	protected static JSONObject toJson(Menu menu) throws JSONException {

		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();

		if (menu != null) {
			
			json.put("iconCls", menu.getIconCls());
			if(menu.getParent() != null) json.put("menu_id", menu.getParent().getId());
			json.put("id", menu.getId());

			if (menu.getChildren().size() > 0) {
				json.put("title", menu.getText());
				for (int i = 0; i < menu.getChildren().size(); i++) {
					// EoF
					array.put(toJson(menu.getChildren().get(i)));
				}
				
			} else {
				json.put("text", menu.getText());
			}
		}

		if (array.length() > 0) {
			json.put("items", array);
		}

		return json;
	}
}

/*
 * 
 * { [ { "id": 1, "name": "Phil", "leaf": true }, { "id": 2, "name": "Nico",
 * "expanded": true, "children": [ { "id": 3, "name": "Mitchell", "leaf": true }
 * ]}, { "id": 4, "name": "Sue", "loaded": true } ]
 * 
 * { [ {"id:" 1, "name": "SAO VICENTE", "expanded":true ,"children":[ {"id:" 2,
 * "name": "PLATAFORMA B", "expanded":true, "children":[ { "id": 17, "name":
 * "CABINE DE SEGURANCA", "leaf": true }, { "id": 18, "name":
 * "CABINE DE GUARITA" , "leaf": true } ]} ]} ] }
 * 
 * }
 * 
 * http://stackoverflow.com/questions/20470077/how-to-populate-store-with-recursive
 * -object-extjs http://docs.sencha.com/extjs/5.0/components/trees.html
 * 
 * 
 * 
 * public class JSon {
 * 
 * public String toJson(List<Site> sites) throws JSONException{
 * 
 * return JsonIt(sites.get(0),sites.get(0).getChildren()).toString(); }
 * 
 * protected JSONObject eachRecursive(Site site){
 * 
 * JSONObject JSonObj = new JSONObject(); JSONArray JSonArray = new JSONArray();
 * 
 * if(ResObj.Rels.length > 0) {
 * 
 * var childNodeModel = { name: ResObj.F + " " +ResObj.L, text: ResObj.F + " "
 * +ResObj.L, leaf: false, children: [] };
 * 
 * var childNode = parent.appendChild(childNodeModel);
 * 
 * for(var i=0; i<ResObj.Rels.length; i++){ eachRecursive(ResObj.Rels[i],
 * childNode); } } else { var childNodeModel = { name: ResObj.F + " " +ResObj.L,
 * text: ResObj.F + " " + ResObj.L, leaf: true };
 * parent.appendChild(childNodeModel);
 * 
 * } } }
 */