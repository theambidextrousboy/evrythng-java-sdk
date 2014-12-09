/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>projects</em>.
 */
public class Project extends DurableResourceModel {

	private static final long serialVersionUID = -2362864476419100132L;
	private String name;
	private String description;
	private String imageUrl;
	private Long startAt;
	private Long endAt;

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {

		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {

		this.description = description;
	}

	/**
	 * @return the url of the image
	 */
	public String getImageUrl() {

		return imageUrl;
	}

	/**
	 * @param imageUrl the url of the image to set
	 */
	public void setImageUrl(final String imageUrl) {

		this.imageUrl = imageUrl;
	}

	/**
	 * @return the startAt
	 */
	public Long getStartAt() {

		return startAt;
	}

	/**
	 * @param startAt the startAt to set
	 */
	public void setStartAt(final Long startAt) {

		this.startAt = startAt;
	}

	/**
	 * @return the endAt
	 */
	public Long getEndAt() {

		return endAt;
	}

	/**
	 * @param endAt the endAt to set
	 */
	public void setEndAt(final Long endAt) {

		this.endAt = endAt;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Project{");
		sb.append("name='").append(name).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", imageUrl='").append(imageUrl).append('\'');
		sb.append(", startAt=").append(startAt);
		sb.append(", endAt=").append(endAt);
		sb.append('}');
		return sb.toString();
	}
}
