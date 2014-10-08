/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

/**
 * Model representation for <em>properties</em>.
 **/
public class Property extends TemporalResourceModel {

	private static final long serialVersionUID = 795449469216913053L;

	private String key;

	private String value;

	/**
	 * Creates a new empty instance of {@link Property}.
	 */
	public Property() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link Property}.
	 * 
	 * @param key
	 * @param value
	 */
	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link Property}.
	 * 
	 * @param key
	 * @param value
	 */
	public Property(String key, String value, Long timestamp) {
		super(timestamp);
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
