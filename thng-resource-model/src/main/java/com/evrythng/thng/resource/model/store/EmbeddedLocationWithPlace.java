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
public class EmbeddedLocationWithPlace extends EmbeddedLocation implements ILocationWithPlace {

	private String place;

	public EmbeddedLocationWithPlace() {
	}

	public EmbeddedLocationWithPlace(String place, Double latitude, Double longitude) {
		super(latitude, longitude);
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public static EmbeddedLocationWithPlace copyFrom(EmbeddedLocationWithPlace source) {
		return new EmbeddedLocationWithPlace(source.place, source.getLatitude(), source.getLongitude());
	}

	public static EmbeddedLocationWithPlace copyFrom(ILocationWithPlace source) {
		EmbeddedLocationWithPlace loc = new EmbeddedLocationWithPlace();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public void copy(ILocation obj) {
		super.copy(obj);
		if (obj instanceof ILocationWithPlace) this.place = ((ILocationWithPlace) obj).getPlace();
	}
}
