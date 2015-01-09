/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#FORBIDDEN} (403)
 **/
public class ForbiddenException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException(final ErrorMessage message) {
		super(message);
	}

	public ForbiddenException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
