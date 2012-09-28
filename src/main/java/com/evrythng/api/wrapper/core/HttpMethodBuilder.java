/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import com.evrythng.api.wrapper.exception.EvrythngException;
import com.evrythng.api.wrapper.util.JSONUtils;

/**
 * TODO: comment this class
 * 
 * @author Pedro De Almeida (almeidap)
 **/
public class HttpMethodBuilder {

	public interface MethodBuilder<T extends HttpRequestBase> {
		T build(URI uri);
	}

	public static abstract class EntityMethodBuilder<E extends HttpEntityEnclosingRequestBase> implements MethodBuilder<E> {

		protected E entity(E request, final Object data) {
			// Set request entity:
			try {
				request.setEntity(new StringEntity(JSONUtils.write(data)));
			} catch (Exception e) {
				throw new EvrythngException("Unable to define request entity: [data={}]", e);
			}
			return request;
		}
	}

	public static MethodBuilder<HttpPost> httpPost(final Object data) {
		return new EntityMethodBuilder<HttpPost>() {
			@Override
			public HttpPost build(URI uri) {
				HttpPost request = new HttpPost(uri);
				entity(request, data);
				return request;
			}
		};
	}

	public static MethodBuilder<HttpGet> httpGet() {
		return new MethodBuilder<HttpGet>() {
			@Override
			public HttpGet build(URI uri) {
				return new HttpGet(uri);
			}
		};
	}

	public static MethodBuilder<HttpPut> httpPut(final Object data) {
		return new EntityMethodBuilder<HttpPut>() {
			@Override
			public HttpPut build(URI uri) {
				HttpPut request = new HttpPut(uri);
				entity(request, data);
				return request;
			}
		};
	}

	public static MethodBuilder<HttpDelete> httpDelete() {
		return new MethodBuilder<HttpDelete>() {
			@Override
			public HttpDelete build(URI uri) {
				return new HttpDelete(uri);
			}
		};
	}
}
