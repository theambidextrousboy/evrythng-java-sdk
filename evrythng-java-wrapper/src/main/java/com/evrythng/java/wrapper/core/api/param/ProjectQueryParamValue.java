/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;
import org.apache.commons.lang3.StringUtils;

/**
 * Provides support for the {@code project} query param.
 */
public class ProjectQueryParamValue extends QueryParamValue {

	public static final String NAME = "project";

	private ProjectQueryParamValue(final String value) {

		super(NAME, value);
	}

	/**
	 * @param project project id
	 *
	 * @return query parameter for this app id
	 */
	public static ProjectQueryParamValue project(final String project) {

		return new ProjectQueryParamValue(project);
	}

	/**
	 * @param project project id
	 *
	 * @return true if the project is null or empty.
	 */
	public static boolean isNoProject(final String project) {

		return StringUtils.isEmpty(project);
	}
}
