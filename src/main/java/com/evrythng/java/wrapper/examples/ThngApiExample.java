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
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
 * 
 * @author Dominique Guinard (domguinard)
 * 
 */
public class ThngApiExample extends ExampleRunner {

	/**
	 * @param config
	 */
	public ThngApiExample(ApiConfiguration config) {
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
		new ThngApiExample(config).run();

		System.exit(0);
	}

	/* (non-Javadoc)
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	public void doRun() throws EvrythngException {
		// Initialize the API Manager:
		ApiManager apiManager = new ApiManager(config);

		// Get the Thng API service.
		ThngService thngService = apiManager.thngService();

		// Command builder: GET /thngs
		Builder<List<Thng>> thngsReader = thngService.thngsReader();

		List<Thng> results = thngsReader.execute();
		echo("GET /thngs", results);
		echo("GET /thngs > count", thngsReader.count());

		results = thngsReader.page(2).execute();
		echo("GET /thngs?page=2", results);

		results = thngsReader.page(2).perPage(2).execute();
		echo("GET /thngs?page=2&perPage=2", results);

		results = thngsReader.perPage(1).execute();
		echo("GET /thngs?perPage=1", results);

		// Command builder: GET /thngs/{id}
		Thng retrieved = thngService.thngReader(results.get(0).getId()).execute();
		echo("GET /thngs/{id}", retrieved);

		// Command builder: POST /thngs
		retrieved.setId(null);
		Builder<Thng> thngCreator = thngService.thngCreator(retrieved);

		Thng created = thngCreator.execute();
		echo("POST /thngs", created);
		echo("GET /thngs > count", thngsReader.count());

		// Command builder: POST /thngs/bulk
		for (Thng thng : results) {
			thng.setId(null);
		}
		/*
		List<String> refs = thngService.thngsCreator(results).execute();
		echo("POST /thngs/bulk", refs));
		*/

		// Command builder: PUT /thngs
		created.setName("[Updated] " + created.getName());
		Thng updated = thngService.thngUpdater(created.getId(), created).execute();
		echo("PUT /thngs/{id}", updated);
		echo("GET /thngs > count", thngsReader.count());

		// Command builder: DELETE /thngs/{id}
		created = thngCreator.execute();
		boolean deleted = thngService.thngDeleter(created.getId()).execute();
		echo("DELETE /thngs/{id}", deleted);
		echo("GET /thngs > count", thngsReader.count());
	}
}
