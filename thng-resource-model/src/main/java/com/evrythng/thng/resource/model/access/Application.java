/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.access;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * A registered Application in the EVRYTHNG engine.
 * 
 **/
public class Application extends DurableResourceModel {

	private static final long serialVersionUID = -5251229000992852717L;

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
	 * @deprecated There is not customer in Application since 1.12.
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
