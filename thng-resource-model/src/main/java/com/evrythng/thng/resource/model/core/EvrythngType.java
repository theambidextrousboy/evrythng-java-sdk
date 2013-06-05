/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumearates Evrythng's object types.
 * 
 * @author Michel Yerly (my)
 **/
public enum EvrythngType {

	THNG("thng"), PRODUCT("product"), PLACE("place"), COLLECTION("collection");

	private final String jsonValue;
	private static Map<String, EvrythngType> lookup;

	private EvrythngType(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	static {
		lookup = new HashMap<String, EvrythngType>();
		for (EvrythngType t : EnumSet.allOf(EvrythngType.class)) {
			lookup.put(t.getJsonValue(), t);
		}
	}

	@JsonValue
	public String getJsonValue() {
		return jsonValue;
	}

	/**
	 * Returns an {@link EvrythngType} value given its corresponding name.
	 * 
	 * @return The {@link EvrythngType} value or null if the parameter is null.
	 * @throws IllegalArgumentException
	 *             if the name does not match any value.
	 */
	@JsonCreator
	public static EvrythngType forValue(String v) {
		if (v == null) {
			return null;
		}
		EvrythngType t = lookup.get(v);
		if (t == null) {
			throw new IllegalArgumentException();
		}
		return t;
	}
}
