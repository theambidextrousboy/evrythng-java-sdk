/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code filter} query param.
 */
public class FilterQueryParamValue extends QueryParamValue {

	public static final String NAME = "filter";

	public FilterQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param filter filter
	 * @return query parameter for this filter
	 */
	public static FilterQueryParamValue filter(final String filter) {

		return new FilterQueryParamValue(filter);
	}
}
