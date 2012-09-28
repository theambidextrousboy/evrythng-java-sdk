/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * EVRYTHNG API commands builder.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiBuilder {

	public static <T> Builder<T> post(String apiKey, URI uri, Object data, TypeReference<T> typeReference) {
		return new Builder<T>().request(apiKey, HttpMethodBuilder.httpPost(data), uri, typeReference);
	}

	public static <T> Builder<T> get(String apiKey, URI uri, TypeReference<T> typeReference) {
		return new Builder<T>().request(apiKey, HttpMethodBuilder.httpGet(), uri, typeReference);
	}

	public static <T> Builder<T> put(String apiKey, URI uri, Object data, TypeReference<T> typeReference) {
		return new Builder<T>().request(apiKey, HttpMethodBuilder.httpPut(data), uri, typeReference);
	}

	public static <T> Builder<T> delete(String apiKey, URI uri, TypeReference<T> typeReference) {
		return new Builder<T>().request(apiKey, HttpMethodBuilder.httpDelete(), uri, typeReference);
	}

	public static Builder<HttpResponse> delete(String apiKey, URI uri) {
		return new Builder<HttpResponse>().request(apiKey, HttpMethodBuilder.httpDelete(), uri, new TypeReference<HttpResponse>() {
		});
	}

	public static class Builder<T> {

		private ApiCommand<?, T> command;

		private Builder() {
			/* Hide default constructor */
		}

		private <M extends HttpRequestBase> Builder<T> request(String apiKey, MethodBuilder<M> methodBuilder, URI uri, TypeReference<T> typeReference) {
			this.command = new ApiCommand<M, T>(apiKey, methodBuilder, uri, typeReference);
			return this;
		}

		/**
		 * @param token
		 * @return
		 */
		public Builder<T> authorization(String apiKey) {
			return header("Authorization", apiKey);
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

		public Builder<T> queryParam(String name, String value) {
			command.setQueryParam(name, value);
			return this;
		}

		public Builder<T> header(String key, String value) {
			command.setHeader(key, value);
			return this;
		}

		/**
		 * @return
		 */
		public T execute() {
			return command.execute();
		}

		/**
		 * @return
		 */
		public int count() {
			return command.count();
		}
	}
}