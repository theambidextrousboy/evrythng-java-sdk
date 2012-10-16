/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.java.wrapper.examples;

import java.util.ArrayList;
import java.util.List;

import com.evrythng.java.wrapper.ApiConfiguration;
import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
 * 
 * @author Dominique Guinard (domguinard)
 * 
 */
public class ThngPropertyApiExample extends ExampleRunner {

	/**
	 * @param config
	 */
	public ThngPropertyApiExample(ApiConfiguration config) {
		super(config);
	}

	/**
	 * @param args
	 * @throws EvrythngException
	 */
	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			ApiExamples.usage();
			return;
		}

		ApiConfiguration config = ApiExamples.extractConfig(args);

		// Run example:
		new ThngPropertyApiExample(config).run();

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

		// Command builder: GET /thngs:
		Builder<List<Thng>> thngsReader = thngService.thngsReader();

		Thng thng = thngsReader.execute().get(0);
		echo("GET /thngs/[first]", thng);

		// Command builder: GET /thngs/{id}/properties:
		Builder<List<Property>> thngPropertiesReader = thngService.propertiesReader(thng.getId());

		List<Property> results = thngPropertiesReader.execute();
		echo("GET /thngs/{id}/properties", results);

		// Command builder: PUT /thngs/{id}/properties:
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(null, "temperature", String.valueOf(Math.random()), System.currentTimeMillis()));
		properties.add(new Property(null, "speed", String.valueOf(Math.random()), System.currentTimeMillis()));
		results = thngService.propertiesCreator(thng.getId(), properties).execute();
		echo("PUT /thngs/{id}/properties", results);

		// Command builder: GET /thngs/{id}/properties/temperature:
		Builder<List<PropertyValue>> temperatureGetter = thngService.propertyReader(thng.getId(), "temperature");
		List<PropertyValue> values = temperatureGetter.execute();
		echo("GET /thngs/{id}/properties/temperature", values);

		// Command builder: DELETE /thngs/{id}/properties/temperature:
		boolean deleted = thngService.propertyDeleter(thng.getId(), "temperature").execute();
		echo("DELETE /thngs/{id}/properties/temperature", deleted);

		// Command builder: GET /thngs/{id}/properties/temperature:
		values = temperatureGetter.execute();
		echo("GET /thngs/{id}/properties/temperature", values);

		// Command builder: GET /thngs/{id}/properties/speed:
		Builder<List<PropertyValue>> speedGetter = thngService.propertyReader(thng.getId(), "speed");

		values = speedGetter.from(System.currentTimeMillis() - (1000 * 60 * 5)).execute();
		echo("GET /thngs/{id}/properties/speed?from", values);
		echo("COUNT /thngs/{id}/properties/speed", values.size());

		values = speedGetter.to(System.currentTimeMillis() - (1000 * 60 * 2)).execute();
		echo("GET /thngs/{id}/properties/speed?to", values);
		echo("COUNT /thngs/{id}/properties/speed", values.size());
	}
}
