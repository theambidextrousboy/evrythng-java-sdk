/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a resource model with properties.
 * 
 * @author Michel Yerly (my)
 **/
public interface ResourceWithProperties {

	/**
	 * @return The properties.
	 */
	Map<String, String> getProperties();
}
