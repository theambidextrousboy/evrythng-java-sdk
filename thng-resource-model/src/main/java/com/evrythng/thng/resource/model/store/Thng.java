/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>thngs</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class Thng extends DurableResourceModel implements ResourceWithProperties {

	private String name;

	private String description;

	private EmbeddedLocation location;

	/**
	 * Reference to {@link Product#id}.
	 */
	private String product;

	private Map<String, String> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmbeddedLocation getLocation() {
		return location;
	}

	public void setLocation(EmbeddedLocation location) {
		this.location = location;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Thng [name=" + name + ", description=" + description + ", location=" + location + ", product=" + product + ", properties=" + properties + ", id=" + id + "]";
	}
}
