/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "evrythngId"} query param.
 */
public class EvrythngIdQueryParamValue extends QueryParamValue {

	public static final String NAME = "evrythngId";

	public EvrythngIdQueryParamValue(final String value) {
		super(NAME, value);
	}

	/**
	 * @param id
	 *            evrythng id
	 * @return query parameter for this evrythng id
	 */
	public static EvrythngIdQueryParamValue id(final String id) {
		return new EvrythngIdQueryParamValue(id);
	}
}
