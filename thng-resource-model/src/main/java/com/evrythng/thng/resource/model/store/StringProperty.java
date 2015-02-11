/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>properties</em>.
 */
public class StringProperty extends Property<String> {

	private static final long serialVersionUID = 795449469216913053L;

	/**
	 * Creates a new empty instance of {@link StringProperty}.
	 */
	public StringProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link StringProperty}.
	 */
	public StringProperty(final String key, final String value) {

		super(key, value);
	}

	/**
	 * Creates a new instance of {@link StringProperty}.
	 */
	public StringProperty(final String key, final String value, final Long timestamp) {

		super(key, value, timestamp);
	}
}
