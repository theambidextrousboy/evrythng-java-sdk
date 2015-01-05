/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code lat} query param.
 */
public class LatQueryParamValue extends DoubleQueryParamValue {

	public static final String NAME = "lat";

	public LatQueryParamValue(final double value) {
		super(NAME, value);
	}

	/**
	 * @param latitude
	 *            latitude
	 * @return query parameter for this latitude
	 */
	public static LatQueryParamValue lat(final double latitude) {
		return new LatQueryParamValue(latitude);
	}
}
