/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link QueryParamValue} subclass that holds integer value.
 */
public class IntegerQueryParamValue extends QueryParamValue {

	public IntegerQueryParamValue(String key, int value) {
		super(key, Integer.toString(value));
	}
}
