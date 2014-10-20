/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Map;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>thngs</em>.
 **/
public class Thng extends DurableResourceModel implements ResourceWithProperties {

	private static final long serialVersionUID = -5495600871904690325L;

	private String name;
	public final static String FIELD_NAME = "name";

	private String description;

	private EmbeddedLocation location;

	/**
	 * Reference to {@link Product#id}.
	 */
	private String product;

	private Map<String, String> properties;

	/**
	 * An array of global identifiers for this thng
	 */
	private Map<String, String> identifiers;

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
		return "Thng [name=" + name + ", description=" + description + ", location=" + location + ", product=" + product + ", properties=" + properties + ", id=" + getId() + ", identifiers="
				+ identifiers + "]";
	}

	public void addIdentifier(String type, String value) {
		if (identifiers == null) {
			identifiers = new HashMap<String, String>();
		}
		identifiers.put(type, value);
	}

	public Map<String, String> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Map<String, String> identifiers) {
		this.identifiers = identifiers;
	}

	public String firstIdentifier() {
		return identifiers.values().iterator().next();
	}
}
