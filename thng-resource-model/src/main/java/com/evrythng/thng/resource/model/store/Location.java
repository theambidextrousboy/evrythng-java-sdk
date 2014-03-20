package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * 
 * Model representation for <em>locations</em>.
 * 
 **/
public class Location extends TemporalResourceModel implements Locatable {

	private String place;

	private Double latitude;
	private Double longitude;
	private GeoJsonPoint position;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.longitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public GeoJsonPoint getPosition() {
		return position;
	}

	public void setPosition(GeoJsonPoint position) {
		this.position = position;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Location [place=" + place + ", latitude=" + latitude + ", longitude=" + longitude + ", position=" + position + ", id=" + id + ", createdAt=" + createdAt + ", customFields="
				+ customFields + "]";
	}
}
