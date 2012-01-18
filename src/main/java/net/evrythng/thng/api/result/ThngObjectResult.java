package net.evrythng.thng.api.result;

import java.io.IOException;

import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;

public class ThngObjectResult extends ThngContentResult<JSONObject> {

	/**
	 * Creates a new instance of {@link ThngObjectResult}.
	 * 
	 * @param httpResponse
	 * @throws IOException
	 */
	public ThngObjectResult(HttpResponse httpResponse) throws IOException {
		super(httpResponse, HttpComponentsUtils.toJSONObject(httpResponse));
	}

}
