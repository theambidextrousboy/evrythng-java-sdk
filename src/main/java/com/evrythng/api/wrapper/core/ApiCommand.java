package com.evrythng.api.wrapper.core;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.exception.InternalErrorException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.evrythng.api.wrapper.util.URIBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

public class ApiCommand<M extends HttpRequestBase, T> {

	private static final Logger logger = LoggerFactory.getLogger(ApiCommand.class);

	protected LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
	protected Map<String, String> headers = new LinkedHashMap<String, String>();

	protected String apiKey;
	protected MethodBuilder<M> methodBuilder;
	protected URI uri;
	protected TypeReference<T> type;

	public ApiCommand(String apiKey, MethodBuilder<M> methodBuilder, URI uri, TypeReference<T> type) {
		this.apiKey = apiKey;
		this.methodBuilder = methodBuilder;
		this.uri = uri;
		this.type = type;
	}

	/**
	 * Execute remote API method and unmarshall the result to its native type.
	 * 
	 * @return Instance of result type.
	 */
	@SuppressWarnings("unchecked")
	public T execute() {
		// Create a new client:
		HttpClient client = new DefaultHttpClient();
		T result = null;

		try {
			// Build request method:
			M request = methodBuilder.build(buildUri());

			// Execute request:
			HttpResponse response = this.execute(client, request);

			if (type.getType().equals(HttpResponse.class)) {
				// We already have a HttpResponse, let's return it:
				result = (T) response;
			} else {
				try {
					HttpEntity entity = response.getEntity();
					result = JSONUtils.read(entity.getContent(), type);
				} catch (Exception e) {
					throw new EvrythngException(String.format("Error while mapping response entity! [type=%s]", type.getType()), e);
				}
			}
		} finally {
			shutdown(client);
		}

		return result;
	}

	/*
	 * FIXME: check usefulness & validity of this!
	 */
	public int count() {
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = execute(client, new HttpGet(buildUri()));
			Header xResultCountHeader = this.getHttpHeader(response, ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			return Integer.valueOf(xResultCountHeader.getValue());
		} finally {
			shutdown(client);
		}
	}

	/**
	 * Shuts down the connection manager to ensure immediate deallocation of all system resources.
	 * 
	 * @param client
	 */
	private void shutdown(HttpClient client) {
		client.getConnectionManager().shutdown();
	}

	public void setHeader(String key, String value) {
		headers.put(key, value);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setQueryParam(String key, String value) {
		queryParams.set(key, value);
	}

	/**
	 * @param request
	 */
	protected void prepare(HttpUriRequest request) {
		// Define required API headers:
		request.setHeader("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
		request.setHeader("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
		request.setHeader("Authorization", apiKey);

		// Define client headers:
		for (Entry<String, String> header : headers.entrySet()) {
			request.setHeader(header.getKey(), header.getValue());
		}
	}

	protected URI buildUri() {
		return URIBuilder.fromUri(uri.toString()).queryParams(queryParams).build();
	}

	protected Header getHttpHeader(HttpResponse response, String name) {
		logger.debug("Retrieving HTTP header: [name={}]", name);
		return response.getFirstHeader(name);
	}

	/**
	 * TODO
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	private static EvrythngException createException(int code, String message) {
		switch (code) {
			case 400:
				return new NotFoundException(message);
			case 500:
				return new InternalErrorException(message);

		}
		return new EvrythngException(message);
	}

	private HttpResponse execute(HttpClient client, HttpRequestBase request) {
		logger.debug("Executing request: [method={}, url={}]", request.getMethod(), request.getURI().toString());

		try {
			prepare(request);
			HttpResponse response = client.execute(request);

			// TODO
			// int statusCode = response.getStatusLine().getStatusCode();
			// if (statusCode != XYZ)
			// createException(statusCode, unmarshall(entity.getContent(), new TypeReference<ErrorMessage>() {}));

			return response;
		} catch (Exception e) {
			throw new EvrythngException("Unable to execute request!", e);
		}
	}
}
