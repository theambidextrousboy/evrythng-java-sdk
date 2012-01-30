/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.result;

import org.apache.http.HttpResponse;

/**
 * This is used to hold the result of an API call when no entity-body is returned.
 * (e.g., 200 OK with no body).
 * @author domguinard
 */
public class EvrythngResult {

	private final ThngResponse response;

	/**
	 * Creates a new instance of {@link EvrythngResult} using the provided
	 * {@link HttpResponse}.
	 * 
	 * @param httpResponse
	 */
	public EvrythngResult(HttpResponse httpResponse) {
		this.response = new ThngResponse(httpResponse);
	}

	public ThngResponse getResponse() {
		return response;
	}
}
