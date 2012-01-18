package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.search.GeoCode;
import net.evrythng.thng.api.search.GeoCode.MeasureUnit;
import net.evrythng.thng.api.search.SearchParameter;
import net.evrythng.thng.api.search.SearchParameter.Type;
import net.evrythng.thng.api.utils.JSONUtils;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.junit.Test;

public class SearchTest extends TestBase {

	/**
	 * Tests GET /search
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearch() throws Exception {
		Collection<Thng> results = wrapper.search();
		assertNotNull(results);

		// Debug only:
		JSONUtils.debug(results);
	}

	/**
	 * Tests GET /search?q={pattern}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByQuery() throws Exception {
		// Create random Thng data:
		Thng data = ThngsTest.buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);

		// Execute search using thng name:
		Collection<Thng> results = wrapper.search(expected.getName());
		assertNotNull(results);
		assertEquals(results.size(), 1);

		// Execute search using thng 'namespace':
		results = wrapper.search(getNamespace());
		assertNotNull(results);
		assertTrue(results.size() >= 1);

		// Debug only:
		JSONUtils.debug(results);
	}

	/**
	 * Tests GET /search?geocode={geocode}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByGeocode() throws Exception {
		// Create random Thng data:
		Thng data = ThngsTest.buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);

		// Execute search:
		GeoCode geoCode = new GeoCode(expected.getLatitude(), expected.getLongitude(), 0.9999d, MeasureUnit.KM);
		Collection<Thng> results = wrapper.search(SearchParameter.GEOCODE, geoCode.toString());
		assertNotNull(results);
		assertTrue(results.size() >= 1);

		// Debug only:
		JSONUtils.debug(results);
	}
	
	/**
	 * Tests GET /search?type={mine|all}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByType() throws Exception {
		// Create random Thng data:
		Thng data = ThngsTest.buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);

		// Execute search:
		Collection<Thng> results = wrapper.search(SearchParameter.TYPE, Type.ALL.toString());
		assertNotNull(results);
		assertTrue(results.size() >= 1);

		// Debug only:
		JSONUtils.debug(results);
	}
	
	/**
	 * Tests GET /search?q={pattern}&geocode={geocode}&type={mine|all}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchFull() throws Exception {
		// Create random Thng data:
		Thng data = ThngsTest.buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);

		// Execute search:
		GeoCode geoCode = new GeoCode(expected.getLatitude(), expected.getLongitude(), 0.9999d, MeasureUnit.KM);
		Collection<Thng> results = wrapper.search(expected.getName(), geoCode, Type.MINE);
		assertNotNull(results);
		assertTrue(results.size() >= 1);

		// Debug only:
		JSONUtils.debug(results);
	}
}
