/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "tpl"} query param.
 */
public class TplQueryParamValue extends QueryParamValue {

	public static final String NAME = "tpl";
	private static final String DEFAULT_SHORTID = "default-shortid";
	private static final String DEFAULT = "default";
	private static final String DEMO_EVRYTHNG = "demo-evrythng";

	public TplQueryParamValue(String value) {
		super(NAME, value);
	}

	/**
	 * @param template
	 *            template
	 * @return query parameter for this template
	 */
	public static TplQueryParamValue template(String template) {
		return new TplQueryParamValue(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "default-shortid"}
	 */
	public static boolean isDefaultShortId(String template) {
		return DEFAULT_SHORTID.equals(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "default"}
	 */
	public static boolean isDefault(String template) {
		return DEFAULT.equals(template);
	}

	/**
	 * @param template
	 *            template
	 * @return true if app id equals {@code "demo-evrythng"}
	 */
	public static boolean isDemoEvrythng(String template) {
		return DEMO_EVRYTHNG.equals(template);
	}
}
