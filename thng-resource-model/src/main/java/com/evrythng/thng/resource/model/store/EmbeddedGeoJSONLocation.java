/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Arrays;

/**
 * Geo JSON location to be used within a parent model.
 * 
 * @author colin
 **/
public class EmbeddedGeoJSONLocation implements ILocation {

	private double[] coordinates;

	public EmbeddedGeoJSONLocation() {
		this(0d, 0d);
	}

	public EmbeddedGeoJSONLocation(Double latitude, Double longitude) {
		this.coordinates = new double[] {longitude, latitude};
	}

	@Override
	public Double getLatitude() {
		return coordinates[LAT_IDX];
	}

	@Override
	public void setLatitude(Double latitude) {
		coordinates[LAT_IDX] = latitude;
	}

	@Override
	public Double getLongitude() {
		return coordinates[LON_IDX];
	}

	@Override
	public void setLongitude(Double longitude) {
		coordinates[LON_IDX] = longitude;
	}
	
	public double[] getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public static EmbeddedGeoJSONLocation copyFrom(EmbeddedGeoJSONLocation source) {
		return new EmbeddedGeoJSONLocation(source.getLatitude(), source.getLongitude());
	}

	public static EmbeddedGeoJSONLocation copyFrom(ILocation source) {
		EmbeddedGeoJSONLocation loc = new EmbeddedGeoJSONLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public void copy(ILocation obj) {
		setLatitude(obj.getLatitude());
		setLongitude(obj.getLongitude());
	}

	@Override
	public String toString() {
		return "EmbeddedGeoJSONLocation [coordinates="
				+ Arrays.toString(coordinates) + "]";
	}
}
