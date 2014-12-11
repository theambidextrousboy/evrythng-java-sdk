/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.io.Serializable;

/**
 * Model class for a timestamp.
 */
public class Timestamp implements Serializable {

	private static final long serialVersionUID = -2460519576290598569L;
	private Long timestamp;

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {

		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(final Long timestamp) {

		this.timestamp = timestamp;
	}
}
