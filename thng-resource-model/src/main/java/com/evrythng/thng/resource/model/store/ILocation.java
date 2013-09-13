package com.evrythng.thng.resource.model.store;

/**
 * Interface for a location
 * @author colin
 *
 */
public interface ILocation {
	static final int LON_IDX = 0;
	static final int LAT_IDX = 1;

	public Double getLatitude();

	public void setLatitude(Double latitude);

	public Double getLongitude();

	public void setLongitude(Double longitude);
}
