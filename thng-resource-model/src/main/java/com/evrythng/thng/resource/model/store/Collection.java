/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>collections</em>.
 */
public class Collection extends DurableResourceModel {

	private static final long serialVersionUID = -2064399431964890923L;
	private String name;
	private String description;

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	@Override
	public String toString() {

		return "Collection{" + "name='" + name + "\'" + ", description='" + description + "\'" + "}";
	}
}
