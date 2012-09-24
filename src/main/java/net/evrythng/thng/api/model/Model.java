/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.model;

import net.evrythng.thng.api.utils.JSONUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class Model {

	private String createdAt;

	public JSONObject toJSONObject() {
		return JSONUtils.toJSONObject(this);
	}
	
	public JSONArray toJSONArray() {
		return JSONUtils.toJSONArray(this);
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] " + this.toJSONObject();
	}
}
