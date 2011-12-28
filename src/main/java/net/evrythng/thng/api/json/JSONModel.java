package net.evrythng.thng.api.json;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * {@link JSONObject} decorator.
 * 
 * @author almeidap
 *
 */
public abstract class JSONModel {
	
	protected JSONObject model = new JSONObject();
		
	@Override
	public String toString() {
		return model.toString();
	}
	
	public JSONObject toJSONObject() {
		return model;
	}
	
	public Object get(String key) throws JSONException {
		return this.model.get(key);
	}
	
	public String getString(String key) throws JSONException {
		return this.model.getString(key);
	}
	
	public void put(String key, Object value) throws JSONException {
		this.model.put(key, value);
	}
}
