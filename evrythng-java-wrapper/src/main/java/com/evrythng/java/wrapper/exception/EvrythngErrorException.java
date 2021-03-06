/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class EvrythngErrorException extends EvrythngException {

	private static final long serialVersionUID = 1L;
	private final ErrorMessage errorMessage;

	protected EvrythngErrorException(final ErrorMessage message) {
		this(message, null);
	}

	protected EvrythngErrorException(final ErrorMessage message, final Throwable cause) {
		super(JSONUtils.write(message), cause);
		this.errorMessage = message;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
