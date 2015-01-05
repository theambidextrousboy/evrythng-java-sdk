/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

/**
 * This class contains utility methods for logging
 */
public class LogUtils {

	/**
	 * Masks api key. Example output {@code 'JbXptr...9Ixpze'}
	 *
	 * @param key api key
	 *
	 * @return masked api key
	 */
	public static String maskApiKey(final String key) {

		if (key.length() <= 12) {
			return key;
		} else {
			String beginning = key.substring(0, 6);
			String end = key.substring(key.length() - 6);
			return beginning + "..." + end;
		}
	}
}
