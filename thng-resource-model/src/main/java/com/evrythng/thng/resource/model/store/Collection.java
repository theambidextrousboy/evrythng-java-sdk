/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.LinkedHashSet;

/**
 * Model representation for <em>collections</em>.
 */
public class Collection extends DurableResourceModel {

	private static final long serialVersionUID = -2064399431964890923L;
	private String name;
	private String description;
	private String type;
	/**
	 * Collection of {@link Thng#id} references.
	 */
	private LinkedHashSet<String> thngs;

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

	public String getType() {

		return type;
	}

	public void setType(final String type) {

		this.type = type;
	}

	public LinkedHashSet<String> getThngs() {

		return thngs;
	}

	public void setThngs(final LinkedHashSet<String> thngs) {

		this.thngs = thngs;
	}

	@Override
	public String toString() {

		return "Collection{" + "name='" + name + "\'" + ", description='" + description + "\'" + ", type=" + type + "} + , thngs=" + thngs + "}";
	}
}
