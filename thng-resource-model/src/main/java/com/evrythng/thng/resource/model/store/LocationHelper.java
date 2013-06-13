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

	public static boolean coordinatesEqual(ILocation a, ILocation b) {
		if (a == b) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		final double TOLERANCE = 0.0000001;
		boolean lat, lon;
		if (a.getLatitude() == null && b.getLatitude() == null) {
			lat = true;
		} else if (a.getLatitude() == null || b.getLatitude() == null) {
			lat = false;
		} else {
			lat = Math.abs(a.getLatitude() - b.getLatitude()) < TOLERANCE;
		}
		if (!lat) {
			return false;
		}
		if (a.getLongitude() == null && b.getLongitude() == null) {
			lon = true;
		} else if (a.getLongitude() == null || b.getLongitude() == null) {
			lon = false;
		} else {
			lon = Math.abs(a.getLongitude() - b.getLongitude()) < TOLERANCE;
		}
		return lon;
	}
}
