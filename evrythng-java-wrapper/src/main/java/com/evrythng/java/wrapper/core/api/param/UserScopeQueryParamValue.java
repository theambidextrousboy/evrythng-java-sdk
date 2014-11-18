/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "userScope"} query param.
 */
public class UserScopeQueryParamValue extends QueryParamValue {

	public static final String NAME = "userScope";

	public UserScopeQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param userScope
	 *            user scope
	 * @return query parameter for this user scope
	 */
	public static UserScopeQueryParamValue userScope(String userScope) {
		return new UserScopeQueryParamValue(userScope);
	}
}
