package net.evrythng.thng.api.result;

import org.apache.http.HttpResponse;

public class ThngResult {

	private final ThngResponse response;

	/**
	 * Creates a new instance of {@link ThngResult} using the provided
	 * {@link HttpResponse}.
	 * 
	 * @param httpResponse
	 */
	public ThngResult(HttpResponse httpResponse) {
		this.response = new ThngResponse(httpResponse);
	}

	public ThngResponse getResponse() {
		return response;
	}
}
