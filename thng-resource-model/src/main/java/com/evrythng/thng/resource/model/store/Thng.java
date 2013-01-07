/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.evrythng.thng.resource.model.core.PropertiesResourceModel;

/**
 * Model representation for <em>thngs</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class Thng extends PropertiesResourceModel {

	@NotNull
	private String name;

	private String description;

	@Valid
	private Location location;

	/**
	 * Reference to {@link Product#id}.
	 */
	private String product;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
