/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.commons;

/**
 * Class that holds a value. Useful for passing arguments by reference.
 * 
 * @author Michel Yerly (my)
 **/
public class Ref<T> {

	private T obj = null;

	/**
	 * Creates an instance that holds the null value.
	 */
	public Ref() {
	}

	/**
	 * Creates an instance that holds the specified value.
	 */
	public Ref(T obj) {
		this.obj = obj;
	}

	/**
	 * Gets the value held.
	 */
	public T get() {
		return obj;
	}

	/**
	 * Sets the value to hold.
	 */
	public void set(T obj) {
		this.obj = obj;
	}
}
