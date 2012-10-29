/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core.api;

import java.net.URI;

import org.apache.http.HttpResponse;

import com.evrythng.java.wrapper.core.http.HttpMethodBuilder.MethodBuilder;
import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.java.wrapper.exception.EvrythngException;
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
	 * Sets a query parameter or removes it if {@code value} equals {@code null}.
	 * 
	 * @param name the query parameter name
	 * @param value the query parameter value
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
	 * Sets a request header or removes it if {@code value} equals {@code null}.
	 * 
	 * @param name request header name
	 * @param value the request header value
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
	 * Executes the current command and maps the {@link HttpResponse} entity to {@code T} specified by
	 * {@link ApiCommand#responseType}.
	 * 
	 * @see ApiCommand#execute()
	 * @return the {@link HttpResponse} entity mapped to {@code T}
	 * @throws EvrythngException
	 */
	public T execute() throws EvrythngException {
		return command.execute();
	}

	/**
	 * Executes the current command and returns the native {@link HttpResponse}.
	 * 
	 * @see ApiCommand#request()
	 * @return the {@link HttpResponse} implied by the request
	 * @throws EvrythngException
	 */
	public HttpResponse request() throws EvrythngException {
		return command.request();
	}

	public ApiCommand<T> getCommand() {
		return command;
	}
}
