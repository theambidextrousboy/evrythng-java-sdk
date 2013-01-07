/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import javax.validation.constraints.NotNull;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;

/**
 * Model representation for <em>properties</em>.
 * 
 * @author Michel Yerly (my)
 **/
public class Property extends TemporalResourceModel {

	@NotNull
	private String thng;

	@NotNull
	private String key;

	@NotNull
	private String value;

	/**
	 * Creates a new empty instance of {@link Property}.
	 */
	public Property() {
		/* Allow dynamic instantiation */
	}

	/**
	 * Creates a new instance of {@link Property}.
	 * 
	 * @param key
	 * @param value
	 */
	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Creates a new instance of {@link Property}.
	 * 
	 * @param key
	 * @param value
	 */
	public Property(String thng, String key, String value, Long timestamp) {
		super(timestamp);
		this.thng = thng;
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the thng
	 */
	public String getThng() {
		return thng;
	}

	/**
	 * @param thng
	 *        the thng to set
	 */
	public void setThng(String thng) {
		this.thng = thng;
	}
}
