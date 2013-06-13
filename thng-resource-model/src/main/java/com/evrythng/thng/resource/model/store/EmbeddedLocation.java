/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Location to be used within a parent model.
 * 
 * @author Michel Yerly (my)
 **/
public class EmbeddedLocation implements ILocation {

	private String place;
	private Double latitude;
	private Double longitude;

	public EmbeddedLocation() {
	}

	public EmbeddedLocation(String place, Double latitude, Double longitude) {
		this.place = place;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public static EmbeddedLocation copyFrom(EmbeddedLocation source) {
		return new EmbeddedLocation(source.place, source.latitude, source.longitude);
	}

	public static EmbeddedLocation copyFrom(ILocation source) {
		EmbeddedLocation loc = new EmbeddedLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}
}
