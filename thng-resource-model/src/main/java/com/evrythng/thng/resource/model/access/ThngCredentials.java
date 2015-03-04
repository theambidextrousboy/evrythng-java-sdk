/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.access;

/**
 * Access credentials of an {@link com.evrythng.thng.resource.model.store.Thng}.
 */
public class ThngCredentials {

	private String thngId;
	private String thngApiKey;

	/**
	 * @return the thng id
	 */
	public String getThngId() {

		return thngId;
	}

	/**
	 * @param thngId the thng id to provide access to
	 */
	public void setThngId(final String thngId) {

		this.thngId = thngId;
	}

	/**
	 * @return the thng api key
	 */
	public String getThngApiKey() {

		return thngApiKey;
	}

	/**
	 * @param thngApiKey provided api key of the thing
	 */
	public void setThngApiKey(final String thngApiKey) {

		this.thngApiKey = thngApiKey;
	}
}
