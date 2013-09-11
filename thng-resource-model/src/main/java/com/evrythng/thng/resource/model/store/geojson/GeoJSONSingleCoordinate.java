package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.EmbeddedGeoJSONLocation;
import com.evrythng.thng.resource.model.store.ILocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoJSONSingleCoordinate extends GeoJSON implements ILocation {
	private EmbeddedGeoJSONLocation coordinates;
	
	protected GeoJSONSingleCoordinate(GeoJSONType type, EmbeddedGeoJSONLocation coordinates) {
		super(type);
		this.coordinates = new EmbeddedGeoJSONLocation();
		this.coordinates.copy(coordinates);
	}
	
	protected GeoJSONSingleCoordinate(GeoJSONType type, Double latitude, Double longitude) {
		super(type);
		this.coordinates = new EmbeddedGeoJSONLocation(latitude, longitude);
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
	public void setCoordinates(EmbeddedGeoJSONLocation coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setCoordinatesArr(double[] coordinates) {
		this.coordinates.setCoordinates(coordinates);
	}

	public void copy(ILocation obj) {
		this.coordinates.copy(obj);
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
