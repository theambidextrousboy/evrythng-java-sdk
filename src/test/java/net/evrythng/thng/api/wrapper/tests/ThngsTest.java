package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import net.evrythng.thng.api.element.ThngObject;
import net.evrythng.thng.api.element.ThngProperty;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class ThngsTest extends TestBase {

	// Sample thng properties:
	ThngProperty<Integer> volume = null;
	ThngProperty<Double> pressure = null;

	@Before
	public void init() throws JSONException {
		// Initialize sample properties:
		volume = new ThngProperty<Integer>("Volume", 20);
		pressure = new ThngProperty<Double>("Pressure", 0.7);
	}

	/**
	 * Tests GET /thngs
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllThngs() throws Exception {
		JSONArray thngs = thngAPIWrapper.getAllThngs();
		assertNotNull(thngs);

		// Debug only:
		this.printItems(thngs);
	}

	/**
	 * Tests GET /thngs/:identifier
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetThng() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		// Create the thng:
		JSONObject expected = this.createTestThng(identifier);
		assertNotNull(expected);

		// Retrieve the thng:
		JSONObject actual = thngAPIWrapper.getThng(identifier);
		assertNotNull(actual);
		assertTrue(expected.getString("identifier").equals(actual.getString("identifier")));
	}

	/**
	 * Tests POST /thngs
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * 
	 * @see ThngAPIWrapper#createThng(String, String, Boolean)
	 * @see ThngAPIWrapper#createThng(ThngObject)
	 */
	@Test
	public void testCreateThng() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		JSONObject thng = this.createTestThng(identifier);
		assertNotNull(thng);
		assertTrue(thng.getString("identifier").equals(identifier));
	}

	/**
	 * Tests GET /thngs/:identifier/properties
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProperties() throws Exception {

		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		JSONArray created = thngAPIWrapper.createThngProperties(thng.getString("identifier"), volume, pressure);
		assertNotNull(created);

		JSONArray properties = thngAPIWrapper.getThngProperties(thng.getString("identifier"));
		assertNotNull(properties);
		assertEquals(properties.length(), 2 + 1); // FIXME OMFG: 1 thng + 2 properties
		
		// Debug only:
		this.printItems(properties);
	}

	/**
	 * Tests GET /thngs/:identifier/properties/:title
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProperty() throws Exception {

		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		JSONObject result = thngAPIWrapper.createThngProperty(thng.getString("identifier"), volume);
		assertNotNull(result);

		JSONObject actual = thngAPIWrapper.getThngProperty(thng.getString("identifier"), volume.getTitle());
		assertNotNull(actual);
		assertEquals(volume.getTitle(), actual.getString("title"));
		assertEquals(volume.getText(), Integer.valueOf(actual.getString("text")));
	}

	/**
	 * Tests POST /thngs/:identifier/properties
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProperty() throws Exception {

		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		JSONObject result = thngAPIWrapper.createThngProperty(thng.getString("identifier"), volume);
		assertNotNull(result);
		assertTrue(volume.getTitle().equals(result.getString("title")));
		assertTrue(volume.getText().equals(Integer.valueOf(result.getString("text"))));
	}

	/**
	 * Tests POST /thngs/:identifier/properties
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProperties() throws Exception {

		// Create a random thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		// Execute request:
		JSONArray actual = thngAPIWrapper.createThngProperties(thng.getString("identifier"), volume, pressure);
		assertNotNull(actual);
		assertTrue(actual.length() == 2);

		JSONObject actualVolume = actual.getJSONObject(0);
		assertTrue(actualVolume.getString("title").equals(volume.getTitle()));
		assertTrue(volume.getText().equals(Integer.valueOf(actualVolume.getString("text"))));

		JSONObject actualPressure = actual.getJSONObject(1);
		assertTrue(actualPressure.getString("title").equals(pressure.getTitle()));
		assertTrue(pressure.getText().equals(Double.valueOf(actualPressure.getString("text"))));
	}
	
	/**
	 * Tests DELETE /thngs/:identifier/properties/:title
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteProperty() throws Exception {

		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		// Create some properties:
		JSONArray result = thngAPIWrapper.createThngProperties(thng.getString("identifier"), volume, pressure);
		assertNotNull(result);

		// Delete 1 property only:
		JSONObject deleted = thngAPIWrapper.deleteThngProperty(thng.getString("identifier"), volume.getTitle());
		assertNotNull(deleted);
		
		JSONArray actual = thngAPIWrapper.getThngProperties(thng.getString("identifier"));
		assertNotNull(actual);
		assertEquals(actual.length(), 1 + 1); // FIXME OMFG: 1 thng + 1 properties
	}

	/* ***** BATCH TESTS ***** */

	/**
	 * Batch tests POST /thngs
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException 
	 * @throws ParseException 
	 */
	@Test
	public void testBatchCreateThngs() throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
		for (int i = 0; i < BATCH_COUNT; i++) {
			JSONObject thng = this.createRandomThng();
			assertNotNull(thng);
		}
	}

	/**
	 * Batch tests POST /thngs/:identifier/properties
	 * 
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws ClientProtocolException 
	 * @throws URISyntaxException 
	 */
	@Test
	public void testBatchCreateProperties() throws ClientProtocolException, JSONException, IOException, URISyntaxException {

		// Create a random thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);

		for (int i = 0; i < BATCH_COUNT; i++) {
			// Execute request:
			JSONArray actual = thngAPIWrapper.createThngProperties(thng.getString("identifier"), volume, pressure);
			assertNotNull(actual);
			assertTrue(actual.length() == 2);

			JSONObject actualVolume = actual.getJSONObject(0);
			assertTrue(actualVolume.getString("title").equals(volume.getTitle()));
			assertTrue(volume.getText().equals(Integer.valueOf(actualVolume.getString("text"))));

			JSONObject actualPressure = actual.getJSONObject(1);
			assertTrue(actualPressure.getString("title").equals(pressure.getTitle()));
			assertTrue(pressure.getText().equals(Double.valueOf(actualPressure.getString("text"))));
			
			// Update values of properties:
			volume.setText(volume.getText() + random.nextInt(256));
			pressure.setText(pressure.getText() + random.nextFloat());
		}
	}
}
