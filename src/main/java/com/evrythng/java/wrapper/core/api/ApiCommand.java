/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

import com.evrythng.java.wrapper.core.http.HttpMethodBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.BadRequestException;
import com.evrythng.java.wrapper.exception.ConflictException;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.exception.EvrythngUnexpectedException;
import com.evrythng.java.wrapper.exception.ForbiddenException;
import com.evrythng.java.wrapper.exception.InternalErrorException;
import com.evrythng.java.wrapper.exception.NotFoundException;
import com.evrythng.java.wrapper.exception.UnauthorizedException;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.java.wrapper.util.URIBuilder;
import com.evrythng.thng.resource.model.exception.ErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Generic definition for API commands.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiCommand<T> {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommand.class);

	protected LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
	protected Map<String, String> headers = new LinkedHashMap<String, String>();

	protected MethodBuilder<?> methodBuilder;
	protected URI uri;
	protected Status responseStatus;
	protected TypeReference<T> responseType;

	/**
	 * Creates a new instance of {@link ApiCommand}.
	 * 
	 * @param methodBuilder the {@link MethodBuilder} used for creating the request
	 * @param uri the {@link URI} holding the absolute URL
	 * @param responseStatus the expected {@link HttpResponse} status
	 * @param responseType the native type to which the {@link HttpResponse} will be mapped to
	 */
	public ApiCommand(MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.methodBuilder = methodBuilder;
		this.uri = uri;
		this.responseStatus = responseStatus;
		this.responseType = responseType;
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to {@code T} specified by
	 * {@link ApiCommand#responseType}.
	 * 
	 * @see #execute(TypeReference)
	 * @return the {@link HttpResponse} entity mapped to {@code T}
	 * @throws EvrythngException
	 */
	public T execute() throws EvrythngException {
		return execute(responseType);
	}

	/**
	 * Executes the current command and returns the native {@link HttpResponse}.
	 * 
	 * @see #execute(TypeReference)
	 * @return the {@link HttpResponse} implied by the request
	 * @throws EvrythngException
	 */
	public HttpResponse request() throws EvrythngException {
		return execute(new TypeReference<HttpResponse>() {
		});
	}

	/**
	 * Executes the current command and outputs the {@link HttpResponse} entity body.
	 * 
	 * @see #execute(TypeReference)
	 * @return the {@link HttpResponse} entity body
	 * @throws EvrythngException
	 */
	public String output() throws EvrythngException {
		return execute(new TypeReference<String>() {
		});
	}

	/**
	 * Executes the current command using the HTTP {@code HEAD} method and returns the value of the first
	 * {@link HttpResponse} {@link Header} specified by {@code headerName}. This method is usefull for obtaining
	 * metainformation about the {@link HttpResponse} implied by the request without transferring the entity-body.
	 * 
	 * FIXME: HEAD not supported for now, using GET instead
	 * 
	 * @see HttpResponse#getFirstHeader(String)
	 * 
	 * @param headerName the {@link HttpResponse} header to be retrieved
	 * @return the value of the first retrieved {@link HttpResponse} header or null if no such header could be found.
	 * @throws EvrythngException
	 */
	public Header head(String headerName) throws EvrythngException {
		HttpResponse response = execute(HttpMethodBuilder.httpGet(), new TypeReference<HttpResponse>() {
		});
		logger.debug("Retrieving first header: [name={}]", headerName);
		return response.getFirstHeader(headerName);
	}

	/**
	 * Sets (adds or overwrittes) the specified request header.
	 * 
	 * @param name the request header name
	 * @param value the request header value
	 */
	public void setHeader(String name, String value) {
		logger.debug("Setting header: [name={}, value={}]", name, value);
		headers.put(name, value);
	}

	/**
	 * Removes the specified request header.
	 * 
	 * @param name the name of the request header to be removed
	 */
	public void removeHeader(String name) {
		logger.debug("Removing header: [name={}]", name);
		headers.remove(name);
	}

	/**
	 * Sets (adds or overwrittes) the specified query parameter.
	 * 
	 * @param name the query parameter name
	 * @param value the query parameter value
	 */
	public void setQueryParam(String name, String value) {
		logger.debug("Setting query parameter: [name={}, value={}]", name, value);
		queryParams.set(name, value);
	}

	/**
	 * Removes the specified query parameter.
	 * 
	 * @param name the name of the query parameter to be removed
	 */
	public void removeQueryParam(String name) {
		logger.debug("Removing query parameter: [name={}]", name);
		queryParams.remove(name);
	}

	private <K> K execute(TypeReference<K> type) throws EvrythngException {
		// Delegate:
		return execute(methodBuilder, type);
	}

	private <K> K execute(MethodBuilder<?> method, TypeReference<K> type) throws EvrythngException {
		// Delegate:
		return execute(method, responseStatus, type);
	}

	@SuppressWarnings("unchecked")
	private <K> K execute(MethodBuilder<?> method, Status expectedStatus, TypeReference<K> type) throws EvrythngException {
		HttpClient client = new DefaultHttpClient();
		try {
			K result = null;
			HttpResponse response = null;

			try {
				HttpUriRequest request = buildRequest(method);
				logger.debug(">> Executing request: [method={}, url={}]", request.getMethod(), request.getURI().toString());
				response = client.execute(request);
			} catch (Exception e) {
				// Convert to custom exception:
				throw new EvrythngClientException("Unable to execute request!", e);
			}

			// Retrieve response entity (as String so that it can be outputted in case of exception):
			String entity = readEntity(response);

			Status actualStatus = Status.fromStatusCode(response.getStatusLine().getStatusCode());
			if (actualStatus == null) {
				throw new EvrythngUnexpectedException(new ErrorMessage(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Unknown status code " + response.getStatusLine().getStatusCode()));
			}
			logger.debug("<< Response received: [status={expected={}, actual={}}]", expectedStatus.getStatusCode(), actualStatus.getStatusCode());

			if (!actualStatus.equals(expectedStatus)) {
				logger.debug("Unexpected status code, mapping response to ErrorMessage: [entity={}]", entity);

				// Map to ErrorMessage:
				ErrorMessage message = null;
				try {
					// API should always return an ErrorMessage as JSON:
					message = JSONUtils.read(entity, new TypeReference<ErrorMessage>() {
					});
				} catch (Exception e) {
					throw new EvrythngClientException(String.format("Unable to retrieve ErrorMessage from response: [entity=%s]", entity), e);
				}

				// Handle unexpected status:
				switch (actualStatus.getFamily()) {
					case CLIENT_ERROR:
						switch (actualStatus) {
							case BAD_REQUEST:
								throw new BadRequestException(message);
							case UNAUTHORIZED:
								throw new UnauthorizedException(message);
							case FORBIDDEN:
								throw new ForbiddenException(message);
							case NOT_FOUND:
								throw new NotFoundException(message);
							case CONFLICT:
								throw new ConflictException(message);
							default:
								throw new EvrythngUnexpectedException(message);
						}
					case SERVER_ERROR:
						throw new InternalErrorException(message);
					default:
						throw new EvrythngUnexpectedException(message);
				}
			} else {
				if (type.getType().equals(HttpResponse.class)) {
					// We already have a HttpResponse, let's return it:
					result = (K) response;
				} else if (type.getType().equals(String.class)) {
					result = (K) entity;
				} else {
					try {
						result = JSONUtils.read(entity, type);
					} catch (Exception e) {
						throw new EvrythngClientException(String.format("Unable to map response entity: [type=%s, entity=%s]", type.getType(), entity), e);
					}
				}
			}
			return result;
		} finally {
			shutdown(client);
		}
	}

	/**
	 * Builds and prepares the {@link HttpUriRequest}.
	 * 
	 * @param method the {@link MethodBuilder} used to build the request
	 * @return the prepared {@link HttpUriRequest} for execution
	 * @throws EvrythngClientException
	 */
	private HttpUriRequest buildRequest(MethodBuilder<?> method) throws EvrythngClientException {

		// Build request method:
		HttpUriRequest request = method.build(buildUri());

		// Define client headers:
		for (Entry<String, String> header : headers.entrySet()) {
			request.setHeader(header.getKey(), header.getValue());
		}

		return request;
	}

	/**
	 * Builds the final {@link URI} using {@link ApiCommand#uri} as base URL and all defined {@link ApiCommand#queryParams}
	 * as query parameters.
	 * 
	 * @return the absolute URI
	 * @throws EvrythngClientException
	 */
	private URI buildUri() throws EvrythngClientException {
		return URIBuilder.fromUri(uri.toString()).queryParams(queryParams).build();
	}

	/**
	 * Reads entity content from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * @return the {@link HttpResponse} entity content as {@link String}
	 * @throws EvrythngClientException
	 */
	private String readEntity(HttpResponse response) throws EvrythngClientException {
		HttpEntity entity = response.getEntity();
		logger.debug("Reading response entity...");

		String result = null;
		try {
			result = IOUtils.toString(entity.getContent());
		} catch (Exception e) {
			// Convert to custom exception:
			throw new EvrythngClientException(String.format("Error while reading response entity! [type=%s]", String.class), e);
		}
		return result;
	}

	/**
	 * Shuts down the connection manager to ensure immediate deallocation of all system resources.
	 * 
	 * @param client the {@link HttpClient} to shut down
	 */
	protected void shutdown(HttpClient client) {
		client.getConnectionManager().shutdown();
	}
}
