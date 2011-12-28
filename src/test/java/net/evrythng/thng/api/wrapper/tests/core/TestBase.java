package net.evrythng.thng.api.wrapper.tests.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import net.evrythng.thng.api.wrapper.ThngAPIWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;

public abstract class TestBase {

	// FIXME: members should not be initialized in a static way
	private static Properties config;
	
	protected static int BATCH_COUNT;
	protected static String TOKEN;

	protected static ThngAPIWrapper wrapper;
	
	protected Random random = new Random();

	@BeforeClass
	public static void setUp() throws IOException {
		// Load configuration:
		config = new Properties();
		InputStream properties = TestBase.class.getClassLoader()
				.getResourceAsStream("config.properties");
		config.load(properties);
		
		// Load property values:
		TOKEN = getPropertyValue("evrythng.api.token");
		BATCH_COUNT = Integer.valueOf(getPropertyValue("evrythng.api.batch.count"));

		// Check state:
		if (StringUtils.isBlank(TOKEN)) {
			throw new IllegalStateException("An authetication token should be provided! Please set the 'evrythng.api.token' property in the 'config.properties' file.");
		}
		
		// Load API wrapper:
		wrapper = new ThngAPIWrapper(TOKEN);
		
		// Debug only:
		System.out.println("TestBase loaded! [TOKEN=*****, BATCH_COUNT=" + BATCH_COUNT + "]");
	}

	/**
	 * Retrieves the value of the provided <code>key</code> property. Property
	 * value will be retrieved in the following order:
	 * <ol>
	 * <li>from VM arguments (i.e. using <code>-Dkey=value</code>)</li>
	 * <li>from <code>config.properties</code> file</li>
	 * </ol>
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static String getPropertyValue(String key) throws IOException {
		String value = System.getProperty(key);
		if (StringUtils.isBlank(value)) {
			// Lookup value from config:
			value = config.getProperty(key);
		}
		return value;
	}

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
	protected JSONObject createRandomThng() throws ClientProtocolException,
			JSONException, IOException, ParseException, URISyntaxException {
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
	protected JSONObject createTestThng(String identifier)
			throws ClientProtocolException, JSONException, IOException,
			ParseException, URISyntaxException {
		return wrapper.createThng(identifier, "Random unit test created thng!",
				true);
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
