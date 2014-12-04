package com.evrythng.thng.resource.model.store.geojson;

import java.util.List;

public abstract class GeoJsonMultiCoordinate extends GeoJson {

	private static final long serialVersionUID = 8060290621322654662L;
	private List<GeoJsonLocation> coordinates;

	protected GeoJsonMultiCoordinate(final GeoJsonType type) {

		super(type);
	}

	public List<GeoJsonLocation> getCoordinates() {

		return coordinates;
	}

	public void setCoordinates(final List<GeoJsonLocation> coordinates) {

		this.coordinates = coordinates;
	}
}
