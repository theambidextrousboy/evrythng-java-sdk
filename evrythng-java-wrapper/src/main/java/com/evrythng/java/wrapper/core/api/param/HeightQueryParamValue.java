/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

/**
 * Provides support for the {@code "h"} query param.
 */
public class HeightQueryParamValue extends IntegerQueryParamValue {

	public static final String NAME = "h";

	public HeightQueryParamValue(int value) {
		super(NAME, value);
	}

	/**
	 * 
	 * @param height
	 *            height
	 * @return query parameter for height
	 */
	public static HeightQueryParamValue height(int height) {
		return new HeightQueryParamValue(height);
	}
}
