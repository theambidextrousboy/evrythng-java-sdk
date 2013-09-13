package com.evrythng.thng.resource.model.store;


/**
 * Model representation for <em>locations</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class LocationWithPlace extends Location implements ILocationWithPlace {

	private String place;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Location{" + "latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", place=" + place + ", timestamp=" + getTimestamp() + '}';
	}
}
