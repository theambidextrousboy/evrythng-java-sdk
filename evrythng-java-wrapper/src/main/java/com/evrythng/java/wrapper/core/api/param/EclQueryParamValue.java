/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "ecl"} query param.
 */
public class EclQueryParamValue extends QueryParamValue {

	public static final String NAME = "ecl";

	public EclQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param ecl
	 *            error correction level
	 * @return query parameter for this error correction level
	 */
	public static EclQueryParamValue ecl(String ecl) {
		return new EclQueryParamValue(ecl);
	}
}
