/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.ResourceModel;

/**
 * Model representation for {@link Property} values.
 * 
 * @author Pedro De Almeida (almeidap)
 * 
 */
public class PropertyValue extends ResourceModel {

	private String value;
	private long timestamp;

	/**
	 * Creates a new empty instance of {@link PropertyValue}.
	 */
	public PropertyValue() {
		/* Allow dynamic instantiation */
	}

	/**
	 * Creates a new empty instance of {@link PropertyValue}.
	 * 
	 * @param value
	 */
	public PropertyValue(String value) {
		this.value = value;
	}

	/**
	 * Creates a new empty instance of {@link PropertyValue}.
	 * 
	 * @param value
	 * @param timestamp
	 */
	public PropertyValue(String value, long timestamp) {
		this.value = value;
		this.timestamp = timestamp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
