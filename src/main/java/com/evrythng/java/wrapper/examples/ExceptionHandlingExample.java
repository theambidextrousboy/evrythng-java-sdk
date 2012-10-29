/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.examples;

import java.util.List;

import com.evrythng.java.wrapper.ApiConfiguration;
import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.exception.BadRequestException;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.exception.ForbiddenException;
import com.evrythng.java.wrapper.exception.NotFoundException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * Example of API exception handling using the EVRYTHNG Java Wrapper.
 * 
 * TODO: clean up the example code
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ExceptionHandlingExample extends ExampleRunner {

	/**
	 * @param config
	 */
	public ExceptionHandlingExample(ApiConfiguration config) {
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

		// Run example:
		new ExceptionHandlingExample(config).run();

		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	public void doRun() throws EvrythngException {
		// Initialize the API Manager:
		ApiManager apiManager = new ApiManager(getConfig());

		// Get the Thng API service.
		ThngService thngService = apiManager.thngService();

		// Command builder: GET /thngs:
		Builder<List<Thng>> thngsReader = thngService.thngsReader();

		List<Thng> results = thngsReader.execute();
		echo("GET /thngs", results);
		echo("GET /thngs > count", thngsReader.count());

		Thng thng = new Thng();
		Builder<Thng> thngCreator = thngService.thngCreator(thng);

		try {
			thngCreator.execute();
		} catch (BadRequestException e) {
			echo("POST /thngs", "[BadRequestException]");
		}

		thng.setName("foo");
		Thng created = thngService.thngCreator(thng).execute();

		// Command builder: DELETE /thngs/{id}:
		boolean deleted = thngService.thngDeleter(created.getId()).execute();
		echo("DELETE /thngs/{id}", deleted);
		echo("GET /thngs > count", thngsReader.count());

		Builder<Thng> thngReader = thngService.thngReader(created.getId());
		try {
			thngReader.execute();
		} catch (NotFoundException e) {
			echo("GET /thngs/{id}", "[NotFoundException]");
		}

		try {
			thngReader.apiKey("invalid").execute();
		} catch (ForbiddenException e) {
			echo("GET /thngs/{id}", "[ForbiddenException]");
		}

		echo("GET /thngs > count", thngsReader.count());
	}
}
