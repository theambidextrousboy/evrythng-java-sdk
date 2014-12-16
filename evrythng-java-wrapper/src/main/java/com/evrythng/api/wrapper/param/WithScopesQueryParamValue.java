/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code withScopes} query param.
 */
public class WithScopesQueryParamValue extends QueryParamValue {

	public static final String NAME = "withScopes";

	/**
	 * @param value {@code "true"} or {@code "false"}
	 */
	public WithScopesQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param value {@code "true"} or {@code "false"}
	 */
	public static WithScopesQueryParamValue valueOf(final String value) {

		return new WithScopesQueryParamValue(value);
	}
}
