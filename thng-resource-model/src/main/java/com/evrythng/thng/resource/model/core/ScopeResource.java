/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the scopes for a resource.
 */
public class ScopeResource implements Serializable {

	public static final String ALL_USERS = "allU";
	public static final String ALL_PROJECTS = "allP";

	private static final long serialVersionUID = -1514722768762632206L;
	private List<String> users;
	private List<String> projects;
	public static final ScopeResource EMPTY = new ScopeResource();

	public ScopeResource() {

		users = new ArrayList<>();
		projects = new ArrayList<>();
	}

	public ScopeResource(final List<String> users, final List<String> projects) {

		this.users = users;
		this.projects = projects;
	}

	public List<String> getUsers() {

		return users;
	}

	public void setUsers(final List<String> users) {

		this.users = users;
	}

	public List<String> getProjects() {

		return projects;
	}

	public void setProjects(final List<String> projects) {

		this.projects = projects;
	}
}
