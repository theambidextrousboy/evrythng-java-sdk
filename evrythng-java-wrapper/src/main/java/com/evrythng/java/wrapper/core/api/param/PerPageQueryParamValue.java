/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "perPage"} query param.
 */
public class PerPageQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "perPage";

	public PerPageQueryParamValue(final int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param perPage
	 *            items per page
	 * @return query parameter for this items per page count
	 */
	public static PerPageQueryParamValue perPage(final int perPage) {
		return new PerPageQueryParamValue(perPage);
	}
}
