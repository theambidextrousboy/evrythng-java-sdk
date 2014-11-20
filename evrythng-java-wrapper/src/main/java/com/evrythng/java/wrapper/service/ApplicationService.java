/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.access.Application;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Methods for the application service
 * "/applications" endpoint. Api is implemented
 * in thng-access
 */
public class ApplicationService extends EvrythngServiceBase {

	public static final String QP_SOCIAL_NETWORK_NAME = "socialNetworkName";
	public static final String QP_SOCIAL_NETWORK_APP_ID = "socialNetworkAppId";
	public static final String PATH_APPLICATIONS = "/applications";
	public static final String PATH_APPLICATION = PATH_APPLICATIONS + "/%s";

	public ApplicationService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Creates a new {@link Application}
	 * <p>
	 * POST {@value #PATH_APPLICATIONS}
	 *
	 * @param app : App to create. Customer and Description are mandatory.
	 * @return the app created, including its brand-new "appApiKey" and its id
	 * to
	 * be used for further call of the service.
	 */
	public Builder<Application> applicationCreator(final Application app) throws EvrythngClientException {

		return post(PATH_APPLICATIONS, app, new TypeReference<Application>() {

		});
	}

	/**
	 * List all {@link Application}.
	 * <p>
	 * GET {@value #PATH_APPLICATIONS}
	 */
	public Builder<List<Application>> applicationsReader() throws EvrythngClientException {

		return get(PATH_APPLICATIONS, new TypeReference<List<Application>>() {

		});
	}

	/**
	 * Load a single {@link Application}.
	 * <p>
	 * GET {@value #PATH_APPLICATION}/:appId
	 */
	public Builder<Application> applicationReader(final String applicationId) throws EvrythngClientException {

		return get(String.format(PATH_APPLICATION, applicationId), new TypeReference<Application>() {

		});
	}

	/**
	 * Update a single {@link Application}.
	 * <p>
	 * PUT {@value #PATH_APPLICATION}/:appId
	 *
	 * @param app : input for field update. Will currently update the fields
	 *            description,
	 *            customer, and social networks.
	 * @return the updated data.
	 */
	public Builder<Application> applicationUpdater(final String appId, final Application app) throws EvrythngClientException {

		return put(String.format(PATH_APPLICATION, appId), app, new TypeReference<Application>() {

		});
	}

	/**
	 * Delete a single {@link Application}. All application users are also
	 * removed from
	 * the system.
	 */
	public Builder<Boolean> applicationDeleter(final String appId) throws EvrythngClientException {

		return delete(String.format(PATH_APPLICATION, appId));
	}

	/**
	 * Delete multiple {@link Application}s. All application users are also
	 * removed from
	 * the system.
	 */
	public Builder<List<Application>> applicationsReader(final String socialNetworkName, final String socialNetworkAppId) throws EvrythngClientException {

		return get(PATH_APPLICATIONS, new TypeReference<List<Application>>() {

		}).queryParam(QP_SOCIAL_NETWORK_NAME, socialNetworkName).queryParam(QP_SOCIAL_NETWORK_APP_ID, socialNetworkAppId);
	}
}
