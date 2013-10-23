/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SocialNetwork {
	FACEBOOK("facebook"), EVRYONE("evryone");

	private static Map<String, SocialNetwork> serNames = new HashMap<String, SocialNetwork>();
	private String serName;

	SocialNetwork(String serName) {
		this.serName = serName;
	}

	static {
		for (SocialNetwork t : values()) {
			serNames.put(t.serName, t);
		}
	}

	@JsonValue
	public String toString() {
		return serName;
	}

	@JsonCreator
	public static SocialNetwork fromString(String serName) {
		if (serName == null) {
			return null;
		}
		SocialNetwork t = serNames.get(serName);
		if (t == null) {
			throw new IllegalArgumentException("Invalid enum value.");
		}
		return t;
	}
}
