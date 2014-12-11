/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.geojson;

/**
 * Multi point geojson.
 */
public class GeoJsonMultiPoint extends GeoJsonMultiCoordinate {

	private static final long serialVersionUID = 4413728162577148877L;

	public GeoJsonMultiPoint() {

		super(GeoJsonType.MULTI_POINT);
	}
}
