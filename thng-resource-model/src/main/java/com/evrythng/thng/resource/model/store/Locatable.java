/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a location model with a position, place and custom fields
 */
public interface Locatable extends Positionable {

	String getPlace();

	void setPlace(String place);

	Map<String, String> getCustomFields();

	void setCustomFields(Map<String, String> customFields);
}
