/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class EvrythngUnexpectedException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public EvrythngUnexpectedException(final ErrorMessage message) {
		super(message);
	}

	public EvrythngUnexpectedException(final ErrorMessage message, final Throwable cause) {
		super(message, cause);
	}
}
