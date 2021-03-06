/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model representation for <em>properties</em>.
 */
public abstract class Property<V> extends TemporalResourceModel {

	public enum Type {

		BOOLEAN, STRING, NUMBER, ARRAY, OBJECT;

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
			if (value instanceof List<?>) {
				return ARRAY;
			}
			if (value instanceof Map<?, ?>) {
				return OBJECT;
			}
			throw new IllegalArgumentException("Unsupported property type: " + value.getClass().getSimpleName());
		}
	}

	public static List<Property<?>> normalize(final List<Property<?>> denormalized){

		List<Property<?>> normalized = new ArrayList<>();
		Map<String, Integer> indexes = new HashMap<>();
		int index = 0;
		for (Property<?> property : denormalized) {
			String key = property.getKey() + "-" + (property.getTimestamp() != null ? property.getTimestamp() : "");
			if (indexes.get(key) == null) {
				indexes.put(key, index);
				normalized.add(index, property);
				index++;
			} else {
				normalized.set(indexes.get(key), property);
			}
		}
		return normalized;
	}

	private static final long serialVersionUID = 4241830003414536087L;
	private String key;
	private V value;
	public static final String FIELD_VALUE = "value";

	/**
	 * Creates a new empty instance of {@link Property}.
	 */
	Property() {
		/* Required by jackson. */
	}

	/**
	 * Creates a new instance of {@link Property}.
	 */
	Property(final String key, final V value) {

		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link Property}.
	 */
	Property(final String key, final V value, final Long timestamp) {

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

		Property that = (Property) o;

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
