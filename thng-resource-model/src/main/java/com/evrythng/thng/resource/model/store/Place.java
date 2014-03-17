package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPolygon;

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
	private GeoJsonPoint position;
	private AddressInfo address;

	//TODO: Implement 'area' in client-side at a later date
	//private GeoJsonPolygon area;

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

	public GeoJsonPoint getPosition() {
		return position;
	}

	public void setPosition(GeoJsonPoint position) {
		this.position = position;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

	/*public GeoJsonPolygon getArea() {
		return area;
	}

	public void setArea(GeoJsonPolygon area) {
		this.area = area;
	}*/
}