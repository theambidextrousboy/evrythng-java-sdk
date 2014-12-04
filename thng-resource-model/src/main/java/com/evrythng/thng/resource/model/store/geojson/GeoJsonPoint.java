package com.evrythng.thng.resource.model.store.geojson;

public class GeoJsonPoint extends GeoJsonSingleCoordinate {

	private static final long serialVersionUID = -8762410528305978101L;

	public GeoJsonPoint() {

		this(0d, 0d);
	}

	public GeoJsonPoint(final Double latitude, final Double longitude) {

		super(GeoJsonType.POINT, latitude, longitude);
	}

	public GeoJsonPoint(final GeoJsonLocation coordinate) {

		super(GeoJsonType.POINT, coordinate);
	}

	@Override
	public String toString() {

		return "GeoJSONPoint [getCoordinate()=" + getCoordinates() + ", getType()=" + getType() + "]";
	}
}
