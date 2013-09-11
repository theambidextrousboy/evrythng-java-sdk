package com.evrythng.thng.resource.model.store.geojson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class GeoJSON implements IGeoJSON {
	private GeoJSONType type;
	
	public GeoJSON(GeoJSONType type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type.toString();
	}

	@JsonIgnore
	@Override
	public GeoJSONType getGeoJSONType() {
		return type;
	}

	@Override
	public String toString() {
		return "GeoJSON [type=" + type + "]";
	}
}
