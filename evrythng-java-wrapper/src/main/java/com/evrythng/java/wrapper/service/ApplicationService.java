/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import java.util.List;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.access.Application;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Methods for the application service
 * "/applications" endpoint. Api is implemented
 * in thng-access
 * 
 **/
public class ApplicationService extends EvrythngServiceBase {

	// ==== configuration ================================================== //

	public static final String PATH_APPLICATIONS = "/applications";
	public static final String PATH_APPLICATION = PATH_APPLICATIONS + "/%s";

	// ==== structor ======================================================= //

	public ApplicationService(ApiManager apiManager) {
		super(apiManager);
	}

	// ==== CRUD on applications =========================================== //

	/**
	 * Creates a new {@link Application}
	 * 
	 * POST {@value #PATH_APPLICATIONS}
	 * 
	 * @param app
	 *            : App to create. Customer and Description are mandatory.
	 * @return the app created, including its brand-new "appApiKey" and its id
	 *         to
	 *         be used for further call of the service.
	 * @throws EvrythngClientException
	 */
	public Builder<Application> applicationCreator(Application app) throws EvrythngClientException {
		return post(PATH_APPLICATIONS, app, new TypeReference<Application>() {
		});
	}

	/**
	 * List all {@link Application}.
	 * 
	 * GET {@value #PATH_APPLICATIONS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Application>> applicationsReader() throws EvrythngClientException {
		return get(PATH_APPLICATIONS, new TypeReference<List<Application>>() {
		});
	}

	/**
	 * Load a single {@link Application}.
	 * 
	 * GET {@value #PATH_APPLICATION}/:appId
	 * 
	 * @param appId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Application> applicationReader(String appId) throws EvrythngClientException {
		return get(String.format(PATH_APPLICATION, appId), new TypeReference<Application>() {
		});
	}

	/**
	 * Update a single {@link Application}.
	 * 
	 * PUT {@value #PATH_APPLICATION}/:appId
	 * 
	 * @param appId
	 * @param app
	 *            : input for field update. Will currently update the fields
	 *            description,
	 *            customer, and social networks.
	 * @return the updated data.
	 * @throws EvrythngClientException
	 */
	public Builder<Application> applicationUpdater(String appId, Application app) throws EvrythngClientException {
		return put(String.format(PATH_APPLICATION, appId), app, new TypeReference<Application>() {
		});
	}

	/**
	 * Delete a single {@link Application}. All application users are also
	 * removed from
	 * the system.
	 * 
	 * @param appId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> applicationDeleter(String appId) throws EvrythngClientException {
		return delete(String.format(PATH_APPLICATION, appId));
	}
}
