/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a location model with a position, place and custom fields
 * 
 **/
public interface Locatable extends Positionable {

	public String getPlace();

	public void setPlace(String place);

	public Map<String, String> getCustomFields();

	public void setCustomFields(Map<String, String> customFields);
}
