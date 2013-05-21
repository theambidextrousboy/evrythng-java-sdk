/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.util.List;

/**
 * Abstract model representation for <em>updatable</em> resources.
 * 
 * @author Pedro De Almeida (almeidap)
 * @author Michel Yerly (my)
 **/
public abstract class DurableResourceModel extends ResourceModel {

	protected Long updatedAt;

	private List<String> tags;

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
