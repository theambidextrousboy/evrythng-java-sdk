/*
 * (c) Copyright 2012 Evrythng Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.result.EvrythngResult;
import net.evrythng.thng.api.utils.JSONUtils;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;
import net.sf.json.JSONArray;

import org.junit.Before;
import org.junit.Test;

public class PropertiesTest extends TestBase {

	// Sample thng properties:
	Property<Integer> volume = null;
	Property<Double> pressure = null;

	@Before
	public void before() {

		// Initialize sample properties:
		volume = new Property<Integer>("Volume", 20);
		pressure = new Property<Double>("Pressure", 0.7);
	}

	/**
	 * Tests POST /thngs/{id}/properties
	 * 
	 * @see ThngAPIWrapper#createThngProperty(String, Property)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProperty() throws Exception {
		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

		Property<Integer> actual = wrapper.createProperty(source.getId(), volume);
		assertProperty(volume, actual);
	}

	/**
	 * Tests POST /thngs/{id}/properties
	 * 
	 * @see ThngAPIWrapper#createProperties(String, Property...)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProperties() throws Exception {
		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

		// Execute request:
		JSONArray actual = wrapper.createProperties(source.getId(), volume, pressure);
		assertNotNull(actual);
		assertTrue(actual.size() == 2);

		Property<Integer> actualVolume = JSONUtils.toBean(actual.getJSONObject(0), Property.class);
		assertProperty(volume, actualVolume);
		Property<Double> actualPressure = JSONUtils.toBean(actual.getJSONObject(1), Property.class);
		assertProperty(pressure, actualPressure);
	}

	/**
	 * Tests GET /thngs/{id}/properties
	 * 
	 * @see ThngAPIWrapper#getProperties(String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProperties() throws Exception {
		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

		JSONArray created = wrapper.createProperties(source.getId(), volume, pressure);
		assertNotNull(created);

		JSONArray actual = wrapper.getProperties(source.getId());
		assertNotNull(actual);
		assertEquals(5, actual.size());
	}

	/**
	 * Tests GET /thngs/{id}/properties/{key}
	 * 
	 * @see ThngAPIWrapper#getProperty(String, String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProperty() throws Exception {

		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

		Property<Integer> expected = wrapper.createProperty(source.getId(), volume);
		assertProperty(volume, expected);

		Property<Integer> actual = wrapper.getProperty(source.getId(), volume.getKey());
		assertProperty(volume, actual);

	}

	/**
	 * Tests DELETE /thngs/{id}/properties/{key}
	 * 
	 * @see ThngAPIWrapper#deleteProperty(String, String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteProperty() throws Exception {

		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

		// Execute request:
		JSONArray actual = wrapper.createProperties(source.getId(), volume, pressure);
		assertNotNull(actual);
		assertEquals(actual.size(), 2);

		// Delete 1 property only:
		EvrythngResult deleted = wrapper.deleteProperty(source.getId(), volume.getKey());
		assertNotNull(deleted);

		JSONArray properties = wrapper.getProperties(source.getId());
		assertNotNull(properties);
		assertEquals(4, properties.size());
	}
        
        /**
	 * Tests PUT /thngs/{id}/property/{id}
	 * 
	 * @see ThngAPIWrapper#updateProperty(String, Property)
	 * @throws Exception
	 */
	@Test
	public void testUpdateProperty() throws Exception {
		// Create a random thng:
		Thng source = wrapper.createThng(ThngsTest.buildRandomThng());

                // Create a property:
                Property<Double> temp = new Property<Double>("Temp", 37.06);
                
		// Execute request:
		Property actual = wrapper.createProperty(source.getId(), temp);
		assertNotNull(actual);

		// Update 1 property:
                temp.setValue(temp.getValue()+1.0);
		Property prop = wrapper.updateProperty(source.getId(), temp);
		assertNotNull(prop);
                
		assertEquals(prop.getValue(), temp.getValue());
	}
        

	private void assertProperty(Property<?> expected, Property<?> actual) {
		assertEquals(expected.getKey(), actual.getKey());
		//assertEquals(expected.getValue(), actual.getValue()); // FIXME: check json-lib reflection
		// assertNotNull(actual.getCreatedAt()); // FIXME: check json-lib property assignment
	}
}
