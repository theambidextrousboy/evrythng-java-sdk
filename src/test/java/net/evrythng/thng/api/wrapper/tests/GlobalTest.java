package net.evrythng.thng.api.wrapper.tests;

import static org.junit.Assert.assertTrue;
import net.evrythng.thng.api.wrapper.tests.core.TestBase;

import org.junit.Test;

public class GlobalTest extends TestBase {

	/**
	 * Tests GET /
	 * @throws Exception
	 */
	@Test
	public void testConnection()  throws Exception {
		assertTrue(wrapper.ping());
	}
	
}
