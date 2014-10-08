/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.evrythng.commons.annotations.csv.CsvTransient;

/**
 * Abstract model representation for resources.
 */
public abstract class ResourceModel implements Serializable {

	private static final long serialVersionUID = -2842591513551534611L;

	public static final String FIELD_ID = "id";
	private String id;

	private Long createdAt;
	private Map<String, String> customFields;
	private List<String> tags;

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

	@CsvTransient
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
