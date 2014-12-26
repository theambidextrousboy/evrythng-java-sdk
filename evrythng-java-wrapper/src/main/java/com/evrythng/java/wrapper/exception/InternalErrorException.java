/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#INTERNAL_SERVER_ERROR} (500)
 **/
public class InternalErrorException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public InternalErrorException(final ErrorMessage message) {
		super(message);
	}

	public InternalErrorException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
