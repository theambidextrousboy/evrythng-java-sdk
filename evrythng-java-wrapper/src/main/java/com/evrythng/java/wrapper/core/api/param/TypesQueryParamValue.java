/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "types"} query param.
 */
public class TypesQueryParamValue extends QueryParamValue {

	public static final String NAME = "types";

	public TypesQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param types
	 *            comma separated types
	 * @return query parameter for this types
	 */
	public static TypesQueryParamValue types(String types) {
		return new TypesQueryParamValue(types);
	}
}
