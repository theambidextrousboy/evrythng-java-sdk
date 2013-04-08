/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

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

	@com.fasterxml.jackson.annotation.JsonValue
	@org.codehaus.jackson.annotate.JsonValue
	public String getJsonValue() {
		return jsonValue;
	}

	@com.fasterxml.jackson.annotation.JsonCreator
	@org.codehaus.jackson.annotate.JsonCreator
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
