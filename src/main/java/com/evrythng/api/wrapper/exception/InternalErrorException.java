/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.exception;

import com.evrythng.thng.resource.model.exception.ErrorMessage;

/**
 * TODO Comment this class
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 **/
public class InternalErrorException extends EvrythngErrorException {

	public InternalErrorException(ErrorMessage message) {
		super(message);
	}

	public InternalErrorException(ErrorMessage message, Throwable cause) {
		super(message, cause);
	}
}
