/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#BAD_REQUEST} (400)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class BadRequestException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(final ErrorMessage message) {
		super(message);
	}

	public BadRequestException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
