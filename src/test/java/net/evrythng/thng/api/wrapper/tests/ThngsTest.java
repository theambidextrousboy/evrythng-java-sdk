package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.evrythng.thng.api.model.Model;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.result.ThngArrayResult;
import net.evrythng.thng.api.result.ThngResult;
import net.evrythng.thng.api.utils.JSONUtils;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;
import net.sf.json.JSONArray;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThngsTest extends TestBase {

	private static final Logger logger = LoggerFactory.getLogger(ThngsTest.class);

	// Repository for created resources:
	// TODO: remove this when a test sandbox environment is up and running!
	List<Thng> repository = null;

	@Before
	public void before() {
		// initialize repositories:
		repository = new ArrayList<Thng>();
	}

	/**
	 * Cleans created assets.
	 * 
	 * TODO: remove this when a test sandbox environment is up and running!
	 * 
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@After
	public void after() throws ClientProtocolException, URISyntaxException, IOException {
		// Clean created thngs:
		for (Thng thng : repository) {
			logger.debug("Deleting thng: {}", thng.getId());
			wrapper.deleteThng(thng.getId());
		}
	}

	/**
	 * Tests POST /thngs
	 * 
	 * @see ThngAPIWrapper#createThng(Thng)
	 * @throws Exception
	 */
	@Test
	public void testCreateThng() throws Exception {
		// Create random thng data:
		Thng expected = buildRandomThng();

		// Create the thng:
		Thng actual = wrapper.createThng(expected);
		repository.add(actual);
		assertThng(expected, actual);
	}

	/**
	 * Tests POST /thngs
	 * Batch mode
	 * 
	 * @see ThngAPIWrapper#createThng(ThngObject)
	 * @throws Exception
	 */
	@Test
	public void testCreateThngs() throws Exception {

		Model[] expected = new Model[BATCH_COUNT];

		for (int i = 0; i < BATCH_COUNT; i++) {
			expected[i] = buildRandomThng();
		}

		ThngArrayResult result = wrapper.createThngs(expected);
		assertResult(result);

		JSONArray json = result.getContent();
		assertNotNull(json);
	}

	/**
	 * Tests GET /thngs
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetThngs() throws Exception {
                wrapper.createThng(buildRandomThng());
                wrapper.createThng(buildRandomThng());
                
             
                wrapper.getThngs();
            
		
	}

	/**
	 * Tests GET /thngs/{id}
	 * 
	 * @see ThngAPIWrapper#createThng(Thng)
	 * @see ThngAPIWrapper#getThng(String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetThng() throws Exception {
		// Create random Thng data:
		Thng data = buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);
		assertThng(data, expected);

		// Retrieve the Thng:
		Thng actual = wrapper.getThng(expected.getId());
		assertThng(expected, actual);
	}

	/**
	 * Tests PUT /thngs/{id}
	 * 
	 * @see ThngAPIWrapper#createThng(Thng)
	 * @throws Exception
	 */
	@Test
	public void testUpdateThng() throws Exception {
		// Create random Thng data:
		Thng data = buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);
		assertThng(data, expected);

		// Update the Thng:
		expected.setName("[EDITED] " + expected.getName());
		expected.setDescription("[EDITED] " + expected.getDescription());
		expected.setIsPublic(!expected.getIsPublic());
		wrapper.updateThng(expected);

		// Retrieve the Thng:
		Thng actual = wrapper.getThng(expected.getId());
		assertThng(expected, actual);
	}

	/**
	 * Tests DELETE /thngs/{id}
	 * 
	 * @see ThngAPIWrapper#deleteThng(String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteThng() throws Exception {
		// Create random Thng data:
		Thng data = buildRandomThng();

		// Create the Thng:
		Thng expected = wrapper.createThng(data);
		assertThng(data, expected);

		// Delete the Thng:
		ThngResult result = wrapper.deleteThng(expected.getId());
		assertResult(result);

		// Check deleted Thng (TODO: use exceptions instead):
		Thng deleted = wrapper.getThng(expected.getId());
		assertNull(deleted.getId());
	}

	/**
	 * Asserts <code>actual</code> {@link ThngCollection} given
	 * <code>expected</code> {@link ThngCollection}.
	 * 
	 * @param expected
	 * @param actual
	 * @throws ParseException
	 */
	protected static void assertThng(Thng expected, Thng actual) throws ParseException {

		assertNotNull(actual);

		// Check read-only fields:
		assertNotBlank(actual.getId());
		assertNotBlank(actual.getCreatedAt());

		// Check expected and actual values:
		if (expected.getId() != null) {
			assertEquals(expected.getId(), actual.getId());
		}
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getIsPublic(), actual.getIsPublic());
	}

	/**
	 * Builds a random {@link Thng} with an unique prefixed <code>name</code>.
	 * 
	 * @see TestBase#generateUniqueName()
	 * @return
	 */
	protected static Thng buildRandomThng() {
		Thng thng = new Thng(generateUniqueName(), "Random unit test created thng!", random.nextBoolean());
		thng.setLatitude(nextRandom(-90, 90));
		thng.setLongitude(nextRandom(-180, 180));
		return thng;
	}

}
