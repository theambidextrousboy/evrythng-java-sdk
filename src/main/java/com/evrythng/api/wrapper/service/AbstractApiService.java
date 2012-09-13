package com.evrythng.api.wrapper.service;


import java.net.URI;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.util.URIBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * API service which facilitates provides helper
 * methods for performing remote method calls as well as deserializing the
 * corresponding JSON responses.
 *
 * @author tpham
 */
public abstract class AbstractApiService {

    protected Configuration config; 
    private static final Logger logger = LoggerFactory.getLogger(AbstractApiService.class);
    private static String API_URL_BASE = "https://api.evrythng.com/";
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
    protected RestTemplate restTemplate;
    protected String contextPath;
    
    public AbstractApiService(Configuration config) {
        this.config = config;
        if (config.getBaseDomainUrl() != null) {
            API_URL_BASE = config.getBaseDomainUrl();
        }
        this.restTemplate = new RestTemplate(config);
    }
    
    /**
	 * @param contextPath the contextPath to set
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	/**
	 * @return the contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}
	
	protected URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    protected URI buildUri(String path, String parameterName, String parameterValue) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(parameterName, parameterValue);
        return buildUri(path, parameters);
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
    	if (this.contextPath != null && this.contextPath.isEmpty()){
    		path = String.format("%s%s", this.contextPath, path);
    	}
        return URIBuilder.fromUri(API_URL_BASE + path).queryParams(parameters).build();
    }
    
    protected MultiValueMap<String, String> buildPagingParameters(Integer page) {
    	return buildPagingParameters(page, 30);
	}
    
    protected MultiValueMap<String, String> buildPagingParameters(Integer page, Integer size) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("page", String.valueOf(page));
		parameters.set("size", String.valueOf(size));
		return parameters;
	}
    
    protected MultiValueMap<String, String> buildPagingParametersFrom(Integer page, Integer size, Long from){
		return buildPagingParametersFromTo(page,size, from, null);
    }
    
    protected MultiValueMap<String, String> buildPagingParametersTo(Integer page, Integer size, Long to){
    	return buildPagingParametersFromTo(page,size, null, to);
    }
    
    protected MultiValueMap<String, String> buildPagingParametersFromTo(Integer page, Integer size, Long from,Long to){
    	MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set("page", String.valueOf(page));
		parameters.set("size", String.valueOf(size));
		if (from != null)
			parameters.set("from", String.valueOf(from));
		if (to != null)
			parameters.set("to", String.valueOf(to));
		return parameters;
    }
        
}