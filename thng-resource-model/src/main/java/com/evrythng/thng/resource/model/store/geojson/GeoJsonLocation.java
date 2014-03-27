/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

import java.util.Arrays;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;

/**
 * Geo JSON location to be used within a parent model.
 * 
 * @author colin
 **/
public class GeoJsonLocation implements Traceable {

	private double[] coordinates;

	public GeoJsonLocation() {
		this(0, 0);
	}

	public GeoJsonLocation(double latitude, double longitude) {
		this.coordinates = new double[] { longitude, latitude };
	}

	@Override
	public Double getLatitude() {
		return coordinates[GeoJson.LAT_IDX];
	}

	@Override
	public void setLatitude(Double latitude) {
		coordinates[GeoJson.LAT_IDX] = latitude;
	}

	@Override
	public Double getLongitude() {
		return coordinates[GeoJson.LON_IDX];
	}

	@Override
	public void setLongitude(Double longitude) {
		coordinates[GeoJson.LON_IDX] = longitude;
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

	public static GeoJsonLocation copyFrom(Traceable source) {
		GeoJsonLocation loc = new GeoJsonLocation();
		LocationHelper.copy(source, loc);
		return loc;
	}

	@Override
	public String toString() {
		return "GeoJsonLocation [coordinates=" + Arrays.toString(coordinates) + "]";
	}
}
