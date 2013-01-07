/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.exception;

import com.evrythng.java.wrapper.core.http.Status;
import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * {@link Status#NOT_FOUND} (404)
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 **/
public class NotFoundException extends EvrythngErrorException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(ErrorMessage message) {
		super(message);
	}

	public NotFoundException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
