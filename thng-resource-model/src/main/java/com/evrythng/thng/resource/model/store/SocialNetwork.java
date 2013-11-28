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
	FACEBOOK("facebook", "fb"), EVRYONE("evryone", "evo");

	private static Map<String, SocialNetwork> serNames = new HashMap<String, SocialNetwork>();
	private static Map<String, SocialNetwork> prefixes = new HashMap<String, SocialNetwork>();
	private String serName;
	private String prefix;

	SocialNetwork(String serName, String prefix) {
		this.serName = serName;
		this.prefix = prefix;
	}

	static {
		for (SocialNetwork t : values()) {
			serNames.put(t.serName, t);
			prefixes.put(t.prefix, t);
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

	public String getPrefix() {
		return prefix;
	}

	public static SocialNetwork fromPrefix(String prefix) {
		SocialNetwork t = prefixes.get(prefix);
		if (t == null) {
			throw new IllegalArgumentException("Invalid prefix.");
		}
		return t;
	}
}
