/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link com.evrythng.java.wrapper.core.api.QueryParamValue} subclass that holds double value.
 */
public class DoubleQueryParamValue extends QueryParamValue {

	public DoubleQueryParamValue(final String key, final double value) {
		super(key, Double.toString(value));
	}
}
