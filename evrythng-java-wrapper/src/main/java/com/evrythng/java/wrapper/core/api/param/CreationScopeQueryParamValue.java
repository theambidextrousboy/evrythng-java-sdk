/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "creationScopes"} query param.
 */
public class CreationScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "creationScope";

	public CreationScopeQueryParamValue(final String creationScopes) {

		super(NAME, creationScopes);
	}

	public static CreationScopeQueryParamValue valueOf(final String creationScopes) {

		return new CreationScopeQueryParamValue(creationScopes);
	}
}
