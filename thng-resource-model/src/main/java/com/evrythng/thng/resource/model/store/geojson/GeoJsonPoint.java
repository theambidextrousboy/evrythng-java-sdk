package com.evrythng.thng.resource.model.store.geojson;


public class GeoJsonPoint extends GeoJsonSingleCoordinate {

	public GeoJsonPoint() {
		this(0d, 0d);
	}

	public GeoJsonPoint(Double latitude, Double longitude) {
		super(GeoJsonType.POINT, latitude, longitude);
	}

	public GeoJsonPoint(GeoJsonLocation coordinate) {
		super(GeoJsonType.POINT, coordinate);
	}

	@Override
	public String toString() {
		return "GeoJSONPoint [getCoordinate()=" + getCoordinates() + ", getType()=" + getType() + "]";
	}
}
