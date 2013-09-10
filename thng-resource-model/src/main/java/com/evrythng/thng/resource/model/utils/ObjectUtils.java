/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.utils;

import java.util.Comparator;

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

	/**
	 * NOTE: <code>null</code> is treated as the smallest value.
	 */
	public static <T> int nullSafeCompare(T a, T b, Comparator<? super T> comp) {
		if (a == null && b == null) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		return normalizeCompareResult(comp.compare(a, b));
	}

	/**
	 * NOTE: <code>null</code> is treated as the smallest value.
	 */
	public static <T extends Comparable<T>> int nullSafeCompare(T a, T b) {
		if (a == null && b == null) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		return normalizeCompareResult(a.compareTo(b));
	}

	private static int normalizeCompareResult(int x) {
		return x > 0 ? 1 : (x < 0 ? -1 : 0);
	}
}
