/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link QueryParamValue} subclass that holds boolean value.
 */
public class BooleanQueryParamValue extends QueryParamValue {

	public BooleanQueryParamValue(String key, boolean value) {
		super(key, Boolean.valueOf(value).toString());
	}
}
