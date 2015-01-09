/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#METHOD_NOT_ALLOWED} (405)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class MethodNotAllowedException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException(final ErrorMessage message) {
		super(message);
	}

	public MethodNotAllowedException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
