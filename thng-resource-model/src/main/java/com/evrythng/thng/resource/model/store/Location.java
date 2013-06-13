package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

/**
 * Model representation for <em>locations</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class Location extends TemporalResourceModel implements ILocation {

	private Double latitude;
	private Double longitude;

	private String place;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public boolean coordinatesEqual(Location other) {
		return (other != null) && (latitude.equals(other.latitude) && longitude.equals(other.longitude));
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Location location = (Location) o;

		return this.coordinatesEqual(location) && ((getTimestamp() == null && location.getTimestamp() == null) || (getTimestamp() != null && getTimestamp().equals(location.getTimestamp())));
	}

	@Override
	public int hashCode() {
		int result = latitude.hashCode();
		result = 31 * result + longitude.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Location{" + "latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + getTimestamp() + '}';
	}
}
