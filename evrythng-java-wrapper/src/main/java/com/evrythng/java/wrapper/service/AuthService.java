/*
 * (c) Copyright 2012-2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.service;

import java.util.Map;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.access.Credentials;
import com.evrythng.thng.resource.model.store.User;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Methods for the authentication API.
 * End point /auth. Implemented by thng-access module.
 * 
 **/
public class AuthService extends EvrythngServiceBase {
	
	// ==== configuration ================================================== //

	public static final String PATH_AUTH = "/auth";
	public static final String PATH_AUTH_EVRYTHNG = PATH_AUTH + "/evrythng";
	public static final String PATH_AUTH_EVRYTHNG_USERS = PATH_AUTH_EVRYTHNG + "/users";
	public static final String PATH_AUTH_EVRYTHNG_USER_VALIDATE = PATH_AUTH_EVRYTHNG_USERS + "/%s/validate";
	
	public static final String PATH_AUTH_ALL = PATH_AUTH + "/all";
	public static final String PATH_AUTH_ALL_LOGOUT = PATH_AUTH_ALL + "/logout";

	// ==== structor ======================================================= //

	public AuthService(ApiManager apiManager) {
		super(apiManager);
	}
	
	// === requests ======================================================== //
	
	/**
	 * Register a new {@link User} in the system.
	 * 
	 * POST {@value #PATH_AUTH_EVRYTHNG_USERS}
	 * 
	 * @param user
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Credentials> evrythngUserCreator(User user) throws EvrythngClientException {
		return post(PATH_AUTH_EVRYTHNG_USERS, user, new TypeReference<Credentials>() {
		});
	}

	/**
	 * Activate a user.
	 * The activation code must match the one received during the registration
	 * process.
	 * 
	 * POST {@value #PATH_AUTH_EVRYTHNG_USER_VALIDATE}
	 * 
	 * @param userId
	 * @param activationCode
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Credentials> evrythngUserValidator(String userId, String activationCode) throws EvrythngClientException {
		Credentials validator = new Credentials();
		validator.setActivationCode(activationCode);
		return post(String.format(PATH_AUTH_EVRYTHNG_USER_VALIDATE, userId), validator, new TypeReference<Credentials>() {
		});
	}

	/**
	 * Authenticate a user.
	 * Based on the email + password pair.
	 * Engine provides the ApiKey of a successfuly authenticated user.
	 * 
	 * POST {@value #PATH_AUTH_EVRYTHNG}
	 * 
	 * @param credentials
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Credentials> evrythngUserAuthenticator(Credentials credentials) throws EvrythngClientException {
		return post(PATH_AUTH_EVRYTHNG, credentials, new TypeReference<Credentials>() {
		});
	}

	/**
	 * Logout. Invalidate user's key.
	 * 
	 * POST {@value #PATH_AUTH_ALL_LOGOUT}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Map<String, String>> authLogouter() throws EvrythngClientException {
		return post(PATH_AUTH_ALL_LOGOUT, null, new TypeReference<Map<String, String>>() {
		});
	}
}
