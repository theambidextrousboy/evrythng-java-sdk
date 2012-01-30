/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.wrapper.tests;

import net.evrythng.thng.api.wrapper.tests.core.TestBase;

public class PerformanceTest extends TestBase {

//	/* ***** BATCH TESTS ***** */
	//
	//	/**
	//	 * Batch tests POST /thngs
	//	 * 
	//	 * @throws IOException
	//	 * @throws JSONException
	//	 * @throws ClientProtocolException
	//	 * @throws URISyntaxException 
	//	 * @throws ParseException 
	//	 */
	//	@Test
	//	public void testBatchCreateThngs() throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
	//		for (int i = 0; i < BATCH_COUNT; i++) {
	//			JSONObject thng = this.createRandomThng();
	//			assertNotNull(thng);
	//		}
	//	}
	//
	//	/**
	//	 * Batch tests POST /thngs/:identifier/properties
	//	 * 
	//	 * @throws IOException 
	//	 * @throws JSONException 
	//	 * @throws ClientProtocolException 
	//	 * @throws URISyntaxException 
	//	 */
	//	@Test
	//	public void testBatchCreateProperties() throws ClientProtocolException, JSONException, IOException, URISyntaxException {
	//
	//		// Create a random thng:
	//		JSONObject thng = this.createRandomThng();
	//		assertNotNull(thng);
	//
	//		for (int i = 0; i < BATCH_COUNT; i++) {
	//			// Execute request:
	//			JSONArray actual = wrapper.createThngProperties(thng.getString("identifier"), volume, pressure);
	//			assertNotNull(actual);
	//			assertTrue(actual.length() == 2);
	//
	//			JSONObject actualVolume = actual.getJSONObject(0);
	//			assertTrue(actualVolume.getString("title").equals(volume.getTitle()));
	//			assertTrue(volume.getText().equals(Integer.valueOf(actualVolume.getString("text"))));
	//
	//			JSONObject actualPressure = actual.getJSONObject(1);
	//			assertTrue(actualPressure.getString("title").equals(pressure.getTitle()));
	//			assertTrue(pressure.getText().equals(Double.valueOf(actualPressure.getString("text"))));
	//			
	//			// Update values of properties:
	//			volume.setText(volume.getText() + random.nextInt(256));
	//			pressure.setText(pressure.getText() + random.nextFloat());
	//		}
	//	}
}
