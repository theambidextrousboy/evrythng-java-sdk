package net.evrythng.thng.api.result;

import java.io.IOException;

import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;

public class ThngObjectResult extends ThngContentResult<JSONObject> {

	public ThngObjectResult(HttpResponse httpResponse) throws ParseException, IOException, JSONException {
		super(httpResponse, HttpComponentsUtils.toJSONObject(httpResponse));
	}

}
