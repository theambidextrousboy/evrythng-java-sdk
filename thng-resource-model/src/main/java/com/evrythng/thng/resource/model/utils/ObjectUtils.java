/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.utils;

/**
 * Provides utility method for objects.
 * 
 * @author Michel Yerly (my)
 **/
public class ObjectUtils {

	public static boolean nullSafeEquals(Object a, Object b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}

}
