/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

/**
 * Model representation for <em>properties</em>.
 */
public abstract class AbstractProperty<V> extends TemporalResourceModel {

	private static final long serialVersionUID = 4241830003414536087L;
	private String key;
	private V value;

	/**
	 * Creates a new empty instance of {@link com.evrythng.thng.resource.model.store.AbstractProperty}.
	 */
	AbstractProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link com.evrythng.thng.resource.model.store.AbstractProperty}.
	 */
	AbstractProperty(final String key, final V value) {

		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link com.evrythng.thng.resource.model.store.AbstractProperty}.
	 */
	AbstractProperty(final String key, final V value, final Long timestamp) {

		super(timestamp);
		this.key = key;
		this.value = value;
	}

	public final String getKey() {

		return key;
	}

	public final void setKey(final String key) {

		this.key = key;
	}

	public final V getValue() {

		return value;
	}

	public final void setValue(final V value) {

		this.value = value;
	}
}
