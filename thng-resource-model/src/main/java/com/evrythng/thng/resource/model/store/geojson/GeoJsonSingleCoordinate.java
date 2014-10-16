package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GeoJsonSingleCoordinate extends GeoJson implements Traceable {

	private static final long serialVersionUID = -1353734859418227063L;

	private GeoJsonLocation coordinates;

	protected GeoJsonSingleCoordinate(GeoJsonType type, GeoJsonLocation coordinates) {
		super(type);
		this.coordinates = new GeoJsonLocation();
		this.coordinates.setCoordinates(coordinates.getCoordinates());
	}

	protected GeoJsonSingleCoordinate(GeoJsonType type, Double latitude, Double longitude) {
		super(type);
		this.coordinates = new GeoJsonLocation(latitude, longitude);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		GeoJsonSingleCoordinate that = (GeoJsonSingleCoordinate) o;

		if (coordinates != null ? !coordinates.equals(that.coordinates) : that.coordinates != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return coordinates != null ? coordinates.hashCode() : 0;
	}

	@JsonIgnore
	public Traceable getCoordinates() {
		return coordinates;
	}

	@JsonProperty(value = FIELD_COORDINATES)
	public double[] getCoordinatesArr() {
		return coordinates.getCoordinates();
	}

	@JsonIgnore
	@JsonProperty(value = FIELD_COORDINATES)
	public void setCoordinates(GeoJsonLocation coordinates) {
		this.coordinates = coordinates;
	}

	public void setCoordinatesArr(double[] coordinates) {
		this.coordinates.setCoordinates(coordinates);
	}

	public void copy(Traceable obj) {
		LocationHelper.copy(obj, this);
	}

	@Override
	@JsonIgnore
	public Double getLatitude() {
		return coordinates.getLatitude();
	}

	@Override
	public void setLatitude(Double latitude) {
		this.coordinates.setLatitude(latitude);
	}

	@Override
	@JsonIgnore
	public Double getLongitude() {
		return coordinates.getLongitude();
	}

	@Override
	public void setLongitude(Double longitude) {
		this.coordinates.setLongitude(longitude);
	}
}
