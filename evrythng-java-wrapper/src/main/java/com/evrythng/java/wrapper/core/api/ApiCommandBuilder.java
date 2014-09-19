/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;

import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Generic API command builder.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
@SuppressWarnings("rawtypes")
public class ApiCommandBuilder<T, B extends ApiCommandBuilder> {

	private final ApiCommand<T> command;

	public ApiCommandBuilder(MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.command = new ApiCommand<T>(methodBuilder, uri, responseStatus, responseType);
	}

	/**
	 * Sets a query parameter or removes it if {@code value} equals {@code null}
	 * .
	 * 
	 * @param name
	 *            the query parameter name
	 * @param value
	 *            the query parameter value
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B queryParam(String name, String value) {
		if (value != null) {
			command.setQueryParam(name, value);
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	/**
	 * Sets a query parameter or removes it if the {@code value} equals
	 * {@code null}.
	 * 
	 * @param qpv
	 *            the name and the value of the query parameter.
	 * @return the current {@code B} instance
	 */
	public B queryParam(QueryParamValue qpv) {
		return queryParam(qpv.getKey(), qpv.getValue());
	}

	/**
	 * Sets a multi-valued query parameter or removes it if {@code value} equals
	 * {@code null}.
	 * */
	@SuppressWarnings("unchecked")
	public B queryParam(String name, List<String> value) {
		if (value != null) {
			command.setQueryParam(name, value);
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	/**
	 * Sets a multi-valued query parameter or removes it if {@code value} equals
	 * {@code null}.
	 * */
	@SuppressWarnings("unchecked")
	public B queryParamList(String name, List<String> values) {
		if (values != null) {
			command.setQueryParam(name, concatenateList(values));
		} else {
			command.removeQueryParam(name);
		}
		return (B) this;
	}

	private String concatenateList(final List<String> values) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			String value = values.get(i);
			builder.append(value);
			if (i < values.size() - 1) {
				builder.append(",");
			}
		}
		return builder.toString();
	}

	/**
	 * Sets the provided query parametes.
	 * 
	 * @see #queryParam(String, String)
	 * @param params
	 *            a map name/value entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B queryParams(Map<String, String> params) {
		for (Entry<String, String> entry : params.entrySet()) {
			queryParam(entry.getKey(), entry.getValue());
		}
		return (B) this;
	}

	/**
	 * Sets a request header or removes it if {@code value} equals {@code null}.
	 * 
	 * @param name
	 *            request header name
	 * @param value
	 *            the request header value
	 * @return the current {@code B} instance
	 */
	@SuppressWarnings("unchecked")
	public B header(String name, String value) {
		if (value != null) {
			command.setHeader(name, value);
		} else {
			command.removeHeader(name);
		}
		return (B) this;
	}

	/**
	 * Sets the value of the {@code Accept} HTTP header.
	 * 
	 * @param mediaType
	 *            a valid media type for the {@code Accept} HTTP header
	 * @return
	 */
	public B accept(String mediaType) {
		return header(ApiConfiguration.HTTP_HEADER_ACCEPT, mediaType);
	}

	/**
	 * Executes the current command and maps the {@link HttpResponse} entity to
	 * {@code T} specified by {@link ApiCommand#responseType}.
	 * 
	 * @see ApiCommand#execute()
	 * @return the {@link HttpResponse} entity mapped to {@code T}
	 * @throws EvrythngException
	 */
	public T execute() throws EvrythngException {
		return command.execute();
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} entity
	 * content as {@link String}.
	 * 
	 * @see ApiCommand#content()
	 * @return the {@link HttpResponse} entity content as {@link String}
	 * @throws EvrythngException
	 */
	public String content() throws EvrythngException {
		return command.content();
	}

	/**
	 * Executes the current command and returns the native {@link HttpResponse}.
	 * 
	 * @see ApiCommand#request()
	 * @return the {@link HttpResponse} resulting from the request
	 * @throws EvrythngException
	 */
	public HttpResponse request() throws EvrythngException {
		return command.request();
	}

	/**
	 * Executes the current command and returns the {@link HttpResponse} content
	 * {@link InputStream}.
	 * 
	 * @see ApiCommand#stream()
	 * @return the {@link InputStream} of the {@link HttpResponse}
	 * @throws EvrythngException
	 */
	public InputStream stream() throws EvrythngException {
		return command.stream();
	}

	public ApiCommand<T> getCommand() {
		return command;
	}
}
