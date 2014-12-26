/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * A {@link com.evrythng.java.wrapper.core.api.QueryParamValue} subclass that holds long value.
 */
public class LongQueryParamValue extends QueryParamValue {

	public LongQueryParamValue(final String key, final long value) {
		super(key, Long.toString(value));
	}
}
