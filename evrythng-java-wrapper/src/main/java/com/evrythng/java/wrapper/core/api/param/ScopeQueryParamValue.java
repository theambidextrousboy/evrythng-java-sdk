/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "scope"} query param.
 */
public class ScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "scope";

	public ScopeQueryParamValue(final String scope) {

		super(NAME, scope);
	}

	public static ScopeQueryParamValue valueOf(final String scope) {

		return new ScopeQueryParamValue(scope);
	}
}
