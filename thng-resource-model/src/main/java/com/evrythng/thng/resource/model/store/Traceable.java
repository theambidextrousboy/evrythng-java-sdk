package com.evrythng.thng.resource.model.store;

/**
 * Characterizes the ability to be traceable within a geographical coordinate
 * system.
 */
public interface Traceable {

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);
}
