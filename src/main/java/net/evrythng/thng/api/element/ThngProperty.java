package net.evrythng.thng.api.element;

import net.evrythng.thng.api.json.JSONModel;

import org.json.JSONException;

public class ThngProperty<K> extends JSONModel {
	
	public ThngProperty(String title, K text) throws JSONException {
		this.setTitle(title);
		this.setText(text);
	}
	
	public String getTitle() throws JSONException {
		return this.getString("title");
	}

	public void setTitle(String title) throws JSONException {
		this.put("title", title);
	}

	public K getText() throws JSONException {
		return (K) this.get("text"); // TODO: check this!
	}

	public void setText(K text) throws JSONException {
		this.put("text", text);
	}
}