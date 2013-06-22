/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * This represents a Product POJO. It is composed of defined fields,
 * directly complying with the hProduct microformat.
 * 
 * @see <a href="http://microformats.org/wiki/hproduct">hProduct</a>, and
 *      flexible fields, in the form of a key/values map. </p>
 * 
 * @author Dominique Guinard (domguinard)
 */
public class Product extends DurableResourceModel implements ResourceWithProperties {

	private String brand;
	private List<String> categories;

	private Map<String, String> properties;

	private String description;
	/**
	 * Product name or title
	 */
	private String fn;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fn == null) ? 0 : fn.hashCode());
		result = prime * result + ((identifiers == null) ? 0 : identifiers.hashCode());
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fn == null) {
			if (other.fn != null)
				return false;
		} else if (!fn.equals(other.fn))
			return false;
		if (identifiers == null) {
			if (other.identifiers != null)
				return false;
		} else if (!identifiers.equals(other.identifiers))
			return false;
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
