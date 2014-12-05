/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;

/**
 * TODO write javadoc here
 */
public class AccessScope implements Serializable {

	private static final long serialVersionUID = 5211679027614774512L;
	public static final AccessScope EMPTY = new AccessScope();
	private String user;
	private String project;
	private String device;

	public AccessScope() {

		// for serialization's sake
	}

	public AccessScope(final String user, final String project, final String device) {

		if (user != null && project == null) {
			throw new IllegalArgumentException("Invalid scope: user cannot be in scope without project");
		}
		this.user = user;
		this.project = project;
		this.device = device;
	}

	public String getUser() {

		return user;
	}

	public void setUser(final String user) {

		this.user = user;
	}

	public String getProject() {

		return project;
	}

	public void setProject(final String project) {

		this.project = project;
	}

	public String getDevice() {

		return device;
	}

	public void setDevice(final String device) {

		this.device = device;
	}

	public boolean hasProject() {

		return project != null;
	}

	public boolean hasDevice() {

		return device != null;
	}

	public boolean hasUser() {

		return user != null && hasProject();
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (!(o instanceof AccessScope)) {
			return false;
		}

		AccessScope that = (AccessScope) o;

		if (device != null ? !device.equals(that.device) : that.device != null) {
			return false;
		}
		if (project != null ? !project.equals(that.project) : that.project != null) {
			return false;
		}
		if (user != null ? !user.equals(that.user) : that.user != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int result = user != null ? user.hashCode() : 0;
		result = 31 * result + (project != null ? project.hashCode() : 0);
		result = 31 * result + (device != null ? device.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("SingleScopeResource{");
		sb.append("user='").append(user).append('\'');
		sb.append(", project='").append(project).append('\'');
		sb.append(", device='").append(device).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
