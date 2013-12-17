package com.evrythng.thng.resource.model.store.geojson;

import java.util.List;

public abstract class GeoJsonMultiCoordinate extends GeoJson {

	private List<GeoJsonLocation> coordinates;

	protected GeoJsonMultiCoordinate(GeoJsonType type) {
		super(type);
	}

	public List<GeoJsonLocation> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<GeoJsonLocation> coordinates) {
		this.coordinates = coordinates;
	}
}
