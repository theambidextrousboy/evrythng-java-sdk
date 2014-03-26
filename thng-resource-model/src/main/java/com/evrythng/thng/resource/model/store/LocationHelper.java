/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Random;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Helper class for locations.
 * 
 **/
public class LocationHelper {

	public static Random random = new Random();

	public static void copy(Locatable from, Locatable to) {
		copy((Positionable) from, (Positionable) to);
		to.setPlace(from.getPlace());
		to.setCustomFields(new HashMap<String, String>(from.getCustomFields()));
	}

	public static void copy(Positionable from, Positionable to) {
		copy((Traceable) from, (Traceable) to);
		to.setPosition(new GeoJsonPoint(from.getLatitude(), from.getLongitude()));
	}

	public static void copy(Traceable from, Traceable to) {
		to.setLatitude(from.getLatitude());
		to.setLongitude(from.getLongitude());
	}

	/**
	 * Determines if the coordinates are valid.
	 * 
	 * @return true if loc is null or if both coordinates are null or if the
	 *         location is valid; false otherwise.
	 */
	public static boolean isGeoValid(Traceable loc) {
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

	public static boolean coordinatesEqual(Traceable a, Traceable b) {
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

	public static double randomLatitude() {
		return (-90) + (random.nextDouble() * (90 - (-90)));
	}

	public static double randomLongitude() {
		return (-180) + (random.nextDouble() * (180 - (-180)));
	}
}
