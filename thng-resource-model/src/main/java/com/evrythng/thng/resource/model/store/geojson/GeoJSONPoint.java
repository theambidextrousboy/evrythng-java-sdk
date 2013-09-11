package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.EmbeddedGeoJSONLocation;

public class GeoJSONPoint extends GeoJSONSingleCoordinate {
	
	public GeoJSONPoint() {
		this(0d, 0d);
	}

	public GeoJSONPoint(Double latitude, Double longitude) {
		super(GeoJSONType.POINT, latitude, longitude);
	}

	public GeoJSONPoint(EmbeddedGeoJSONLocation coordinate) {
		super(GeoJSONType.POINT, coordinate);
	}

	@Override
	public String toString() {
		return "GeoJSONPoint [getCoordinate()=" + getCoordinates()
				+ ", getType()=" + getType() + "]";
	}
}
