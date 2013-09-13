package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.ILocation;
import com.evrythng.thng.resource.model.store.LocationHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GeoJsonSingleCoordinate extends GeoJson implements ILocation {
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
	
	@JsonIgnore
	public ILocation getCoordinates() {
		return coordinates;
	}
	
	@JsonProperty(value="coordinates")
	public double[] getCoordinatesArr() {
		return coordinates.getCoordinates();
	}
	
	@JsonIgnore
	@JsonProperty(value="coordinates")
	public void setCoordinates(GeoJsonLocation coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setCoordinatesArr(double[] coordinates) {
		this.coordinates.setCoordinates(coordinates);
	}

	public void copy(ILocation obj) {
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
