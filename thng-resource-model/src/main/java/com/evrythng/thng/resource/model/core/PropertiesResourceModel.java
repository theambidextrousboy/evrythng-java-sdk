package com.evrythng.thng.resource.model.core;

import java.util.Map;

/**
 * YAABC for a thing that should be a mixin-style interface.
 * TODO: Update the diagram, ask Pedro what tool he used to draw it.
 */
public class PropertiesResourceModel extends DurableResourceModel {

	/**
	 * Map of key/value entries.
	 */
	private Map<String, String> properties;

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
