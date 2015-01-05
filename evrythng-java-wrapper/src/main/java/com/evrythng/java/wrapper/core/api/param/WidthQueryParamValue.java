/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "w"} query param.
 */
public class WidthQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "w";

	public WidthQueryParamValue(final int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param width
	 *            width
	 * @return query parameter for width
	 */
	public static WidthQueryParamValue width(final int width) {
		return new WidthQueryParamValue(width);
	}
}
