package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Model representation for <em>locations</em>.
 */
public class Location extends TemporalResourceModel implements Locatable {

	private static final long serialVersionUID = 1126006191180696211L;
	private String place;
	private Double latitude;
	private Double longitude;
	private GeoJsonPoint position;

	@Override
	public Double getLatitude() {

		return latitude;
	}

	@Override
	public void setLatitude(final Double latitude) {

		this.latitude = latitude;
	}

	@Override
	public Double getLongitude() {

		return longitude;
	}

	@Override
	public void setLongitude(final Double longitude) {

		this.longitude = longitude;
	}

	@Override
	public GeoJsonPoint getPosition() {

		return position;
	}

	@Override
	public void setPosition(final GeoJsonPoint position) {

		this.position = position;
	}

	@Override
	public String getPlace() {

		return place;
	}

	@Override
	public void setPlace(final String place) {

		this.place = place;
	}

	@Override
	public String toString() {

		return "Location [place=" + place + ", latitude=" + latitude + ", longitude=" + longitude + ", position=" + position + ", id=" + getId() + ", createdAt=" + getCreatedAt() + ", customFields=" + getCustomFields() + "]";
	}
}
