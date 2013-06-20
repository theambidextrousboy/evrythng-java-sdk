/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.access;

import java.util.HashMap;
import java.util.Map;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * A registered Application in the EVRYTHNG engine.
 * 
 **/
public class Application extends DurableResourceModel {

	private String description;
	private String customer;

	private String appApiKey;

	private Map<String, SocialNetwork> socialNetworks = new HashMap<String, SocialNetwork>();

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the socialNetworks
	 */
	public Map<String, SocialNetwork> getSocialNetworks() {
		return socialNetworks;
	}

	/**
	 * @param socialNetworks
	 *            the socialNetworks to set
	 */
	public void setSocialNetworks(Map<String, SocialNetwork> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}

	/**
	 * @return the appApiKey
	 */
	public String getAppApiKey() {
		return appApiKey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Application that = (Application) o;

		return !(description != null ? !description.equals(that.description) : that.description != null) && customer.equals(that.customer) && appApiKey.equals(that.appApiKey)
				&& socialNetworks.equals(that.socialNetworks);
	}

	@Override
	public int hashCode() {
		int result = customer.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (appApiKey != null ? appApiKey.hashCode() : 0);
		result = 31 * result + (socialNetworks != null ? socialNetworks.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Application{" + "id='" + id + '\'' + "customer='" + customer + '\'' + ", description='" + description + '\'' + ", socialNetworks='" + socialNetworks + '\'' + '}';
	}

}
