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

	@Override
	public String toString() {
		return "Location{" + "latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + getTimestamp() + '}';
	}

	@Override
	public void copy(ILocation obj) {
		this.latitude = obj.getLatitude();
		this.longitude = obj.getLongitude();
	}
}
