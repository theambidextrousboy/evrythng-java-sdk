package net.evrythng.thng.api.wrapper.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThngAPIWrapperUtils {

	
	/**
	 * Prints all headers from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 *            the {@link HttpResponse} instance from which header values
	 *            will be retrieved
	 */
	public static void printResponseHeaders(HttpResponse response) {
		System.out.println(response.getStatusLine().toString());
		for(Header header : response.getAllHeaders()){
				System.out.println(header.getName() + ": " + header.getValue());	
		}
	}
	
	/**
	 * Prints all headers from the provided {@link HttpRequest}.
	 * 
	 * @param request
	 *            the {@link HttpRequest} instance from which header values
	 *            will be retrieved
	 */
	public static void printResquestHeaders(HttpRequest request) {
		for(Header header : request.getAllHeaders()){
				System.out.println(header.getName() + ": " + header.getValue());	
		}
	}
	
	/**
	 * Prints important headers from the provided {@link HttpRequest}.
	 * 
	 * @param request
	 *            the {@link HttpRequest} instance from which header values
	 *            will be retrieved
	 */
	public static void printResquestInfo(HttpRequest request) {
		System.out.println(request.getRequestLine());
		System.out.println("Content-Type: " + request.getFirstHeader("Content-Type").getValue());
		System.out.println("Status: " + request.getFirstHeader("Status").getValue());
	}
	
	/**
	 * Prints important headers from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 *            the {@link HttpResponse} instance from which header values
	 *            will be retrieved
	 */
	public static void printResponseInfo(HttpResponse response) {
		System.out.println(response.getStatusLine().toString());
		System.out.println("Content-Type: " + response.getFirstHeader("Content-Type").getValue());
		System.out.println("Status: " + response.getFirstHeader("Status").getValue());
	}
	
	/**
	 * Converts the content of the provided {@link HttpResponse} to an {@link JSONArray} instance.
	 * 
	 * @see #getContent(HttpResponse)
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONArray toJSONArray(HttpResponse response) throws ParseException, IOException, JSONException {
		System.out.println(">> Converting HttpResponse content to JSON array...");
		String content = getContent(response);
		return new JSONArray(content);
	}
	
	/**
	 * Converts the content of the provided {@link HttpResponse} to an {@link JSONObject} instance.
	 * 
	 * @see #getContent(HttpResponse)
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject toJSONObject(HttpResponse response) throws ParseException, IOException, JSONException {
		System.out.println(">> Converting HttpResponse content to JSON object...");
		String content = getContent(response);
		return new JSONObject(StringUtils.isNotBlank(content) ? content : "{'empty' : 'content'}"); // FIXME: return may not be JSON!
	}

	/**
	 * Retrieves content data from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private static String getContent(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		
		System.out.println("Length : " + content.length());
		System.out.println("CharSet: " + EntityUtils.getContentCharSet(entity));
		System.out.println("Data   : " + content);
		return content;
	}
}
