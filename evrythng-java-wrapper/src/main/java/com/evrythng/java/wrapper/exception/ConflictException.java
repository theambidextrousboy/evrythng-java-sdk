/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#CONFLICT} (409)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class ConflictException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public ConflictException(final ErrorMessage message) {
		super(message);
	}

	public ConflictException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
