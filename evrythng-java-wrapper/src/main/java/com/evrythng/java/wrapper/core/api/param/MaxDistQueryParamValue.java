/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code maxDist} query param.
 */
public class MaxDistQueryParamValue extends DoubleQueryParamValue {

	public static final String NAME = "maxDist";

	public MaxDistQueryParamValue(double value) {
		super(NAME, value);
	}

	/**
	 * @param maxDist
	 *            maxDist
	 * @return query parameter for this maxDist
	 */
	public static MaxDistQueryParamValue maxDist(double maxDist) {
		return new MaxDistQueryParamValue(maxDist);
	}
}
