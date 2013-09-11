package com.evrythng.thng.resource.model.store.geojson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeoJSONType {
	POINT("Point"),
	POLYGON("Polygon");
	
	private String type;
	
	private GeoJSONType(String type) {
		this.type = type;
	}

	@JsonValue
	public String toString() {
		return type;
	}
	
	@JsonCreator
	public static GeoJSONType fromString(String typeName) {
		try {
			return GeoJSONType.valueOf(typeName);
		} catch (Exception e) {
			return null;
		}
	}
}
