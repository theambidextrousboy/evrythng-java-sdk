/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.examples;

import com.evrythng.java.wrapper.ApiConfiguration;
import com.evrythng.java.wrapper.exception.EvrythngException;

/**
 * Runner suite for all EVRYTHNG API examples.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ApiExamples extends ExampleRunner {

	/**
	 * @param config
	 */
	public ApiExamples(ApiConfiguration config) {
		super(config);
	}

	/**
	 * @param args
	 * @throws EvrythngException
	 */
	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);
		new ApiExamples(config).run();

		System.exit(0);
	}

	/* {@inheritDoc}
	 * @see com.evrythng.java.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	public void doRun() throws EvrythngException {
		// Run examples:
		new ThngApiExample(config).run();
		new ThngLocationApiExample(config).run();
		new ThngPropertyApiExample(config).run();
		new ExceptionHandlingExample(config).run();
	}
}
