/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Abstract model representation for resources.
 *
 * @author Pedro De Almeida (almeidap)
 * @author Dominique Guinard (domguinard)
 *
 */
public abstract class ResourceModel {

	protected String id;
	@Past
	@NotNull
	protected Long createdAt;
	protected Map<String, String> customFields;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Map<String, String> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(Map<String, String> customFields) {
		this.customFields = customFields;
	}

	public void addCustomFields(String key, String value) {
		if (customFields == null) {
			customFields = new HashMap<String, String>();
		}
		customFields.put(key, value);
	}
}
