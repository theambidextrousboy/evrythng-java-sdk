package net.evrythng.thng.api.result;

import java.io.IOException;

import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;

public class ThngArrayResult extends ThngContentResult<JSONArray> {

	/**
	 * Creates a new instance of {@link ThngArrayResult}.
	 * 
	 * @param response
	 * @throws IOException
	 */
	public ThngArrayResult(HttpResponse response) throws IOException {
		super(response, HttpComponentsUtils.toJSONArray(response));
	}

}
