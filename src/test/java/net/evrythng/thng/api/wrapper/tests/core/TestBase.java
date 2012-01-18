package net.evrythng.thng.api.wrapper.tests.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import net.evrythng.thng.api.result.ThngResult;
import net.evrythng.thng.api.wrapper.ThngAPIWrapper;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestBase {

	private static final Logger logger = LoggerFactory.getLogger(TestBase.class);

	protected static final Random random = new Random();

	// FIXME: members should not be initialized in a static way
	private static Properties config;

	protected static String TOKEN;
	protected static int BATCH_COUNT;

	protected static ThngAPIWrapper wrapper;

	@BeforeClass
	public static void setUp() throws IOException {
		// Load configuration:
		config = new Properties();
		InputStream properties = TestBase.class.getClassLoader().getResourceAsStream("test-config.properties");
		config.load(properties);

		// Load property values:
		TOKEN = getPropertyValue("evrythng.api.token");
		BATCH_COUNT = Integer.valueOf(getPropertyValue("evrythng.api.batch.count"));

		// Check state:
		if (StringUtils.isBlank(TOKEN)) {
			throw new IllegalStateException(
					"An authetication token should be provided! Please set the 'evrythng.api.token' property in the 'test-config.properties' file or use VM arguments (i.e. -Devrythng.api.token=<your token>).");
		}

		// Load API wrapper:
		wrapper = new ThngAPIWrapper(TOKEN);

		// Debug only:
		logger.debug("TestBase loaded! [TOKEN=*****, BATCH_COUNT={}]", BATCH_COUNT);
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
	 * Generate an unique prefixed name for <code>thngs</code>.
	 * 
	 * @return a valid name for model elements
	 */
	protected static String generateUniqueName() {
		return getNamespace() + "." + UUID.randomUUID();
	}

	protected static String getNamespace() {
		return TestBase.class.getPackage().getName();
	}

	/**
	 * Returns a pseudorandomly generated <code>double</code> value in the range
	 * between <code>min</code> and <code>max</code>.
	 * 
	 * @param min
	 *            The (included) lower bound of the range
	 * @param max
	 *            The (included) upper bound of the range
	 * 
	 * @return The random value in the range
	 */
	public static double nextRandom(double min, double max) {
		return min + (Math.random() * (max - min));
	}

	protected static void assertResult(ThngResult result) {
		// Delegate:
		assertResult(result, HttpURLConnection.HTTP_OK);
	}

	protected static void assertResult(ThngResult result, int statusCode) {
		assertNotNull(result);
		assertNotNull(result.getResponse());
		assertEquals(result.getResponse().getStatusCode(), statusCode);
	}

	protected static void assertNotBlank(String text) {
		assertTrue(StringUtils.isNotBlank(text));
	}
}
