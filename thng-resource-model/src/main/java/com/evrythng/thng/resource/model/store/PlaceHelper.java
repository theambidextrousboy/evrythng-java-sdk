/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Helper class for places.
 * 
 * @author colin
 **/
public class PlaceHelper {

	/*public static void copy(ILocation from, ILocation to) {
		to.copy(from);
	}*/
	
	public static double[] getCoordinates(GeoJsonPoint location) {
		try {
			return new double[] {location.getLongitude(), location.getLatitude()};
		} catch (NullPointerException e) {
			return null;
		}
	}
}
