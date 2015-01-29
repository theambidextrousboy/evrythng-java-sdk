/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.content;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.Map;

/**
 * Rest model for a content.
 **/
public abstract class Content extends DurableResourceModel {

	private static final long serialVersionUID = -3240773220883299927L;

	private String name;
	private String description;
	private Map<String, Media> media;

	public Content() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Media> getMedia() {
		return media;
	}

	public void setMedia(Map<String, Media> media) {
		this.media = media;
	}

}
