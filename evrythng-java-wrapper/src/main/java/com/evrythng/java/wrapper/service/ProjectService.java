/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Project;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Service wrapper for the {@code /projects} endpoint of the EVRYTHNG API.
 */
public class ProjectService extends EvrythngServiceBase {

	private static final String PATH_PROJECTS = "/projects";
	private static final String PATH_PROJECT = PATH_PROJECTS + "/%s";

	/**
	 * @param apiManager {@link ApiManager} instance
	 */
	public ProjectService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Creates a new {@link Project}
	 * <p>
	 * POST {@value #PATH_PROJECTS}
	 *
	 * @param project {@link Project} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectCreator(final Project project) throws EvrythngClientException {

		return post(PATH_PROJECTS, project, new TypeReference<Project>() {

		});
	}

	/**
	 * Retrieves list of all {@link Project} resources.
	 * <p>
	 * GET {@value #PATH_PROJECTS}
	 *
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<List<Project>> projectsReader() throws EvrythngClientException {

		return get(PATH_PROJECTS, new TypeReference<List<Project>>() {

		});
	}

	/**
	 * Retrieves the referenced {@link Project}
	 * <p>
	 * GET {@value #PATH_PROJECT}
	 *
	 * @param id project id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectReader(final String id) throws EvrythngClientException {

		return get(String.format(PATH_PROJECT, id), new TypeReference<Project>() {

		});
	}

	/**
	 * Updates the referenced {@link Project}
	 * <p>
	 * PUT {@value #PATH_PROJECT}
	 *
	 * @param id      project id
	 * @param project {@link Project} instance
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Project> projectUpdater(final String id, final Project project) throws EvrythngClientException {

		return put(String.format(PATH_PROJECT, id), project, new TypeReference<Project>() {

		});
	}

	/**
	 * Deletes the referenced {@link Project}
	 * <p>
	 * DELETE {@value #PATH_PROJECT}
	 *
	 * @param id project id
	 * @return a preconfigured {@link Builder}
	 */
	public Builder<Boolean> projectDeleter(final String id) throws EvrythngClientException {

		return delete(String.format(PATH_PROJECT, id));
	}
}
