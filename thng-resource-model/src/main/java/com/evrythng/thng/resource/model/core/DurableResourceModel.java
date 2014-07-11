/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

/**
 * Abstract model representation for <em>updatable</em> resources.
 **/
public abstract class DurableResourceModel extends ResourceModel {

	protected Long updatedAt;

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
}
