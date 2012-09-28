/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.service.ThngService;
import com.evrythng.api.wrapper.util.JSONUtils;
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
	 */
	public static void main(String[] args) {

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
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#run()
	 */
	@Override
	public void run() {
		// Initialize the API Manager:
		ApiManager apiManager = new ApiManager(config);

		// Get the Thng API service.
		ThngService thngService = apiManager.thngService();

		// Command builder: GET /thngs:
		Builder<List<Thng>> thngsReader = thngService.getThngs();

		Thng thng = thngsReader.execute().get(0);
		System.out.println("GET /thngs/[first]: " + JSONUtils.write(thng));

		// Command builder: GET /thngs/{id}/properties:
		Builder<List<Property>> thngPropertiesReader = thngService.getThngProperties(thng.getId());

		List<Property> results = thngPropertiesReader.execute();
		System.out.println("GET /thngs/{id}/properties: " + JSONUtils.write(results));

		// Command builder: PUT /thngs/{id}/properties:
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(null, "temperature", String.valueOf(Math.random()), System.currentTimeMillis()));
		properties.add(new Property(null, "speed", String.valueOf(Math.random()), System.currentTimeMillis()));
		results = thngService.createThngProperties(thng.getId(), properties).execute();
		System.out.println("PUT /thngs/{id}/properties: " + JSONUtils.write(results));

		// Command builder: GET /thngs/{id}/properties/temperature:
		Builder<List<PropertyValue>> temperatureGetter = thngService.getThngProperty(thng.getId(), "temperature");
		List<PropertyValue> values = temperatureGetter.execute();
		System.out.println("GET /thngs/{id}/properties/temperature: " + JSONUtils.write(values));

		// Command builder: DELETE /thngs/{id}/properties/temperature:
		HttpResponse response = thngService.deleteThngProperty(thng.getId(), "temperature").execute();
		System.out.println("DELETE /thngs/{id}/properties/temperature: " + response.getStatusLine().toString());

		// Command builder: GET /thngs/{id}/properties/temperature:
		values = temperatureGetter.execute();
		System.out.println("GET /thngs/{id}/properties/temperature: " + JSONUtils.write(values));

		// Command builder: GET /thngs/{id}/properties/speed:
		Builder<List<PropertyValue>> speedGetter = thngService.getThngProperty(thng.getId(), "speed");

		values = speedGetter.from(System.currentTimeMillis() - (1000 * 60 * 5)).execute();
		System.out.println("GET /thngs/{id}/properties/speed?from: " + JSONUtils.write(values));
		System.out.println("COUNT /thngs/{id}/properties/speed: " + values.size());

		values = speedGetter.to(System.currentTimeMillis() - (1000 * 60 * 2)).execute();
		System.out.println("GET /thngs/{id}/properties/speed?to: " + JSONUtils.write(values));
		System.out.println("COUNT /thngs/{id}/properties/speed: " + values.size());
	}
}
