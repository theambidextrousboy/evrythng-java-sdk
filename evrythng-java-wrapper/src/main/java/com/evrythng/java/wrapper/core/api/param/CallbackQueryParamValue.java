/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "callback"} query param.
 */
public class CallbackQueryParamValue extends QueryParamValue {

	public static final String NAME = "callback";

	public CallbackQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param callback
	 *            callback value
	 * @return query parameter for this callback value
	 */
	public static CallbackQueryParamValue callback(String callback) {
		return new CallbackQueryParamValue(callback);
	}

	/**
	 * @return query parameter for empty callback
	 */
	public static CallbackQueryParamValue empty() {
		return new CallbackQueryParamValue(null);
	}
}
