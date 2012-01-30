/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.result;

import org.apache.http.HttpResponse;

public class ThngResponse {

	private final HttpResponse httpResponse;
	private final int statusCode;

	/**
	 * Creates a new instance of {@link ThngResponse} using the provided
	 * {@link HttpResponse}.
	 * 
	 * @param httpResponse
	 */
	public ThngResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;

		// Extract important headers:
		this.statusCode = httpResponse.getStatusLine().getStatusCode();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + getHttpResponse().getStatusLine() + "]";
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
