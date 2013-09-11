/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Interface for a location model with a place.
 * 
 * @author Michel Yerly (my)
 **/
public interface ILocationWithPlace extends ILocation {

	public String getPlace();

	public void setPlace(String place);
}
