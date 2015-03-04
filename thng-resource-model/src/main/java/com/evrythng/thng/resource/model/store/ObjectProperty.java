/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Map;

/**
 * Model representation for <em>number properties</em>.
 */
public final class ObjectProperty extends Property<Map<String, ?>> {

	private static final long serialVersionUID = -806157898317946571L;

	/**
	 * Creates a new empty instance of {@link ObjectProperty}.
	 */
	public ObjectProperty() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link ObjectProperty}.
	 */
	public ObjectProperty(final String key, final Map<String, ?> value) {

		super(key, value != null ? new HashMap<>(value) : null);
	}

	/**
	 * Creates a new instance of {@link ObjectProperty}.
	 */
	public ObjectProperty(final String key, final Map<String, ?> value, final Long timestamp) {

		super(key, value != null ? new HashMap<>(value) : null, timestamp);
	}
}
