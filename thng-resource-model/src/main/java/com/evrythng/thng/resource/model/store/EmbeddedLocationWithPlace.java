/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Location to be used within a parent model.
 * 
 * @author Michel Yerly (my)
 **/
public class EmbeddedLocationWithPlace implements ILocationWithPlace {

	private String place;

	private Double latitude;
	private Double longitude;
	private GeoJsonPoint position;

	public EmbeddedLocationWithPlace() {
	}

	public EmbeddedLocationWithPlace(String place, Double latitude, Double longitude) {
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

	public static EmbeddedLocationWithPlace copyFrom(EmbeddedLocationWithPlace source) {
		return new EmbeddedLocationWithPlace(source.place, source.getLatitude(), source.getLongitude());
	}

	public static EmbeddedLocationWithPlace copyFrom(ILocationWithPlace source) {
		EmbeddedLocationWithPlace loc = new EmbeddedLocationWithPlace();
		loc.setLatitude(source.getLatitude());
		loc.setLongitude(source.getLongitude());
		loc.setPlace(source.getPlace());
		//LocationHelper.copy(source, loc);
		return loc;
	}
}
