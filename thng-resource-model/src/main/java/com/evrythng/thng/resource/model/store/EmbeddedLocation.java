/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Location to be used within a parent model.
 * 
 **/
public class EmbeddedLocation implements Locatable {

	private String place;

	private Double latitude;
	private Double longitude;
	private GeoJsonPoint position;
	private Map<String, String> customFields;

	public EmbeddedLocation() {
	}

	public EmbeddedLocation(String place, Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.setPosition(new GeoJsonPoint(latitude, longitude));
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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

	public Map<String, String> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, String> customFields) {
		this.customFields = customFields;
	}

	public static EmbeddedLocation copyFrom(Locatable source) {
		EmbeddedLocation loc = new EmbeddedLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public String toString() {
		return "EmbeddedLocation [place=" + place + ", latitude=" + latitude + ", longitude=" + longitude + ", position=" + position + ", customFields=" + customFields + ", getPlace()=" + getPlace()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude() + ", getPosition()=" + getPosition() + ", getCustomFields()=" + getCustomFields() + "]";
	}
}
