/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Geo JSON location to be used within a parent model.
 **/
public class GeoJsonLocation implements Traceable, Serializable {

	private static final long serialVersionUID = -2750554873532665915L;

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
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GeoJsonLocation that = (GeoJsonLocation) o;
		if (!Arrays.equals(coordinates, that.coordinates)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {

		return coordinates != null ? Arrays.hashCode(coordinates) : 0;
	}

	@Override
	public String toString() {
		return "GeoJsonLocation [coordinates=" + Arrays.toString(coordinates) + "]";
	}
}
