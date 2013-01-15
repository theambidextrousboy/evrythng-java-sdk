/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Represents a redirector.
 * 
 * @author Michel Yerly (my)
 **/
public class Redirector extends DurableResourceModel {

	public static final String THNGID_TAG = "{thngId}";
	public static final String SHORTID_TAG = "{shortId}";

	private String defaultRedirectUrl; //http://example.com/{thngId}
	private String evrythngUrl;
	private String shortDomain; //tn.gg
	private String shortId; // 10 digits
	private Integer hits;

	/**
	 * @return the defaultRedirectUrl
	 */
	public String getDefaultRedirectUrl() {
		return defaultRedirectUrl;
	}

	/**
	 * @param defaultRedirectUrl
	 *            the defaultRedirectUrl to set
	 */
	public void setDefaultRedirectUrl(String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	/**
	 * @return the shortDomain
	 */
	public String getShortDomain() {
		return shortDomain;
	}

	/**
	 * @param shortDomain
	 *            the shortDomain to set
	 */
	public void setShortDomain(String shortDomain) {
		this.shortDomain = shortDomain;
	}

	/**
	 * @return the shortId
	 */
	public String getShortId() {
		return shortId;
	}

	/**
	 * @param shortId
	 *            the shortId to set
	 */
	public void setShortId(String shortId) {
		this.shortId = shortId;
	}

	/**
	 * @return the hits
	 */
	public Integer getHits() {
		return hits;
	}

	/**
	 * @param hits
	 *            the hits to set
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}

	/**
	 * @return the evrythngUrl
	 */
	public String getEvrythngUrl() {
		return evrythngUrl;
	}

	/**
	 * @param evrythngUrl
	 *            the evrythngUrl to set
	 */
	public void setEvrythngUrl(String evrythngUrl) {
		this.evrythngUrl = evrythngUrl;
	}

}
