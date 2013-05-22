/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

/**
 * Thread-safe version of {@link Ref}.
 * 
 * @author Michel Yerly (my)
 **/
public final class RefTS<T> {

	private volatile T obj = null;

	/**
	 * Creates an instance that holds the null value.
	 */
	public RefTS() {
	}

	/**
	 * Creates an instance that holds the specified value.
	 */
	public RefTS(final T obj) {
		this.obj = obj;
	}

	/**
	 * Gets the value held.
	 */
	public final T get() {
		return obj;
	}

	/**
	 * Sets the value to hold.
	 */
	public final synchronized void set(final T obj) {
		this.obj = obj;
	}
}
