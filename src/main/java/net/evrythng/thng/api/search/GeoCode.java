/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.search;

public class GeoCode {
	
	public enum MeasureUnit {
		KM("km"), MI("mi");
		
		private final String abbreviation;

		private MeasureUnit(String abbreviation) {
			this.abbreviation = abbreviation;
		}
		
		@Override
		public String toString() {
			return abbreviation;
		}
	}

	public static final String GEOCODE_FORMAT = "%.6f,%.6f,%f%s";
	
	private double latitude;
	private double longitude;
	private double radius;
	private MeasureUnit radiusUnit = MeasureUnit.KM;
	
	public GeoCode(double latitude, double longitude, double radius) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	public GeoCode(double latitude, double longitude, double radius, MeasureUnit radiusUnit) {
		this(latitude, longitude, radius);
		this.radiusUnit = radiusUnit;
	}
	
	@Override
	public String toString() {
		return String.format(GEOCODE_FORMAT, latitude, longitude, radius, radiusUnit);
	}
	
}
