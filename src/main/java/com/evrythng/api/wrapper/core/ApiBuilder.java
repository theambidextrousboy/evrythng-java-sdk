/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.evrythng.api.wrapper.exception.BadRequestException;
import com.evrythng.api.wrapper.exception.ConflictException;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.api.wrapper.exception.EvrythngUnexpectedException;
import com.evrythng.api.wrapper.exception.ForbiddenException;
import com.evrythng.api.wrapper.exception.InternalErrorException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.exception.UnauthorizedException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * EVRYTHNG API commands builder.
 * 
 * @author Pedro De Almeida (almeidap)
 * 
 */
public class ApiBuilder {

	public static <T> Builder<T> post(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> responseType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPost(data), uri, responseStatus, responseType);
	}

	public static <T> Builder<T> get(String apiKey, URI uri, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpGet(), uri, responseStatus, returnType);
	}

	public static <T> Builder<T> put(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPut(data), uri, responseStatus, returnType);
	}

	public static Builder<Boolean> delete(String apiKey, URI uri, Status responseStatus) {
		// TODO: refactor this?
		return new Builder<Boolean>(apiKey, HttpMethodBuilder.httpDelete(), uri, responseStatus, new TypeReference<Boolean>() {
		}) {
			@Override
			public Boolean execute() throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException, EvrythngUnexpectedException,
					InternalErrorException {
				// Create a new client:
				HttpClient client = new DefaultHttpClient();
				try {
					// Execute request (response status code will be automatically checked):
					return command.execute(client) != null;
				} finally {
					command.shutdown(client);
				}
			};
		};
	}

	public static class Builder<T> extends CommandBuilder<T, Builder<T>> {

		/* Hide constructor */
		private Builder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
			super(apiKey, methodBuilder, uri, responseStatus, responseType);
		}

		public Builder<T> page(int page) {
			return queryParam(ApiConfiguration.QUERY_PARAM_PAGE, String.valueOf(page));
		}

		public Builder<T> perPage(int perPage) {
			return queryParam(ApiConfiguration.QUERY_PARAM_PER_PAGE, String.valueOf(perPage));
		}

		public Builder<T> from(long from) {
			return queryParam(ApiConfiguration.QUERY_PARAM_FROM, String.valueOf(from));
		}

		public Builder<T> to(long to) {
			return queryParam(ApiConfiguration.QUERY_PARAM_TO, String.valueOf(to));
		}
	}
}