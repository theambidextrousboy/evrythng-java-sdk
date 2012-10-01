/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

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
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
@SuppressWarnings("rawtypes")
public class CommandBuilder<T, B extends CommandBuilder> {

	protected ApiCommand<T> command;

	public CommandBuilder(String apiKey, MethodBuilder<?> methodBuilder, URI uri, Status responseStatus, TypeReference<T> responseType) {
		this.command = new ApiCommand<T>(apiKey, methodBuilder, uri, responseStatus, responseType);
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
	 * @throws EvrythngClientException
	 * @throws InternalErrorException
	 * @throws EvrythngUnexpectedException
	 * @throws ConflictException
	 * @throws NotFoundException
	 * @throws ForbiddenException
	 * @throws UnauthorizedException
	 * @throws BadRequestException
	 */
	public T execute() throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException, EvrythngUnexpectedException,
			InternalErrorException {
		return command.execute();
	}

	/**
	 * @return
	 * @throws EvrythngClientException
	 * @throws InternalErrorException
	 * @throws EvrythngUnexpectedException
	 * @throws ConflictException
	 * @throws NotFoundException
	 * @throws ForbiddenException
	 * @throws UnauthorizedException
	 * @throws BadRequestException
	 */
	public int count() throws EvrythngClientException, BadRequestException, UnauthorizedException, ForbiddenException, NotFoundException, ConflictException, EvrythngUnexpectedException,
			InternalErrorException {
		return command.count();
	}
}
