/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import org.apache.commons.lang3.StringUtils;

/**
 * Provides support for the {@code ids} query param.
 */
public class IdsQueryParamValue extends QueryParamValue {

	public static final String NAME = "ids";

	public IdsQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param ids comma separated ids
	 * @return query parameter for this ids
	 */
	public static IdsQueryParamValue ids(final String ids) {

		return new IdsQueryParamValue(ids);
	}

	/**
	 * @param ids collection of ids
	 * @return query parameter for this ids
	 */
	public static IdsQueryParamValue ids(final Iterable<String> ids) {

		return new IdsQueryParamValue(StringUtils.join(ids, ","));
	}
}
