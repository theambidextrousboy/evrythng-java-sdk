/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * EVRYTHNG API commands builder.
 * 
 * @author Pedro De Almeida (almeidap)
 * 
 */
public class ApiBuilder {

	public static <T> Builder<T> post(String apiKey, URI uri, Object data, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPost(data), uri, returnType);
	}

	public static <T> Builder<T> get(String apiKey, URI uri, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpGet(), uri, returnType);
	}

	public static <T> Builder<T> put(String apiKey, URI uri, Object data, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPut(data), uri, returnType);
	}

	public static Builder<Boolean> delete(String apiKey, URI uri) {
		// TODO: refactor this?
		return new Builder<Boolean>(apiKey, HttpMethodBuilder.httpDelete(), uri, new TypeReference<Boolean>() {
		}) {
			@Override
			public Boolean execute() {
				// Create a new client:
				HttpClient client = new DefaultHttpClient();
				try {
					// Execute request:
					HttpResponse response = command.execute(client);

					// Check status code:
					return Boolean.valueOf(response.getStatusLine().getStatusCode() == Status.OK.getStatusCode());
				} finally {
					command.shutdown(client);
				}
			};
		};
	}

	public static class Builder<T> extends CommandBuilder<T, Builder<T>> {

		/* Hide default constructor */
		private Builder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, TypeReference<T> typeReference) {
			super(apiKey, methodBuilder, uri, typeReference);
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