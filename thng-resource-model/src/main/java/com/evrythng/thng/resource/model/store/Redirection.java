/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Represents a redirection.
 * 
 * @author Michel Yerly (my)
 **/
public class Redirection extends DurableResourceModel {

	private String targetUrl; //http://example.com/{thngId}
	private String shortDomain; //t.tn.gg
	private String shortId; // 10 digits
	private Integer hits;

	/**
	 * @return the targetUrl
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @param targetUrl
	 *            the targetUrl to set
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
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

}
