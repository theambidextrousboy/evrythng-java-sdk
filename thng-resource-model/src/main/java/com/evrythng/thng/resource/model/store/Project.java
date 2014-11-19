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
	private Long startsAt;
	private Long endsAt;

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
	 * @return the startsAt
	 */
	public Long getStartsAt() {

		return startsAt;
	}

	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(final Long startsAt) {

		this.startsAt = startsAt;
	}

	/**
	 * @return the endsAt
	 */
	public Long getEndsAt() {

		return endsAt;
	}

	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(final Long endsAt) {

		this.endsAt = endsAt;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Project{");
		sb.append("name='").append(name).append('\'');
		sb.append(", startsAt=").append(startsAt);
		sb.append(", endsAt=").append(endsAt);
		sb.append('}');
		return sb.toString();
	}
}
