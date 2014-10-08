package com.evrythng.thng.resource.model.store;

/**
 * Characterizes the ability to be traceable within a geographical coordinate
 * system.
 */
public interface Traceable {

	public Double getLatitude();

	public void setLatitude(Double latitude);

	public Double getLongitude();

	public void setLongitude(Double longitude);

}
