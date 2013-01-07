/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#UNAUTHORIZED} (401)
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class UnauthorizedException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(ErrorMessage message) {
		super(message);
	}

	public UnauthorizedException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
