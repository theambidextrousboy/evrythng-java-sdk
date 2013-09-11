package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJSON;

/**
 * Model representation for a <em>place</em>
 * @author colin
 *
 */
public class Place extends DurableResourceModel {
	private String name;
	private String description;
	private String icon;
	private GeoJSON location;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public GeoJSON getLocation() {
		return location;
	}

	public void setLocation(GeoJSON location) {
		this.location = location;
	}
}