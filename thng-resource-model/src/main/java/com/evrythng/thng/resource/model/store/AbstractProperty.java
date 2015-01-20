/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
			if (value instanceof Double) {
				return NUMBER;
			}
			if (value instanceof String) {
				return STRING;
			}
			// TODO consider ARRAY and OBJECT
			throw new IllegalArgumentException("Unsupported type: " + value.getClass().getSimpleName());
		}
	}

	private static final long serialVersionUID = 4241830003414536087L;
	private Type type;
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

		// TODO add reasoning about type
		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link com.evrythng.thng.resource.model.store.AbstractProperty}.
	 */
	AbstractProperty(final String key, final V value, final Long timestamp) {

		// TODO add reasoning about type
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

	@JsonIgnore
	public Type getType() {

		return type;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("{");
		sb.append("").append(key).append(": ");
		sb.append(value).append(", <");
		sb.append(type).append(">");
		sb.append('}');
		return sb.toString();
	}
}
