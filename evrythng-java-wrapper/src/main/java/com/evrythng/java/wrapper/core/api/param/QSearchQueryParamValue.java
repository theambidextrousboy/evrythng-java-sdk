/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "q"} query param.
 */
public class QSearchQueryParamValue extends QueryParamValue {

	public static final String NAME = "q";

	public QSearchQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param pattern
	 *            search pattern
	 * @return query parameter for this search pattern
	 */
	public static QSearchQueryParamValue pattern(String pattern) {
		return new QSearchQueryParamValue(pattern);
	}
}
