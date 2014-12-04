package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum GeoJsonType {
	POINT("Point"), MULTI_POINT("MultiPoint"), POLYGON("Polygon")/*
																 * ,
																 * LINE_STRING(
																 * "LineString"
																 * ),
																 * MULTI_LINE_STRING
																 * (
																 * "MultiLineString"
																 * ),
																 * MULTI_POLYGON(
																 * "MultiPolygon"
																 * ),
																 * GEOMETRY_COLLECTION
																 * (
																 * "GeometryCollection"
																 * ),
																 * FEATURE("Feature"
																 * ),
																 * FEATURE_COLLECTION
																 * (
																 * "FeatureCollection"
																 * )
																 */;
	private static final Map<String, GeoJsonType> geoJSONTypeNames;
	private final String type;

	GeoJsonType(final String type) {

		this.type = type;
	}

	static {
		geoJSONTypeNames = EnumUtils.createNames(values());
	}

	@JsonValue
	public String toString() {

		return type;
	}

	@JsonCreator
	public static GeoJsonType fromString(final String typeName) {

		return EnumUtils.fromString(geoJSONTypeNames, typeName);
	}
}
