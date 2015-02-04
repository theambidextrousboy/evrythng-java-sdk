/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

import java.util.Date;

/**
 * Model representation for <em>properties</em>.
 */
public abstract class AbstractProperty<V> extends TemporalResourceModel {

	public enum Type {

		// TODO consider ARRAY and OBJECT
		BOOLEAN, STRING, NUMBER;

		public static Type forPropertyValue(final Object value) {

			if (value == null) {
				throw new IllegalArgumentException("Cannot determine the type of null property value");
			}
			if (value instanceof Boolean) {
				return BOOLEAN;
			}
			if (value instanceof Number) {
				return NUMBER;
			}
			if (value instanceof String) {
				return STRING;
			}
			// TODO consider ARRAY and OBJECT
			throw new IllegalArgumentException("Unsupported property type: " + value.getClass().getSimpleName());
		}
	}

	private static final long serialVersionUID = 4241830003414536087L;
	private String key;
	private V value;
	public static final String FIELD_VALUE = "value";

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

	public String getKey() {

		return key;
	}

	public void setKey(final String key) {

		this.key = key;
	}

	public V getValue() {

		return value;
	}

	public void setValue(final V value) {

		this.value = value;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AbstractProperty that = (AbstractProperty) o;

		if (key != null ? !key.equals(that.key) : that.key != null) {
			return false;
		}
		if (value != null ? !value.equals(that.value) : that.value != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (key != null ? key.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("{");
		sb.append("<").append(key).append(">: ");
		sb.append(value);
		if (getTimestamp() != null) {
			sb.append(" - at ");
			sb.append(new Date(getTimestamp()));
		}
		sb.append('}');
		return sb.toString();
	}
}
