package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.result.ThngResult;
import net.evrythng.thng.api.utils.JSONUtils;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.apache.http.ParseException;
import org.junit.Test;

public class CollectionsTest extends TestBase {

	/**
	 * Tests POST /collections
	 * 
	 * @see ThngAPIWrapper#createCollection(ThngCollection)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateCollection() throws Exception {
		// Create random ThngCollection data:
		ThngCollection expected = buildRandomCollection();

		// Create the ThngCollection:
		ThngCollection actual = wrapper.createCollection(expected);
		assertCollection(expected, actual);
	}

	/**
	 * Tests GET /collections
	 * 
	 * @see ThngAPIWrapper#getCollections()
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCollections() throws Exception {
		Collection<ThngCollection> collections = wrapper.getCollections();
		assertNotNull(collections);

		// Debug only:
		JSONUtils.debug(collections);
	}

	/**
	 * Tests GET /collections/{id}
	 * 
	 * @see ThngAPIWrapper#getCollection(String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCollection() throws Exception {
		// Create random ThngCollection data:
		ThngCollection data = buildRandomCollection();

		// Create the ThngCollection:
		ThngCollection expected = wrapper.createCollection(data);
		assertCollection(data, expected);

		// Retrieve the ThngCollection:
		ThngCollection actual = wrapper.getCollection(expected.getId());
		assertCollection(expected, actual);
	}
	
	/**
	 * Tests PUT /collections/{id}
	 * 
	 * @see ThngAPIWrapper#getCollection(String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateCollection() throws Exception {
		// Create random ThngCollection data:
		ThngCollection data = buildRandomCollection();

		// Create the ThngCollection:
		ThngCollection expected = wrapper.createCollection(data);
		assertCollection(data, expected);
		
		// Update the ThngCollection:
		expected.setName("[Edit] " + expected.getName());
		expected.setDescription("[Edit] " + expected.getDescription());
		expected.setIsPublic(!expected.getIsPublic());
		wrapper.updateCollection(expected);

		// Retrieve the ThngCollection:
		ThngCollection actual = wrapper.getCollection(expected.getId());
		assertCollection(expected, actual);
	}
	
	/**
	 * Tests DELETE /collections/{id}
	 * 
	 * @see ThngAPIWrapper#
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteCollection() throws Exception {
		// Create random ThngCollection data:
		ThngCollection data = buildRandomCollection();

		// Create the ThngCollection:
		ThngCollection expected = wrapper.createCollection(data);
		assertCollection(data, expected);

		// Delete the ThngCollection:
		ThngResult result = wrapper.deleteCollection(expected.getId());
		assertResult(result);
		
		// Check deleted ThngCollection (TODO: use exceptions instead):
		ThngCollection deleted = wrapper.getCollection(expected.getId());
		assertNull(deleted.getId());
	}

	/**
	 * Asserts <code>actual</code> {@link ThngCollection} given
	 * <code>expected</code> {@link ThngCollection}.
	 * 
	 * @param expected
	 * @param actual
	 * 
	 * @throws ParseException
	 */
	protected static void assertCollection(ThngCollection expected, ThngCollection actual) throws ParseException {

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
	 * Builds a random {@link ThngCollection} with an unique prefixed
	 * <code>name</code>.
	 * 
	 * @see TestBase#generateUniqueName()
	 * @return
	 */
	protected ThngCollection buildRandomCollection() {
		return new ThngCollection(generateUniqueName(), "Random unit test created collection!", random.nextBoolean());
	}

	
	//	/**
	//	 * Tests PUT /collections/:id
	//	 * 
	//	 * @see ThngAPIWrapper#deleteThngProperty(String, String)
	//	 * 
	//	 * @throws Exception
	//	 */
	//	@Test
	//	public void testEditCollection() throws Exception {
	//
	//		// Generate an unique identifier:
	//		String identifier = generateUniqueID();
	//
	//		// Create the collection:
	//		JSONObject created = this.createTestCollection(identifier);
	//		assertNotNull(created);
	//		
	//		// Edit values:
	//		CollectionObject edited = new CollectionObject("[EDIT] " + created.getString("name"), "[EDIT] " + created.getString("description"), !created.getBoolean("is_public"));
	//		
	//		// FIXME: should collection edition return something?
	//		// Edit the collection using 'id' (and not collection name):
	//		//		JSONObject actual = thngAPIWrapper.editCollection(created.getString("id"), edited);
	//		//		assertNotNull(actual);
	//		//		assertTrue(edited.getName().equals(actual.getString("name")));
	//		//		assertTrue(edited.getDescription().equals(actual.getString("description")));
	//		//		assertTrue(edited.getIsPublic().equals(actual.getString("is_public")));
	//		
	//		assertTrue(wrapper.editCollection(created.getString("id"), edited));
	//	}
	//	
	//	/**
	//	 * Tests DELETE /collections/:id
	//	 * 
	//	 * @throws IOException
	//	 * @throws JSONException
	//	 * @throws ClientProtocolException
	//	 * 
	//	 * @see ThngAPIWrapper#createCollection(String, String, Boolean)
	//	 * @see ThngAPIWrapper#createCollection(ThngObject)
	//	 */
	//	@Test
	//	public void testDeleteCollection() throws Exception {
	//
	//		// Generate an unique identifier:
	//		String identifier = generateUniqueID();
	//
	//		// Create a reference collection:
	//		JSONObject created = this.createTestCollection(identifier);
	//		assertNotNull(created);
	//		
	//		// Delete the collection:
	//		assertTrue(wrapper.deleteCollection(created.getString("id")));
	//	}	
	//	
	//	/* ***** PRIVATE ***** */
	//	
	//	/**
	//	 * Creates a random <code>collection</code> with an unique prefixed
	//	 * <code>name</code>.
	//	 * 
	//	 * @see #generateUniqueID()
	//	 * @return
	//	 * @throws IOException
	//	 * @throws JSONException
	//	 * @throws ClientProtocolException
	//	 * @throws URISyntaxException 
	//	 * @throws ParseException 
	//	 */
	//	protected JSONObject createRandomCollection() throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
	//		// Generate an unique identifier:
	//		String name = generateUniqueID();
	//
	//		// Delegate:
	//		return this.createTestCollection(name);
	//	}
	//
	//	/**
	//	 * Creates a <code>thng</code> with the provided <code>identifier</code>.
	//	 * 
	//	 * @param name
	//	 * 
	//	 * @return
	//	 * @throws IOException
	//	 * @throws JSONException
	//	 * @throws ClientProtocolException
	//	 * @throws URISyntaxException 
	//	 * @throws ParseException 
	//	 */
	//	protected JSONObject createTestCollection(String name) throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
	//		return wrapper.createCollection(name, "Random unit test created collection!", true);
	//	}
}
