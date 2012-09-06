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
    public String apiKey = getString("evrythng.api.access_token");
    public static final String BASE_DOMAIN_URL = getString("evrythng.api.baseDomainUrl");
    public static final String ACCEPT_TYPE = getString("accept-type");
    public static final String CONTENT_TYPE = getString("content-type");
    
    private HttpClientParams httpClientParams;

    public Configuration() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

    private static int getInt(String key) {
        return Integer.valueOf(getString(key));
    }

	/**
	 * @return the httpClientParams
	 */
	public HttpClientParams getHttpClientParams() {
		return httpClientParams;
	}

	/**
	 * @param httpClientParams the httpClientParams to set
	 */
	public void setHttpClientParams(HttpClientParams httpClientParams) {
		this.httpClientParams = httpClientParams;
	}
}
