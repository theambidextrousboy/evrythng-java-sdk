/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.exception.InternalErrorException;
import com.evrythng.api.wrapper.exception.NotFoundException;
import com.evrythng.api.wrapper.util.JSONUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * TODO Comment this class
 * 
 * @author Username (tpham)
 * @copyright 2012 Evrythng Ltd London / Zurich
 **/

public class RestTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(RestTemplate.class);
	private Configuration config;
	
	public RestTemplate(Configuration config){
		this.config = config;
	}
	
	/**
	 * This method calls GET on a resource, i.e., it retrieves a resource.
	 * 
	 * @param <T>
	 *            Type to be mapped for response
	 * @param path
	 *            path to the resource
	 * @param object
	 *            resource to be POSTed
	 * @param cl
	 *            Class of the POSTed resource
	 * 
	 * @return The response mapper to the given type
	 */
	protected <T> T get(URI uri, TypeReference<T> typeToken) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(uri);
		request.addHeader("Accept", Configuration.ACCEPT_TYPE);
		if (config.getAccessToken() != null) {
			request.addHeader("Authorization", config.getAccessToken());
		}

		try {
			logRequest(request.getMethod(), request.getURI());
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode != 200)
//				createException(statusCode, unmarshall(entity.getContent(), new TypeReference<ErrorMessage>() {}));
			
			return unmarshall(entity.getContent(), typeToken);

		} catch (Exception e) {
			logger.error("Error while Geting a resource", e);
			throw new RuntimeException("Error while Geting a resource", e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * This method calls PUT on a resource, i.e., it updates an existing
	 * resource.
	 * 
	 * @param <T>
	 *            Type to be mapped for response
	 * @param path
	 *            path to the resource
	 * @param object
	 *            resource to be PUT
	 * @param cl
	 *            Class of the PUT resource
	 * 
	 * @return The response mapper to the given type
	 */
	protected <T> T put(URI uri, T object, TypeReference<T> typeToken) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPut request = new HttpPut(uri);
		request.addHeader("Accept", Configuration.ACCEPT_TYPE);
		request.addHeader("Content-Type", Configuration.CONTENT_TYPE);
		if (config.getAccessToken() != null) {
			request.addHeader("Authorization", config.getAccessToken());
		}

		try {
			logRequest(request.getMethod(), request.getURI());
			request.setEntity(new StringEntity(JSONUtils.getObjectMapper().writeValueAsString(object)));
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			return unmarshall(entity.getContent(), typeToken);

		} catch (Exception e) {
			logger.error("Error while Updating a resource", e);
			throw new RuntimeException("Error while Updating a resource", e);
		}  finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	
	/**
	 * This method calls POST on a resource, i.e., it creates a resource.
	 * 
	 * @param <T>
	 *            Type to be mapped for response
	 * @param path
	 *            path to the resource
	 * @param object
	 *            resource to be POSTed
	 * @param cl
	 *            Class of the POSTed resource
	 * 
	 * @return The response mapper to the given type
	 * @throws URISyntaxException
	 */
	protected <T> T post(URI uri, T object, TypeReference<T> typeToken) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(uri);
		request.addHeader("Accept", Configuration.ACCEPT_TYPE);
		request.addHeader("Content-Type", Configuration.CONTENT_TYPE);
		if (config.getAccessToken() != null) {
			request.addHeader("Authorization", config.getAccessToken());
		}

		try {
			logRequest(request.getMethod(), request.getURI());
			request.setEntity(new StringEntity(JSONUtils.getObjectMapper().writeValueAsString(object)));
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			return unmarshall(entity.getContent(), typeToken);
		} catch (Exception e) {
			logger.error("Error while Creating a resource", e);
			throw new RuntimeException("Request failed.", e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 
	 * /**
	 * This method calls DELETE on a resource, i.e., it removes a resource.
	 * 
	 * @param path
	 *            path to the resource
	 * 
	 * @return The HttpResponse
	 */
	protected HttpResponse delete(URI uri) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete request = new HttpDelete(uri);
		request.addHeader("Accept", Configuration.ACCEPT_TYPE);
		if (config.getAccessToken() != null) {
			request.addHeader("Authorization", config.getAccessToken());
		}

		try {
			logRequest(request.getMethod(), request.getURI());
			return httpclient.execute(request);

		} catch (Exception e) {
			logger.error("Error while Deleting a resource", e);
			throw new RuntimeException("Error while Deleting a resource", e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	protected Header getHttpHeader(URI uri, String headerName) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(uri);
		request.addHeader("Accept", Configuration.ACCEPT_TYPE);
		if (config.getAccessToken() != null) {
			request.addHeader("Authorization", config.getAccessToken());
		}

		try {
			logRequest(request.getMethod(), request.getURI());
			HttpResponse response = httpclient.execute(request);
			return response.getFirstHeader(headerName);
		} catch (Exception e) {
			logger.error("Error while Getting header of a resource", e);
			throw new RuntimeException("Error while Getting header of a resource", e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * Use Jackson to deserialize a JSON object to a native class
	 * representation.
	 * 
	 * @param typeToken
	 *            Native class type wrapper.
	 * @param entity
	 *            Serialized JSON object.
	 * 
	 * @return Deserialized native instance.
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@SuppressWarnings("unchecked")
	protected <T> T unmarshall(String entity, TypeReference<T> typeToken) throws JsonParseException, JsonMappingException, IOException {
		return (T) JSONUtils.getObjectMapper().readValue(entity, typeToken);
	}
	
	/**
	 * Use Jackson to deserialize a JSON object to a native class
	 * representation.
	 * 
	 * @param typeToken
	 *            Native class type wrapper.
	 * @param entity
	 *            Serialized JSON object.
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
	 * @param type
	 *            Native class type wrapper.
	 * @param entity
	 *            Serialized JSON string.
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

	private void logRequest(String method, URI url) {
    	logger.debug("Calling " + method + " on: " + url.toASCIIString());
    }
	
	private static EvrythngException createException(int code, String message) {	
		switch (code){
			 case 400: 
				    return new NotFoundException(message);
			 case 500: 
				    return new InternalErrorException(message);
				    		    
		}
		return new EvrythngException(message);
	}
}
