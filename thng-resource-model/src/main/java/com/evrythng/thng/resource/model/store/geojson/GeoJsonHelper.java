package com.evrythng.thng.resource.model.store.geojson;

import com.evrythng.thng.resource.model.store.LocationHelper;
import com.evrythng.thng.resource.model.store.Traceable;

/**
 * Helper class for GeoJson
 * 
 */
public class GeoJsonHelper {

	public static Double getLatitude(GeoJsonPoint loc) {
		double[] coordinates = loc.getCoordinatesArr();
		return coordinates == null ? null : loc.getCoordinatesArr()[Traceable.LAT_IDX];
	}

	public static Double getLongitude(GeoJsonPoint loc) {
		double[] coordinates = loc.getCoordinatesArr();
		return coordinates == null ? null : loc.getCoordinatesArr()[Traceable.LON_IDX];
	}

	public static void setPosition(GeoJsonPoint loc, Double latitude, Double longitude) {
		if (latitude == null || longitude == null) {
			if (latitude == null && longitude == null) {
				loc.setCoordinates(null);
			} else {
				throw new IllegalArgumentException("latitude and longitude must either be both null, or both non null.");
			}
		} else {
			double[] coordinates = loc.getCoordinatesArr();
			if (coordinates == null) {
				loc.setCoordinatesArr(new double[] { longitude, latitude });
			} else {
				coordinates[Traceable.LAT_IDX] = latitude;
				coordinates[Traceable.LON_IDX] = longitude;
			}
		}
	}

	public static GeoJsonPoint createRandomGeoJsonPoint() {
		return new GeoJsonPoint(LocationHelper.randomLatitude(), LocationHelper.randomLongitude());
	}

}
