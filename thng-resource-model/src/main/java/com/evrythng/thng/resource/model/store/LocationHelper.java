/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Helper class for locations.
 * 
 * @author Michel Yerly (my)
 **/
public class LocationHelper {

	public static void copy(ILocation from, ILocation to) {
		to.setLatitude(from.getLatitude());
		to.setLongitude(from.getLongitude());
		to.setPlace(from.getPlace());
	}

	/**
	 * Determines if the coordinates are valid.
	 * 
	 * @return true if loc is null or if both coordinates are null or if the
	 *         location is valid; false otherwise.
	 */
	public static boolean isGeoValid(ILocation loc) {
		if (loc == null) {
			return true;
		}
		if (loc.getLatitude() == null && loc.getLongitude() == null) {
			return true;
		} else if (loc.getLatitude() != null && loc.getLongitude() != null) {
			return loc.getLatitude() >= -90 && loc.getLatitude() <= 90 && loc.getLongitude() >= -180 && loc.getLongitude() <= 180;
		} else {
			return false;
		}
	}
}
