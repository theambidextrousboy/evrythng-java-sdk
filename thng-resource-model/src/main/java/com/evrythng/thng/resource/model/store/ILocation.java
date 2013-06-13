/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Interface for a location model.
 * 
 * @author Michel Yerly (my)
 **/
public interface ILocation {

	public String getPlace();

	public void setPlace(String place);

	public Double getLatitude();

	public void setLatitude(Double latitude);

	public Double getLongitude();

	public void setLongitude(Double longitude);
}
