/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper;

/**
 * Configuration parameters and values for the EVRYTHNG Java Wrapper.
 *
 * @author
 *
 */
public class Configuration {
    
    /*
     * EVRYTHNG API
     */
    private String access_token = "";
    private String baseDomainUrl = "http://api.evrythng.com/";
    private String thngServiceContextPath = "";
    private String collectionServiceContextPath = "";
    
    public static String ACCEPT_TYPE = "application/json";
    public static String CONTENT_TYPE = "application/json";
    
    protected boolean isWrapperBehindAccessController;
    
    public Configuration(String accessToken) {
    	this.access_token = accessToken;
    	this.isWrapperBehindAccessController = false;
    }

    /**
	 * @return the isWrapperBehindAccessController
	 */
	public boolean isWrapperBehindAccessController() {
		return isWrapperBehindAccessController;
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
    
}
