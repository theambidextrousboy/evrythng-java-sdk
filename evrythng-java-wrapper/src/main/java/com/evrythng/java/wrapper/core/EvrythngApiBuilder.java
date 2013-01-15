/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.java.wrapper.core.api.ApiCommand;
import com.evrythng.java.wrapper.core.api.ApiCommandBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder;
import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.commons.config.ApiConfiguration.QueryKeyword;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Entry-point command builder for the EVRYTHNG API.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public final class EvrythngApiBuilder {

	private static final Logger logger = LoggerFactory.getLogger(EvrythngApiBuilder.class);

	/**
	 * Protected constructor, use one of the static methods to obtain an
	 * instance.
	 */
	protected EvrythngApiBuilder() {
	}

	/**
	 * Creates a {@link Builder} for executing a {@code POST} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> post(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> responseType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPost(data), uri, responseStatus, responseType);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code GET} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> get(String apiKey, URI uri, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpGet(), uri, responseStatus, returnType);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code PUT} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param data
	 *            the content data that will be associated with the POST request
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @param responseType
	 *            the native type to which the {@link HttpResponse} will be
	 *            mapped to
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static <T> Builder<T> put(String apiKey, URI uri, Object data, Status responseStatus, TypeReference<T> returnType) {
		return new Builder<T>(apiKey, HttpMethodBuilder.httpPut(data), uri, responseStatus, returnType);
	}

	/**
	 * Creates a {@link Builder} for executing a {@code DELETE} request.
	 * 
	 * @param apiKey
	 *            the authorization token for accessing the EVRYTHNG API
	 * @param uri
	 *            the {@link URI} holding the absolute URL
	 * @param responseStatus
	 *            the expected {@link HttpResponse} status
	 * @return an EVRYTHNG API-ready {@link Builder}
	 */
	public static Builder<Boolean> delete(String apiKey, URI uri, Status responseStatus) {
		return new Builder<Boolean>(apiKey, HttpMethodBuilder.httpDelete(), uri, responseStatus, new TypeReference<Boolean>() {
		}) {
			/**
			 * {@code true} if the request has been successfully executed (i.e.
			 * returned response status code equals {@link Status#OK}),
			 * {@code false} otherwise
			 */
			@Override
			public Boolean execute() throws EvrythngException {
				// Perform request (response status code will be automatically checked):
				return request() != null;
			};
		};
	}

	/**
	 * Default command builder for the EVRYTHNG API.
	 * 
	 * @author Pedro De Almeida (almeidap)
	 */
	public static class Builder<T> extends ApiCommandBuilder<T, Builder<T>> {

		/**
		 * Private constructor, use {@link EvrythngApiBuilder} static methods
		 * for creating a {@link Builder}.
		 */
		private Builder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
			super(methodBuilder, uri, responseStatus, responseType);

			// Define required API metainformation:
			header("Content-Type", ApiConfiguration.HTTP_CONTENT_TYPE);
			header("Accept", ApiConfiguration.HTTP_ACCEPT_TYPE);
			apiKey(apiKey);
		}

		public Builder<T> apiKey(String apiKey) {
			return header(ApiConfiguration.HTTP_HEADER_AUTHORIZATION, apiKey);
		}

		public Builder<T> search(String pattern) {
			return queryParam(ApiConfiguration.QUERY_PARAM_SEARCH, pattern);
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

		public Builder<T> from(String from) {
			return queryParam(ApiConfiguration.QUERY_PARAM_FROM, from);
		}

		public Builder<T> from(QueryKeyword queryKeyword) {
			return queryParam(ApiConfiguration.QUERY_PARAM_FROM, queryKeyword.toString());
		}

		public Builder<T> to(long to) {
			return queryParam(ApiConfiguration.QUERY_PARAM_TO, String.valueOf(to));
		}

		public Builder<T> to(String to) {
			return queryParam(ApiConfiguration.QUERY_PARAM_TO, to);
		}

		public Builder<T> to(QueryKeyword queryKeyword) {
			return queryParam(ApiConfiguration.QUERY_PARAM_TO, queryKeyword.toString());
		}

		/**
		 * Counts the <strong>total</strong> number of elements if the current
		 * command was executed as a {@code HEAD} request.
		 * 
		 * TODO: check usefulness & validity of this!
		 * 
		 * @see ApiCommand#head(String)
		 * @return the <strong>total</strong> number of elements matching the
		 *         current request
		 * @throws EvrythngException
		 */
		public int count() throws EvrythngException {
			logger.debug("Counting total number of elements: [header={}]", ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			Header xResultCountHeader = getCommand().head(ApiConfiguration.HTTP_HEADER_RESULT_COUNT);
			return Integer.valueOf(xResultCountHeader.getValue());
		}

		/**
		 * Executes the current command by requesting JSONP in the
		 * {@link HttpResponse} entity.
		 * 
		 * TODO: check usefulness & validity of this!
		 * 
		 * @param callback
		 *            the name of the callback function
		 * @throws EvrythngException
		 * @return the {@link HttpResponse} entity in the form of JSONP content
		 */
		public String jsonp(String callback) throws EvrythngException {
			// Add JSONP callback to query parameters list:
			queryParam(ApiConfiguration.QUERY_PARAM_CALLBACK, callback);

			// Retrieve response entity content:
			String jsonp = getCommand().content();

			// Remove callback to avoid conflicts on future requests:
			queryParam(ApiConfiguration.QUERY_PARAM_CALLBACK, (String) null);

			return jsonp;
		}
	}
}
