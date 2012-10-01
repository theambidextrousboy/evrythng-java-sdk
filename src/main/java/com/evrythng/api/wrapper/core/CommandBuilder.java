/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import com.evrythng.api.wrapper.core.HttpMethodBuilder.MethodBuilder;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
@SuppressWarnings("rawtypes")
public class CommandBuilder<T, B extends CommandBuilder> {

	protected ApiCommand<T> command;

	public CommandBuilder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, TypeReference<T> typeReference) {
		this.command = new ApiCommand<T>(apiKey, methodBuilder, uri, typeReference);
	}

	public B authorization(String apiKey) {
		return header("Authorization", apiKey);
	}

	@SuppressWarnings("unchecked")
	public B queryParam(String name, String value) {
		command.setQueryParam(name, value);
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B header(String key, String value) {
		command.setHeader(key, value);
		return (B) this;
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
