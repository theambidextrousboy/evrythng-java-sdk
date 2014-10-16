/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link JSONUtils}.
 */
public class JSONUtilsTest {

	private static class MyObject {

		private String myProp;
		private String b;

		public String getMyProp() {
			return myProp;
		}

		public void setMyProp(String myProp) {
			this.myProp = myProp;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}

	@Test
	public void deserialize() {

		String json = "{\"myProp\":\"1\",\"b\":\"2\" }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals("2", o.getB());
	}

	@Test
	public void deserializeLess() {

		String json = "{\"myProp\":\"1\" }";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertNull(o.getB());
	}

	@Test
	public void deserializeMore() {

		String json = "{\"myProp\":\"1\",\"b\":\"2\", \"unexpected\":\"3\"}";

		MyObject o = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals("1", o.getMyProp());
		Assert.assertEquals("2", o.getB());
	}

	@Test
	public void serialize() {

		MyObject exp = new MyObject();
		exp.setMyProp("1");
		exp.setB("2");

		String json = JSONUtils.write(exp);

		MyObject act = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals(exp.getMyProp(), act.getMyProp());
		Assert.assertEquals(exp.getB(), act.getB());
	}

	@Test
	public void serializeLess() {

		MyObject exp = new MyObject();
		exp.setMyProp("1");

		String json = JSONUtils.write(exp);

		MyObject act = JSONUtils.read(json, new TypeReference<MyObject>() {
		});

		Assert.assertEquals(exp.getMyProp(), act.getMyProp());
		Assert.assertEquals(exp.getB(), act.getB());
	}
}
