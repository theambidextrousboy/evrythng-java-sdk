package net.evrythng.thng.api.result;

import org.apache.http.HttpResponse;

public class ThngResult {

	private ThngResponse response;

	public ThngResult(HttpResponse httpResponse) {
		this.response = new ThngResponse(httpResponse);
	}

	public ThngResponse getResponse() {
		return response;
	}
}
