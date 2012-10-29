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
import com.evrythng.java.wrapper.exception.EvrythngException;
import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * This is a simple example of how to use the EVRYTHNG API wrapper.
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
	public void doRun() throws EvrythngException {
		echo("Initializing ApiManager with provided API configuration", getConfig());
		ApiManager apiManager = new ApiManager(getConfig());

		// Let's create a Collection resource:
		echo("Retrieving the Collection API service...");
		CollectionService collectionService = apiManager.collectionService();

		// Build data for Collection:
		Collection collection = new Collection();
		collection.setName("Cameras");
		collection.setDescription("This is my personal collection of cameras.");

		// Retrieve a Collection creator builder and execute it:
		echo("Creating a new collection", collection);
		Collection createdCollection = collectionService.collectionCreator(collection).execute();
		echo("Collection created", createdCollection);

		// Let's create a Thng resource:
		echo("Retrieving the Thng API service...");
		ThngService thngService = apiManager.thngService();

		// Build data for Collection:
		Thng thngData = new Thng();
		thngData.setName("Panasonic LUMIX DMC-GF5");
		thngData.setDescription("The LUMIX GF5 enables unlimited artistic expression. Designed in sophisticated profile of ultra-compact body, the new DMC-GF5 features higher image quality even in high sensitivity.");
		thngData.addCustomFields("color", "black");

		// Retrieve a Collection creator builder and execute it:
		echo("Creating a new Thng", thngData);
		Thng thng = thngService.thngCreator(thngData).execute();
		echo("Thng created", thng);

		// Add created Thng to our Collection:
		echo("Adding created Thng to Collection");
		List<String> thngReferences = collectionService.thngAdder(createdCollection.getId(), thng.getId()).execute();
		echo("Thng references of Collection", thngReferences);

		// Now update Thng location:
		Location locationData = new Location();
		locationData.setLatitude(47.3839);
		locationData.setLongitude(8.5281);

		// Retrieve a Location updater builder and execute it:
		echo("Updating Location", locationData);
		List<Location> lastLocations = thngService.locationUpdater(thng.getId(), locationData).execute();
		echo("Location updated. Last locations", lastLocations);

		// Let's create some properties:
		List<Property> propertyData = new ArrayList<Property>();
		propertyData.add(new Property(null, "Type", "Digital Single Lens Mirrorless camera", null));
		propertyData.add(new Property(null, "Lens Mount", "Micro Four Thirds mount", null));
		propertyData.add(new Property(null, "Camera Effective Pixels", "12.10 Megapixels", null));

		echo("Creating Properties", propertyData);
		List<Property> properties = thngService.propertiesCreator(thng.getId(), propertyData).execute();
		echo("Properties created", properties);

		// Finally, retrieve our updated Thng:
		echo("Retrieving updated Thng...");
		thng = thngService.thngReader(thng.getId()).execute();
		echo("Final Thng", thng);

		echo("Done! Checkout others examples for more usages of the EVRYTHNG API wrapper.");
	}
}
