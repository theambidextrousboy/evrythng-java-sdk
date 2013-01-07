/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.LinkedHashSet;

import javax.validation.constraints.NotNull;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>collections</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 * @author Michel Yerly (my)
 **/
public class Collection extends DurableResourceModel {

	@NotNull
	private String name;

	private String description;

	/**
	 * Collection of {@link Thng#id} references.
	 */
	private LinkedHashSet<String> thngs;

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

	public LinkedHashSet<String> getThngs() {
		return thngs;
	}

	public void setThngs(LinkedHashSet<String> thngs) {
		this.thngs = thngs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Collection that = (Collection) o;

		return !(description != null ? !description.equals(that.description) : that.description != null) && name.equals(that.name) && !(thngs != null ? !thngs.equals(that.thngs) : that.thngs != null);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (thngs != null ? thngs.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Collection{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", thngs=" + thngs + '}';
	}
}
