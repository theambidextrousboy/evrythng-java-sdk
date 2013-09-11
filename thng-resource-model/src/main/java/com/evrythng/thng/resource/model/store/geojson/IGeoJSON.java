package com.evrythng.thng.resource.model.store.geojson;


/**
 * Represents the characteristics of a GeoJSON coordinate
 * @author colin
 *
 */
public interface IGeoJSON {
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_COORDINATES = "coordinates";
	
	public String getType();
	public GeoJSONType getGeoJSONType();
}
