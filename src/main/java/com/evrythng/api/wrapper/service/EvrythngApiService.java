package com.evrythng.api.wrapper.service;


import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API service which facilitates provides helper
 * methods for performing remote method calls as well as deserializing the
 * corresponding JSON responses.
 *
 * @author tpham
 */
public abstract class EvrythngApiService {

    private Configuration config; 
    private static final Logger logger = LoggerFactory.getLogger(EvrythngApiService.class);
    

    public EvrythngApiService(Configuration config) {
        this.config = config;
    }

    /**
     * This method calls POST on a resource, i.e., it creates a resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be POSTed
     * @param cl     Class of the POSTed resource
     *
     * @return The response mapper to the given type
     * @throws URISyntaxException 
     */
    protected <T> T post(String path, Map<String,String> queryparams, T object, TypeReference<T> typeToken) throws URISyntaxException {
        HttpClient httpclient = new DefaultHttpClient();
        
        URIBuilder uriBuilder = new URIBuilder(Configuration.BASE_DOMAIN_URL + path);
        for (String key : queryparams.keySet()){
        	uriBuilder.addParameter(key, queryparams.get(key));
        }
        
        HttpPost request = new HttpPost(uriBuilder.toString());
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        request.addHeader("Content-Type", Configuration.CONTENT_TYPE);
        addCredentials(request);

        try {
            logRequest(request.getMethod(), request.getURI());
            request.setEntity(new StringEntity(JSONUtils.getObjectMapper().writeValueAsString(object)));
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return JSONUtils.getObjectMapper().readValue(entity.getContent(), typeToken);
        } catch (IllegalStateException e) {
        	logger.error("Error while POSTting a resource", e);
        } catch (IOException e) {
        	logger.error("Error while POSTting a resource", e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

    /**
     * This method calls PUT on a resource, i.e., it updates an existing
     * resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be PUT
     * @param cl     Class of the PUT resource
     *
     * @return The response mapper to the given type
     */
    protected <T> T put(String path, Map<String,String> queryparams, T object, TypeReference<T> typeToken) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut request = new HttpPut(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        request.addHeader("Content-Type", Configuration.CONTENT_TYPE);    
        addCredentials(request);

        try {
            logRequest(request.getMethod(), request.getURI());

            request.setEntity(new StringEntity(JSONUtils.getObjectMapper().writeValueAsString(object)));
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return JSONUtils.getObjectMapper().readValue(entity.getContent(), typeToken);

        } catch (IllegalStateException e) {
        	logger.error("Error while PUTting a resource", e);
        } catch (IOException e) {
        	logger.error("Error while PUTting a resource", e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

    
    
    
    /**
     * This method calls GET on a resource, i.e., it retrieves a resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be POSTed
     * @param cl     Class of the POSTed resource
     *
     * @return The response mapper to the given type
     */
    protected <T> T get(String path, Map<String,String> queryparams, TypeReference<T> typeToken) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addCredentials(request);

        try {
            logRequest(request.getMethod(), request.getURI());
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return unmarshall(entity.getContent() , typeToken);

        } catch (IllegalStateException e) {
        	logger.error("Error while GETting a resource", e);
        } catch (IOException e) {
        	logger.error("Error while GETting a resource", e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

    /**
     *
     * /**
     * This method calls DELETE on a resource, i.e., it removes a resource.
     *
     * @param path path to the resource
     *
     * @return The HttpResponse
     */
    protected HttpResponse delete(String path, Map<String,String> queryparams) {
        HttpClient httpclient = new DefaultHttpClient();        
        HttpDelete request = new HttpDelete(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addCredentials(request);

        try {
            logRequest(request.getMethod(), request.getURI()); 
            return httpclient.execute(request);

        } catch (IllegalStateException e) {
        	logger.error("Error while DELETting a resource", e);
        } catch (IOException e) {
        	logger.error("Error while DELETting a resource", e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }
    
    protected Header getHeader(String path, Map<String,String> queryparams, String headerName) {
    	HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addCredentials(request);

        try {
            logRequest(request.getMethod(), request.getURI());

            HttpResponse response = httpclient.execute(request);
            return response.getFirstHeader(headerName);
        } catch (IllegalStateException e) {
        	logger.error("Error while GETting a resource", e);
        } catch (IOException e) {
        	logger.error("Error while GETting a resource", e);
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

    /**
     * Adds an API key to a request.
     *
     * @param request
     * @param key
     *
     * @return
     */
    protected HttpRequest addCredentials(HttpRequest request) {
        if (config.getApiKey() != null) {
            request.addHeader("Authorization", config.getApiKey());
            //setParams(request.getParams().setParameter("access_token", config.getApiKey()));
        }
        return request;
    }

    protected void printResponse(HttpResponse response) {
        String inputLine;
        HttpEntity entity = response.getEntity();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
        	logger.error("Error while printing response", e);
        }
    }

    private void logRequest(String method, URI url) {
    	logger.debug("Calling " + method + " on: " + url.toASCIIString());
    }

    /**
     * Use Jackson to deserialize a JSON object to a native class
     * representation.
     *
     * @param <T>       Native class type.
     * @param typeToken Native class type wrapper.
     * @param entity  Serialized JSON object.
     *
     * @return Deserialized native instance.
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(InputStream entity, TypeReference<T> typeToken) throws JsonParseException, JsonMappingException, IOException {
        return (T) JSONUtils.getObjectMapper().readValue(entity, typeToken);
    }

    /**
     * Use Jackson to deserialize a JSON string to a native class
     * representation.
     *
     * @param <T>       Native class type.
     * @param typeToken Native class type wrapper.
     * @param entity   Serialized JSON string.
     *
     * @return Deserialized native instance.
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(String entity, Class<?> type) throws JsonParseException, JsonMappingException, IOException {
        return (T) JSONUtils.getObjectMapper().readValue(entity, type);
    }

    

    
}