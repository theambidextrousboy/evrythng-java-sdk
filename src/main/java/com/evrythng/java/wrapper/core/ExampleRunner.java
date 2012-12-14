/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.commons.config.ApiConfiguration;

/**
 * Base definition for implementing examples on top of the EVRYTHNG API wrapper.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public abstract class ExampleRunner {

	private static final Logger logger = LoggerFactory.getLogger(ExampleRunner.class);

	private final ApiConfiguration config;

	/**
	 * Creates a new instance of {@link ExampleRunner} using the provided {@link ApiConfiguration}.
	 * 
	 * @param config
	 */
	public ExampleRunner(ApiConfiguration config) {
		this.config = config;
	}

	/**
	 * Runs the current example.
	 * 
	 * @throws EvrythngException
	 */
	public void run() throws EvrythngException {
		try {
			// Delegate:
			doRun();
			echo("Done! Checkout others examples for more usages of the EVRYTHNG API wrapper.");
		} catch (EvrythngException e) {
			echo("[EvrythngException] ", e.getMessage());
			throw e;
		}
	}

	/**
	 * Concrete implementation.
	 * 
	 * @throws EvrythngException
	 */
	abstract protected void doRun() throws EvrythngException;

	/**
	 * Extracts required parameters from program {@code args}.
	 * 
	 * @param args
	 * @return
	 */
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

	/**
	 * Displays the usage information.
	 */
	public static void usage() {
		echo(">> Usage:");
		echo("   --key: The EVRYTHNG API key for authentication.\n");
		echo("   --url: [Optional] The EVRYTHNG API base URL.\n");
	}

	/**
	 * Logs the provided message.
	 * 
	 * @param message
	 */
	public static void echo(String message) {
		logger.info("[ECHO] {}", message);
	}

	/**
	 * Logs an INFO message according to the specified format and arguments. Arguments will be jsonified before string
	 * replacement.
	 * 
	 * @param format
	 * @param args
	 */
	public static void echo(String format, Object... args) {
		// Jsonify data objects:
		List<Object> argsAsJson = new ArrayList<Object>();
		for (Object arg : args) {
			argsAsJson.add(JSONUtils.write(arg));
		}
		logger.info("[ECHO] " + format, argsAsJson.toArray());
	}

	public ApiConfiguration getConfig() {
		return config;
	}
}
