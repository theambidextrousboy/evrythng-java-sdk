package net.evrythng.thng.api.result;

import org.apache.http.HttpResponse;

public class ThngResponse {

	private final HttpResponse httpResponse;
	private int statusCode;

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
