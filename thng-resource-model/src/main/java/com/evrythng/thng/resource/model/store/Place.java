package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

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
	private GeoJsonPoint location;
	private AddressInfo address;

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

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}
}