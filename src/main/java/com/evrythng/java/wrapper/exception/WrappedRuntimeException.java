/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.exception;

/**
 * 
 * Exception wrapper for throwing important checked exceptions
 * over non-checked methods. It should be used with care,
 * and in limited circumstances.
 * 
 * @author YOUR_FULL_NAME_HERE (almeidap)
 **/
public class WrappedRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrappedRuntimeException(Exception e) {
		super(e);
	}

}
