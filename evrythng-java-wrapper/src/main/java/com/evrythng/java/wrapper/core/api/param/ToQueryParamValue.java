/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import com.evrythng.thng.commons.config.ApiConfiguration;

/**
 * Provides support for the {@code "to"} query param.
 */
public class ToQueryParamValue extends QueryParamValue {

	public static final String NAME = "to";

	public ToQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param to
	 *            from value
	 * @return query parameter for this from value
	 */
	public static ToQueryParamValue to(String to) {
		return new ToQueryParamValue(to);
	}
}
