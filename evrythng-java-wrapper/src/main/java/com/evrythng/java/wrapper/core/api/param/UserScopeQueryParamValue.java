/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "creationScopes"} query param.
 */
public class UserScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "userScope";

	public UserScopeQueryParamValue(final String value) {

		super(NAME, value);
	}

	public static UserScopeQueryParamValue valueOf(final String value) {

		return new UserScopeQueryParamValue(value);
	}
}
