/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

import java.util.Arrays;

import com.evrythng.thng.resource.model.store.ILocation;
import com.evrythng.thng.resource.model.store.LocationHelper;

/**
 * Geo JSON location to be used within a parent model.
 * 
 * @author colin
 **/
public class GeoJsonLocation implements ILocation {

	private double[] coordinates;

	public GeoJsonLocation() {
		this(0, 0);
	}

	public GeoJsonLocation(double latitude, double longitude) {
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

	public static GeoJsonLocation copyFrom(GeoJsonLocation source) {
		return new GeoJsonLocation(source.getLatitude(), source.getLongitude());
	}

	public static GeoJsonLocation copyFrom(ILocation source) {
		GeoJsonLocation loc = new GeoJsonLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public String toString() {
		return "EmbeddedGeoJSONLocation [coordinates="
				+ Arrays.toString(coordinates) + "]";
	}
}
