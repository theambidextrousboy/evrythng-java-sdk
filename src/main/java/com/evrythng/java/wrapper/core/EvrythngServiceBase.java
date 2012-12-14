/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import java.net.URI;

import org.apache.http.HttpResponse;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.util.URIBuilder;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Base definition for EVRYTHNG API services.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class EvrythngServiceBase {

	private final ApiConfiguration config;

	/**
	 * Creates a new instance of {@link EvrythngServiceBase} using the provided {@link ApiConfiguration}.
	 * 
	 * @param config the {@link ApiConfiguration} for accessing the API service.
	 */
	public EvrythngServiceBase(ApiConfiguration config) {
		this.config = config;
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing POST requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath the relative path of the API endpoint. It will be appended to {@link ApiConfiguration#getUrl()} in
	 *        order to build the absolute endpoint URL.
	 * @param data the content data that will be associated with the POST request
	 * @param type the {@link TypeReference} for mapping the {@link HttpResponse} entity.
	 * @return a preconfigured {@link Builder} for executing POST requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> post(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return EvrythngApiBuilder.post(config.getKey(), absoluteUri(relativePath), data, Status.CREATED, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing GET requests.
	 * 
	 * @see EvrythngApiBuilder#get(String, URI, Status, TypeReference)
	 * @param relativePath the relative path of the API endpoint. It will be appended to {@link ApiConfiguration#getUrl()} in
	 *        order to build the absolute endpoint URL.
	 * @param type the {@link TypeReference} for mapping the {@link HttpResponse} entity.
	 * @return a preconfigured {@link Builder} for executing GET requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> get(String relativePath, TypeReference<T> type) throws EvrythngClientException {
		return EvrythngApiBuilder.get(config.getKey(), absoluteUri(relativePath), Status.OK, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing PUT requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath the relative path of the API endpoint. It will be appended to {@link ApiConfiguration#getUrl()} in
	 *        order to build the absolute endpoint URL.
	 * @param data the content data that will be associated with the PUT request
	 * @param type the {@link TypeReference} for mapping the {@link HttpResponse} entity.
	 * @return a preconfigured {@link Builder} for executing PUT requests
	 * @throws EvrythngClientException
	 */
	public <T> Builder<T> put(String relativePath, Object data, TypeReference<T> type) throws EvrythngClientException {
		return EvrythngApiBuilder.put(config.getKey(), absoluteUri(relativePath), data, Status.OK, type);
	}

	/**
	 * Returns a preconfigured {@link Builder} for executing DELETE requests.
	 * 
	 * @see EvrythngApiBuilder#post(String, URI, Object, Status, TypeReference)
	 * @param relativePath the relative path of the API endpoint. It will be appended to {@link ApiConfiguration#getUrl()} in
	 *        order to build the absolute endpoint URL.
	 * @return a preconfigured {@link Builder} for executing DELETE requests
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> delete(String relativePath) throws EvrythngClientException {
		return EvrythngApiBuilder.delete(config.getKey(), absoluteUri(relativePath), Status.OK);
	}

	/**
	 * Builds an absolute {@link URI} using the provided {@code relativePath} and the predefined
	 * {@link ApiConfiguration#getUrl()}.
	 * 
	 * @param relativePath the relative path that will be appended to {@link ApiConfiguration#getUrl()} in order to build the
	 *        absolute endpoint URL.
	 * @return the absolute endpoint {@link URI}
	 * @throws EvrythngClientException
	 */
	protected URI absoluteUri(String relativePath) throws EvrythngClientException {
		String path = relativePath.startsWith("/") ? relativePath : String.format("/%s", relativePath);
		return URIBuilder.fromUri(String.format("%s%s", config.getUrl(), path)).build();
	}

	public ApiConfiguration getConfig() {
		return config;
	}
}
