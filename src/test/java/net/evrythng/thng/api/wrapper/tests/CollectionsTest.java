package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import net.evrythng.thng.api.element.CollectionObject;
import net.evrythng.thng.api.element.ThngObject;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class CollectionsTest extends TestBase {

	/**
	 * Tests GET /collections
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCollections() throws Exception {
		JSONArray collections = thngAPIWrapper.getAllCollections();
		assertNotNull(collections);

		// Debug only:
		this.printItems(collections);
	}
	
	
	/**
	 * Tests GET /collections/:id
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCollection() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		// Create the collection:
		JSONObject expected = this.createTestCollection(identifier);
		assertNotNull(expected);

		// Retrieve the collection using 'id':
		JSONObject actual = thngAPIWrapper.getCollection(expected.getString("id"));
		assertNotNull(actual);
		assertTrue(expected.getString("name").equals(actual.getString("name")));
	}
	
	/**
	 * Tests POST /collections
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * 
	 * @see ThngAPIWrapper#createCollection(String, String, Boolean)
	 * @see ThngAPIWrapper#createCollection(ThngObject)
	 */
	@Test
	public void testCreateCollection() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		JSONObject collection = this.createTestCollection(identifier);
		assertNotNull(collection);
		assertTrue(collection.getString("name").equals(identifier));
	}
	
	/**
	 * Tests PATCH/PUT /collections/:id
	 * 
	 * @see ThngAPIWrapper#deleteThngProperty(String, String)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEditCollection() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		// Create the collection:
		JSONObject created = this.createTestCollection(identifier);
		assertNotNull(created);
		
		// Edit values:
		CollectionObject edited = new CollectionObject("[EDIT] " + created.getString("name"), "[EDIT] " + created.getString("description"), !created.getBoolean("is_public"));
		
		// FIXME: should collection edition return something?
		// Edit the collection using 'id' (and not collection name):
		//		JSONObject actual = thngAPIWrapper.editCollection(created.getString("id"), edited);
		//		assertNotNull(actual);
		//		assertTrue(edited.getName().equals(actual.getString("name")));
		//		assertTrue(edited.getDescription().equals(actual.getString("description")));
		//		assertTrue(edited.getIsPublic().equals(actual.getString("is_public")));
		
		assertTrue(thngAPIWrapper.editCollection(created.getString("id"), edited));
	}
	
	/**
	 * Tests DELETE /collections/:id
	 * 
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * 
	 * @see ThngAPIWrapper#createCollection(String, String, Boolean)
	 * @see ThngAPIWrapper#createCollection(ThngObject)
	 */
	@Test
	public void testDeleteCollection() throws Exception {

		// Generate an unique identifier:
		String identifier = generateUniqueID();

		// Create a reference collection:
		JSONObject created = this.createTestCollection(identifier);
		assertNotNull(created);
		
		// Delete the collection:
		assertTrue(thngAPIWrapper.deleteCollection(created.getString("id")));
	}	
	
	/* ***** PRIVATE ***** */
	
	/**
	 * Creates a random <code>collection</code> with an unique prefixed
	 * <code>name</code>.
	 * 
	 * @see #generateUniqueID()
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException 
	 * @throws ParseException 
	 */
	protected JSONObject createRandomCollection() throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
		// Generate an unique identifier:
		String name = generateUniqueID();

		// Delegate:
		return this.createTestCollection(name);
	}

	/**
	 * Creates a <code>thng</code> with the provided <code>identifier</code>.
	 * 
	 * @param name
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException 
	 * @throws ParseException 
	 */
	protected JSONObject createTestCollection(String name) throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
		return thngAPIWrapper.createCollection(name, "Random unit test created collection!", true);
	}
}
