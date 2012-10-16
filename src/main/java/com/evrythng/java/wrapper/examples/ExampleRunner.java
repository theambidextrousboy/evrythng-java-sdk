/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.java.wrapper.ApiConfiguration;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.JSONUtils;

/**
 * Base definition for running examples using the EVRYTHNG API wrapper.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class ExampleRunner {

	private static final Logger logger = LoggerFactory.getLogger(ExampleRunner.class);

	protected ApiConfiguration config;

	/**
	 * @param config
	 */
	public ExampleRunner(ApiConfiguration config) {
		this.config = config;
	}

	public void run() throws EvrythngException {
		try {
			// Delegate:
			doRun();
		} catch (EvrythngException e) {
			System.out.println("[EvrythngException] " + e.getMessage());
			throw e;
		}
	}

	abstract public void doRun() throws EvrythngException;

	public static ApiConfiguration extractConfig(String[] args) {
		ApiConfiguration config = new ApiConfiguration();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--key") && i + 1 < args.length) {
				config.setKey(args[i + 1]);
			} else if (args[i].equals("--url") && i + 1 < args.length) {
				config.setUrl(args[i + 1]);
			}
		}
		return config;
	}

	public static void usage() {
		System.out.println(">> Usage:");
		System.out.println("   --key: The EVRYTHNG API key for authentication.\n");
		System.out.println("   --url: [Optional] The EVRYTHNG API base URL.\n");
	}

	/**
	 * @param string
	 * @param results
	 */
	protected void echo(String label, Object object) {
		logger.info("[ECHO] {}: {}", label, JSONUtils.write(object));
	}
}
