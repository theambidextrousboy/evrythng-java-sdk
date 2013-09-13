package com.evrythng.thng.resource.model.store.geojson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents the characteristics of a GeoJSON coordinate
 * @author colin
 *
 */
public abstract class GeoJson {
	
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_COORDINATES = "coordinates";
	
	private GeoJsonType type;
	
	public GeoJson(GeoJsonType type) {
		this.type = type;
	}

	public String getType() {
		return type.toString();
	}

	@JsonIgnore
	public GeoJsonType getGeoJsonType() {
		return type;
	}

	@Override
	public String toString() {
		return "GeoJson [type=" + type + "]";
	}
}
