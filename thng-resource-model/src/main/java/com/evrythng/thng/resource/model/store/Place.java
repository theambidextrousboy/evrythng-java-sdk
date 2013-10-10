package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJson;

/**
 * Model representation for a <em>place</em>
 * 
 * @author colin
 * 
 */
public class Place extends DurableResourceModel {

	private String name;
	private String description;
	private String icon;
	private GeoJson location;

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

	public GeoJson getLocation() {
		return location;
	}

	public void setLocation(GeoJson location) {
		this.location = location;
	}
}