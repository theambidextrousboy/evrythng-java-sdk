/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "creationScopes"} query param.
 */
public class CreationScopesQueryParamValue extends QueryParamValue {

	public static final String NAME = "creationScopes";

	public CreationScopesQueryParamValue(final String creationScopes) {

		super(NAME, creationScopes);
	}

	public static CreationScopesQueryParamValue valueOf(final String creationScopes) {

		return new CreationScopesQueryParamValue(creationScopes);
	}
}
