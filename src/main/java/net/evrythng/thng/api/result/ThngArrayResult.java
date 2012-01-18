package net.evrythng.thng.api.result;

import java.io.IOException;

import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;

public class ThngArrayResult extends ThngContentResult<JSONArray> {

	public ThngArrayResult(HttpResponse response) throws ParseException, IOException, JSONException {
		super(response, HttpComponentsUtils.toJSONArray(response));
	}

}
