/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a share
 */
public abstract class ActionOnCollection extends Action {

	private static final long serialVersionUID = -3695192368487217322L;
	public static final String FIELD_COLLECTION = "collection";
	private String collection;

	protected ActionOnCollection(final String type) {

		setType(type);
	}

	protected ActionOnCollection() {
		// TODO _MS_ check whether jax.ws.rs can work with default visibility
	}

	public String getCollection() {

		return collection;
	}

	public void setCollection(final String collection) {

		this.collection = collection;
	}
}
