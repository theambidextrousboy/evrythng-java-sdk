/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper;

import java.util.ResourceBundle;

import org.apache.http.client.params.HttpClientParams;

/**
 * Configuration parameters and values for the EVRYTHNG Java Wrapper.
 *
 * @author
 *
 */
public class Configuration {

    /**
     * The filename of the configuration properties file.
     */
    private static final String BUNDLE_NAME = "wrapper";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    /*
     * EVRYTHNG API
     */
    private String access_token = getString("evrythng.api.access_token");
    private String baseDomainUrl = getString("evrythng.api.baseDomainUrl");
    private String thngServiceContextPath = getString("evrythng.engine.thng-store.contextPath");
    private String productServiceContextPath = getString("evrythng.engine.thng-store.contextPath");
    private String collectionServiceContextPath = getString("evrythng.engine.thng-store.contextPath");
    
    public static String ACCEPT_TYPE = getString("accept-type");
    public static String CONTENT_TYPE = getString("content-type");
    
    public Configuration(){
    }

    public Configuration(String accessToken) {
    	this.access_token = accessToken;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }
    
    public String getBaseDomainUrl(){
    	return this.baseDomainUrl;
    }
    
    public void setBaseDomainUrl(String baseDomainUrl){
    	this.baseDomainUrl = baseDomainUrl;
    }
    
    /**
	 * @return the thngServiceContextPath
	 */
	public String getThngServiceContextPath() {
		return thngServiceContextPath;
	}

	/**
	 * @param thngServiceContextPath the thngServiceContextPath to set
	 */
	public void setThngServiceContextPath(String thngServiceContextPath) {
		this.thngServiceContextPath = thngServiceContextPath;
	}

	/**
	 * @return the productServiceContextPath
	 */
	public String getProductServiceContextPath() {
		return productServiceContextPath;
	}

	/**
	 * @param productServiceContextPath the productServiceContextPath to set
	 */
	public void setProductServiceContextPath(String productServiceContextPath) {
		this.productServiceContextPath = productServiceContextPath;
	}

	/**
	 * @return the collectionServiceContextPath
	 */
	public String getCollectionServiceContextPath() {
		return collectionServiceContextPath;
	}

	/**
	 * @param collectionServiceContextPath the collectionServiceContextPath to set
	 */
	public void setCollectionServiceContextPath(String collectionServiceContextPath) {
		this.collectionServiceContextPath = collectionServiceContextPath;
	}

	protected static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

    protected static int getInt(String key) {
        return Integer.valueOf(getString(key));
    }
    
    
}
