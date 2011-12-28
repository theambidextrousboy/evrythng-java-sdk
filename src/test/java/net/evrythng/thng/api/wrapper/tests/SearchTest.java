package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.*;
import net.evrythng.thng.api.wrapper.SearchParameterEnum;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class SearchTest extends TestBase {

	/**
	 * Tests GET /search
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearch() throws Exception {
		JSONArray results = wrapper.search();
		assertNotNull(results);
		
		// Debug only:
		this.printItems(results);
	}
	
	/**
	 * Tests GET /search
	 * Parameter: <code>q</code>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByQuery() throws Exception {
		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);
		
		JSONArray results = wrapper.search(thng.getString("identifier"));
		assertNotNull(results);
		assertTrue(results.length() >= 1);
		
		// Debug only:
		this.printItems(results);
	}
	
	/**
	 * Tests GET /search
	 * Parameter: <code>geocode</code>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByGeocode() throws Exception {
		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);
		
		JSONArray results = wrapper.search(SearchParameterEnum.GEOCODE, "-14.40,-71.33,5km");
		assertNotNull(results);
		assertTrue(results.length() >= 1);
		
		// Debug only:
		this.printItems(results);
	}
	
	/**
	 * Tests GET /search
	 * Parameter: <code>thngspace</code>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByThngspace() throws Exception {
		// Create a reference thng:
		JSONObject thng = this.createRandomThng();
		assertNotNull(thng);
		
		JSONArray results = wrapper.search(SearchParameterEnum.THNGSPACE, this.getNamespace());
		assertNotNull(results);
		assertTrue(results.length() >= 1);
		
		// Debug only:
		this.printItems(results);
	}

}
