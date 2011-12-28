package net.evrythng.thng.api.wrapper.tests.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.UUID;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.evrythng.thng.api.wrapper.ThngAPIWrapper;

public abstract class TestBase {

	protected static final int BATCH_COUNT = 10; // TODO: extract to property file!
	protected String token = "e27701f377341b8df7f1d194bc7dadafa15dd155a455f921a1cd60bdfd9d2b84"; // TODO: extract from here!
	
	protected ThngAPIWrapper thngAPIWrapper = new ThngAPIWrapper(token);
	protected Random random = new Random();
	
	
	/**
	 * Creates a random <code>thng</code> with an unique prefixed
	 * <code>identifier</code>.
	 * 
	 * @see #generateUniqueID()
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException 
	 * @throws ParseException 
	 */
	protected JSONObject createRandomThng() throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
		// Generate an unique identifier:
		String identifier = generateUniqueID();

		// Delegate:
		return this.createTestThng(identifier);
	}

	/**
	 * Creates a <code>thng</code> with the provided <code>identifier</code>.
	 * 
	 * @param identifier
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException 
	 * @throws ParseException 
	 */
	protected JSONObject createTestThng(String identifier) throws ClientProtocolException, JSONException, IOException, ParseException, URISyntaxException {
		return thngAPIWrapper.createThng(identifier, "Random unit test created thng!", true);
	}
	
	/**
	 * Generate an unique prefixed identifier for <code>thngs</code>.
	 * 
	 * @return a valid identifier for <code>thngs</code>
	 */
	protected String generateUniqueID() {
		String thngID = getNamespace() + "." + UUID.randomUUID();
		return thngID;
	}

	/**
	 * @return
	 */
	protected String getNamespace() {
		return this.getClass().getPackage().getName();
	}
	
	/**
	 * @param items
	 * @throws JSONException
	 */
	protected void printItems(JSONArray items) throws JSONException {
		System.out.println("> " + items.length() + " items(s) found!");
		for (int i = 0; i < items.length(); i++) {
			System.out.println(">> " + i + " => " + items.get(i));
		}
	}
}
