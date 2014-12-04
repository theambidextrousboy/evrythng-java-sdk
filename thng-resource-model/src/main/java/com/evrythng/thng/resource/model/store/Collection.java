/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.ScopeResource;
import com.evrythng.thng.resource.model.core.WithScopeResource;

import java.util.LinkedHashSet;

/**
 * Model representation for <em>collections</em>.
 */
public class Collection extends DurableResourceModel implements WithScopeResource {

	private static final long serialVersionUID = -2064399431964890923L;
	private String name;
	private String description;
	/**
	 * Collection of {@link Thng#id} references.
	 */
	private LinkedHashSet<String> thngs;
	private ScopeResource scopes;

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

	public LinkedHashSet<String> getThngs() {

		return thngs;
	}

	public void setThngs(final LinkedHashSet<String> thngs) {

		this.thngs = thngs;
	}

	@Override
	public String toString() {

		return "Collection{" + "name='" + name + "\'" + ", description='" + description + "\'" + ", thngs=" + thngs + "}";
	}
}
