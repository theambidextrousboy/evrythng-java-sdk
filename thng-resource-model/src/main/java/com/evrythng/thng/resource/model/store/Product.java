/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for a product.
 */
public class Product extends DurableResourceModel implements ResourceWithProperties, Serializable {

	private static final long serialVersionUID = -6201425043153000867L;

	private String brand;
	private List<String> categories;

	private Map<String, String> properties;

	private String description;
	/**
	 * Product name or title
	 */
	private String fn;
	public final static String FIELD_FN = "fn";
	/**
	 * URLs to the photos, not the photos themselves
	 */
	private List<String> photos;
	/**
	 * Authoritative URL for this product (where more info can be found)
	 */
	private String url;
	/**
	 * An array of global identifiers for this product
	 */
	private Map<String, String> identifiers;

	public Product() {
	}

	public Product(String brand, List<String> categories, String description, String fn, List<String> photos, String url, Map<String, String> identifiers, Map<String, String> customFields) {
		this.brand = brand;
		this.categories = categories;
		this.description = description;
		this.fn = fn;
		this.photos = photos;
		this.url = url;
		this.identifiers = identifiers;
		this.setCustomFields(customFields);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Map<String, String> identifiers) {
		this.identifiers = identifiers;
	}

	public void addIdentifier(String type, String value) {
		if (identifiers == null) {
			identifiers = new HashMap<String, String>();
		}
		identifiers.put(type, value);
	}

	public String firstIdentifier() {
		return identifiers.values().iterator().next();
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
