/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.examples;

import com.evrythng.api.wrapper.ApiConfiguration;

/**
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class ExampleRunner {

	protected ApiConfiguration config;

	/**
	 * @param config
	 */
	public ExampleRunner(ApiConfiguration config) {
		this.config = config;
	}

	abstract public void run();
}
