/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

/**
 * Multi point geojson.
 * 
 **/
public class GeoJsonMultiPoint extends GeoJsonMultiCoordinate {

	protected GeoJsonMultiPoint(GeoJsonType type) {
		super(GeoJsonType.MULTI_POINT);
	}

}
