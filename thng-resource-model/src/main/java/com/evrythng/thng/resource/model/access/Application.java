/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.access;

import java.util.HashMap;
import java.util.Map;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A registered Application in the EVRYTHNG engine.
 * 
 **/
public class Application extends DurableResourceModel {

	// === some useful keys ================================================ //

	public static final String SN_FACEBOOK = "facebook";

	private String name;
	private String description;

	private String appApiKey;

	private Map<String, SocialNetwork> socialNetworks;

	private String defaultUrl;

	private Long startAt;
	private Long endAt;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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
	 * @deprecated There is not cutomer in Application since 1.12.
	 * 
	 * @return null
	 */
	@Deprecated
	public String getCustomer() {
		return null;
	}

	/**
	 * Does not set anything.
	 * 
	 * @deprecated There is not cutomer in Application since 1.12.
	 * 
	 */
	public void setCustomer(String customer) {
		// NOP
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

	/**
	 * @return the startAt
	 */
	public Long getStartAt() {
		return startAt;
	}

	/**
	 * @param startAt
	 *            the startAt to set
	 */
	public void setStartAt(Long startAt) {
		this.startAt = startAt;
	}

	/**
	 * @return the endAt
	 */
	public Long getEndAt() {
		return endAt;
	}

	/**
	 * @param endAt
	 *            the endAt to set
	 */
	public void setEndAt(Long endAt) {
		this.endAt = endAt;
	}

	/**
	 * @return the defaultUrl
	 */
	public String getDefaultUrl() {
		return defaultUrl;
	}

	/**
	 * @param defaultUrl
	 *            the defaultUrl to set
	 */
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
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
		Application other = (Application) obj;
		if (appApiKey == null) {
			if (other.appApiKey != null)
				return false;
		} else if (!appApiKey.equals(other.appApiKey))
			return false;
		if (defaultUrl == null) {
			if (other.defaultUrl != null)
				return false;
		} else if (!defaultUrl.equals(other.defaultUrl))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endAt == null) {
			if (other.endAt != null)
				return false;
		} else if (!endAt.equals(other.endAt))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (socialNetworks == null) {
			if (other.socialNetworks != null)
				return false;
		} else if (!socialNetworks.equals(other.socialNetworks))
			return false;
		if (startAt == null) {
			if (other.startAt != null)
				return false;
		} else if (!startAt.equals(other.startAt))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appApiKey == null) ? 0 : appApiKey.hashCode());
		result = prime * result + ((defaultUrl == null) ? 0 : defaultUrl.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endAt == null) ? 0 : endAt.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((socialNetworks == null) ? 0 : socialNetworks.hashCode());
		result = prime * result + ((startAt == null) ? 0 : startAt.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Application [name=" + name + ", description=" + description + ", appApiKey=" + appApiKey + ", startAt=" + startAt + ", endAt=" + endAt + "]";
	}

	// === helpers methods ================================================= //

	/**
	 * This convinience method set / replace the {@link SocialNetwork} object in
	 * the
	 * socialnetworks map. It creates the map if necessary.
	 * 
	 * @param snName
	 * @param socialNetwork
	 */
	@JsonIgnore
	public void defineSocialNetwork(String snName, SocialNetwork socialNetwork) {
		if (socialNetworks == null) {
			socialNetworks = new HashMap<String, SocialNetwork>();
		}

		socialNetworks.put(snName, socialNetwork);
	}

	/**
	 * Set the Facebook social network (key {@value SN_FACEBOOK})
	 * 
	 * @param socialNetwork
	 */
	@JsonIgnore
	public void defineFacebook(SocialNetwork socialNetwork) {
		defineSocialNetwork(SN_FACEBOOK, socialNetwork);
	}
}
