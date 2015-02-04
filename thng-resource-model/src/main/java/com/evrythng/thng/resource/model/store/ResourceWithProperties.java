/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

/**
 * Interface for a resource model with properties.
 */
public interface ResourceWithProperties {

	/**
	 * @return The properties.
	 * @deprecated use {@link #getTypedProperties()} instead
	 */
	@Deprecated
	Map<String, String> getProperties();

	/**
	 * @return The properties.
	 */
	Map<String, Object> getTypedProperties();
}
