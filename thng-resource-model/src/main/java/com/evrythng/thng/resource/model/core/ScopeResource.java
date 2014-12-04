/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO write javadoc here
 */
public class ScopeResource implements Serializable {

	private static final long serialVersionUID = -1514722768762632206L;
	private String[] users;
	private String[] projects;
	private String[] devices;
	public static final ScopeResource EMPTY = new ScopeResource();

	public ScopeResource() {

		users = new String[0];
		projects = new String[0];
		devices = new String[0];
	}

	public ScopeResource(final String[] users, final String[] projects, final String[] devices) {

		this.users = users;
		this.projects = projects;
		this.devices = devices;
	}

	public String[] getUsers() {

		return users != null ? Arrays.copyOf(users, users.length) : null;
	}

	public void setUsers(final String... users) {

		this.users = users;
	}

	public String[] getProjects() {

		return projects != null ? Arrays.copyOf(projects, projects.length) : null;
	}

	public void setProjects(final String... projects) {

		this.projects = projects;
	}

	public String[] getDevices() {

		return devices != null ? Arrays.copyOf(devices, devices.length) : null;
	}

	public void setDevices(final String... devices) {

		this.devices = devices;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (!(o instanceof ScopeResource)) {
			return false;
		}

		ScopeResource that = (ScopeResource) o;

		if (!asSet(devices).equals(asSet(that.devices))) {
			return false;
		}
		if (!asSet(projects).equals(asSet(that.projects))) {
			return false;
		}
		if (!asSet(users).equals(asSet(that.users))) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int result = asSet(users).hashCode();
		result = 31 * result + asSet(projects).hashCode();
		result = 31 * result + asSet(devices).hashCode();
		return result;
	}

	private Set<String> asSet(final String[] array) {

		Set<String> set = new HashSet<>();
		Collections.addAll(set, array);
		return set;
	}
}
