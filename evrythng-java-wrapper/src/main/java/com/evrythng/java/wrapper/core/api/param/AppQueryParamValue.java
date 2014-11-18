/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code app} query param.
 */
public class AppQueryParamValue extends QueryParamValue {

	public static final String NAME = "app";
	private static final String ALL = "all";

	public AppQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param appId
	 *            app id
	 * @return query parameter for this app id
	 */
	public static AppQueryParamValue appId(String appId) {
		return new AppQueryParamValue(appId);
	}

	/**
	 * @return query parameter for all app ids
	 */
	public static AppQueryParamValue all() {
		return new AppQueryParamValue(ALL);
	}

	/**
	 * @param appId
	 *            app id
	 * @return true if app id equals {@code "all"}
	 */
	public static boolean isAll(String appId) {
		return ALL.equals(appId);
	}
}
