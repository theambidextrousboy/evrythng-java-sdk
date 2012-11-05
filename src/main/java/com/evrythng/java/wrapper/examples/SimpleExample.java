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
import com.evrythng.java.wrapper.core.ExampleRunner;
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * Simple example on the usage of the EVRYTHNG API Java Wrapper. In this example, you will learn how to:
 * <ul>
 * <li>Initialize the {@link ApiManager}</li>
 * <li>Retrieve the {@link CollectionService} and {@link ThngService} through the {@link ApiManager}</li>
 * <li>Create a {@link Collection}</li>
 * <li>Create a {@link Thng}</li>
 * <li>Add a {@link Thng} to an existing {@link Collection}</li>
 * <li>Update the {@link Location} of an existing {@link Thng}</li>
 * <li>Update an existing {@link Thng} with multiple {@link Property} elements.</li>
 * <li>Retrieve an existing {@link Thng}</li>
 * </ul>
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class SimpleExample extends ExampleRunner {

	public SimpleExample(ApiConfiguration config) {
		super(config);
	}

	public static void main(String[] args) throws EvrythngException {

		if (args.length <= 1) {
			usage();
			return;
		}

		ApiConfiguration config = extractConfig(args);

		// Run example:
		new SimpleExample(config).run();

		System.exit(0);
	}

	/* {@inheritDoc}
	 * @see com.evrythng.api.wrapper.examples.ExampleRunner#doRun()
	 */
	@Override
	protected void doRun() throws EvrythngException {
		// Initialize the API Manager:
		echo("Initializing the ApiManager: [config={}]", getConfig());
		ApiManager apiManager = new ApiManager(getConfig());

		// Let's create a Collection resource using the CollectionService:
		echo("Retrieving the Collection API service...");
		CollectionService collectionService = apiManager.collectionService();

		// Build some sample data for creating a new Collection:
		Collection collectionData = new Collection();
		collectionData.setName("Cameras");
		collectionData.setDescription("This is my personal collection of cameras.");

		// Retrieve a Collection creator builder and execute it:
		echo("Creating a new Collection: [input={}]", collectionData);
		Collection collection = collectionService.collectionCreator(collectionData).execute();
		echo("Collection created: [output={}]", collection);

		// Let's create a Thng resource using the ThngService:
		echo("Retrieving the Thng API service...");
		ThngService thngService = apiManager.thngService();

		// Build data for a new Thng:
		Thng thngData = new Thng();
		thngData.setName("Panasonic LUMIX DMC-GF5");
		thngData.setDescription("The LUMIX GF5 enables unlimited artistic expression. Designed in sophisticated profile of ultra-compact body, the new DMC-GF5 features higher image quality even in high sensitivity.");
		thngData.addCustomFields("color", "black");

		// Retrieve a Thng creator builder and execute it:
		echo("Creating a new Thng: [input={}]", thngData);
		Thng thng = thngService.thngCreator(thngData).execute();
		echo("Thng created: [output={}]", thng);

		// Add created Thng to our Collection:
		echo("Adding created Thng to Collection: [thngId={}, collectionId={}]", thng.getId(), collection.getId());
		List<String> thngReferences = collectionService.thngAdder(collection.getId(), thng.getId()).execute();
		echo("Collection updated: [output={}]", thngReferences);

		// Now update Thng location:
		Location locationData = new Location();
		locationData.setLatitude(47.3839);
		locationData.setLongitude(8.5281);

		// Retrieve a Location updater builder and execute it:
		echo("Updating Thng Location: [input={}]", locationData);
		List<Location> lastLocations = thngService.locationUpdater(thng.getId(), locationData).execute();
		echo("Thng Location updated: [output={}]", lastLocations);

		// Let's create some properties:
		List<Property> propertyData = new ArrayList<Property>();
		propertyData.add(new Property("Type", "Digital Single Lens Mirrorless camera"));
		propertyData.add(new Property("Lens Mount", "Micro Four Thirds mount"));
		propertyData.add(new Property("Camera Effective Pixels", "12.10 Megapixels"));

		echo("Creating Properties: [thngId={}, input={}]", thng.getId(), propertyData);
		List<Property> properties = thngService.propertiesCreator(thng.getId(), propertyData).execute();
		echo("Thng Properties created: [output={}]", properties);

		// Finally, retrieve our updated Thng using a Thng reader builder:
		echo("Retrieving updated Thng...");
		thng = thngService.thngReader(thng.getId()).execute();
		echo("Thng retrieved: [output={}]", thng);
	}
}
