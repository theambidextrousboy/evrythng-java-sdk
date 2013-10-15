/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for the enums.
 * 
 **/
public class EnumUtils {

	/**
	 * Creates an enum value from a string using a lookup map.
	 */
	public static <E> E fromString(Map<String, E> names, String name) {
		if (name == null) {
			return null;
		}
		E t = names.get(name);
		if (t == null) {
			throw new IllegalArgumentException("Invalid enum value.");
		}
		return t;
	}

	/**
	 * Creates a lookup map for the enum names. Uses the toString method.
	 */
	public static <E> Map<String, E> createNames(E[] values) {
		Map<String, E> names = new HashMap<String, E>();
		for (E t : values) {
			names.put(t.toString(), t);
		}
		return names;
	}
}
