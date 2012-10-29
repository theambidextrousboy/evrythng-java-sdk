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
public class ApiExamples {

	/**
	 * @param args
	 * @throws EvrythngException
	 */
	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			ExampleRunner.usage();
			return;
		}

		ApiConfiguration config = ExampleRunner.extractConfig(args);

		// Run examples:
		new ThngApiExample(config).run();
		new ThngLocationApiExample(config).run();
		new ThngPropertyApiExample(config).run();
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}
}
