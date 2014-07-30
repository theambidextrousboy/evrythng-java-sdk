/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.li;

import com.evrythng.thng.resource.model.core.DurableResourceModel;
import com.evrythng.thng.resource.model.core.EvrythngType;

/**
 * URL binding model.
 * 
 */
public abstract class AbstractUrlBinding extends DurableResourceModel {

	public static final String EVRYTHNGID_TAG = "{evrythngId}";
	public static final String SHORTID_TAG = "{shortId}";
	private String shortDomain;
	private String defaultRedirectUrl;
	private EvrythngType type;
	private String evrythngUrl;

	public AbstractUrlBinding() {
	}

	public AbstractUrlBinding(String shortDomain) {
		this.shortDomain = shortDomain;
	}

	protected AbstractUrlBinding(AbstractUrlBinding that) {
		this(that.shortDomain);
		this.setDefaultRedirectUrl(that.getDefaultRedirectUrl());
		this.setEvrythngUrl(that.getEvrythngUrl());
		this.setType(that.getType());
		this.setId(that.getId());
		this.setCreatedAt(that.getCreatedAt());
		this.setUpdatedAt(that.getUpdatedAt());
		this.setCustomFields(that.getCustomFields());
		this.setTags(that.getTags());
	}

	public String getDefaultRedirectUrl() {
		return defaultRedirectUrl;
	}

	public void setDefaultRedirectUrl(String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	public String getShortDomain() {
		return shortDomain;
	}

	public void setShortDomain(String shortDomain) {
		this.shortDomain = shortDomain;
	}

	/**
	 * @return the type
	 */
	public EvrythngType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(EvrythngType type) {
		this.type = type;
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
