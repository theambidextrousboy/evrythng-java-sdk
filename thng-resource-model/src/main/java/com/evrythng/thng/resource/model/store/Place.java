package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Model representation for a <em>place</em>
 * 
 */
public class Place extends DurableResourceModel implements Positionable {

	private static final long serialVersionUID = 6579445453253811614L;

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

	@Override
	public Double getLatitude() {
		return position == null ? null : position.getLatitude();
	}

	@Override
	public void setLatitude(Double latitude) {
		if (position != null) {
			position.setLatitude(latitude);
		}
	}

	@Override
	public Double getLongitude() {
		return position == null ? null : position.getLongitude();
	}

	@Override
	public void setLongitude(Double longitude) {
		if (position != null) {
			position.setLongitude(longitude);
		}
	}
}