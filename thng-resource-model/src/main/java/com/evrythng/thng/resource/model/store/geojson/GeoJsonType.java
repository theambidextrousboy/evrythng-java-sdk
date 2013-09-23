package com.evrythng.thng.resource.model.store.geojson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeoJsonType {
	POINT("Point"),
	POLYGON("Polygon");
	
	private static Map<String, GeoJsonType> geoJSONTypeNames = new HashMap<String, GeoJsonType>();
	private String type;
	
	private GeoJsonType(String type) {
		this.type = type;
	}

	static {
		for (GeoJsonType t : values()) {
			geoJSONTypeNames.put(t.type, t);
		}
	}

	@JsonValue
	public String toString() {
		return type;
	}
	
	@JsonCreator
	public static GeoJsonType fromString(String typeName) {
		if (typeName == null) {
			return null;
		}
		GeoJsonType t = geoJSONTypeNames.get(typeName);
		if (t == null) {
			throw new IllegalArgumentException("Invalid enum value.");
		}
		return t;
	}
}
