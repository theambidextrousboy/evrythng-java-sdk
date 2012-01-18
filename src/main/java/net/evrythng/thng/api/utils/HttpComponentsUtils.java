package net.evrythng.thng.api.utils;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpComponentsUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpComponentsUtils.class);

	private HttpComponentsUtils() {
		/* Hide default constructor */
	}

	/**
	 * Prints all headers from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 *            the {@link HttpResponse} instance from which header values
	 *            will be retrieved
	 */
	public static void printResponseHeaders(HttpResponse response) {
		logger.info(">> Response headers:");
		logger.info("StatusLine: {}", response.getStatusLine());
		for (Header header : response.getAllHeaders()) {
			printHeader(header);
		}
	}

	/**
	 * Prints all headers from the provided {@link HttpRequest}.
	 * 
	 * @param request
	 *            the {@link HttpRequest} instance from which header values will
	 *            be retrieved
	 */
	public static void printResquestHeaders(HttpRequest request) {
		for (Header header : request.getAllHeaders()) {
			printHeader(header);
		}
	}

	/**
	 * Prints name and value of the provided {@link Header}.
	 * 
	 * @param header
	 */
	private static void printHeader(Header header) {
		logger.info("{}: {}", header.getName(), header.getValue());
	}

	/**
	 * Prints important headers from the provided {@link HttpRequest}.
	 * 
	 * @param request
	 *            the {@link HttpRequest} instance from which header values will
	 *            be retrieved
	 */
	public static void printResquestInfo(HttpRequest request) {
		logger.info("Request: {}", request.getRequestLine());

	}

	/**
	 * Prints important headers from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 *            the {@link HttpResponse} instance from which header values
	 *            will be retrieved
	 */
	public static void printResponseInfo(HttpResponse response) {
		logger.info("StatusLine: {}", response.getStatusLine());
		printMessageInfo(response);
	}

	/**
	 * Prints important headers from the provided {@link HttpMessage}.
	 * 
	 * @param response
	 *            the {@link HttpMessage} instance from which header values
	 *            will be retrieved
	 */
	public static void printMessageInfo(HttpMessage message) {
		logger.info("Content-Type: {}", message.getFirstHeader("Content-Type").getValue());
		logger.info("Status: {}", message.getFirstHeader("Status").getValue());
	}

	/**
	 * Converts the content of the provided {@link HttpResponse} to an
	 * {@link JSONArray} instance.
	 * 
	 * @see #getContent(HttpResponse)
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static JSONArray toJSONArray(HttpResponse response) throws IOException {
		logger.debug(">> Converting HttpResponse content to JSON array...");
		String content = getContent(response);
		return JSONUtils.toJSONArray(content);
	}

	/**
	 * Converts the content of the provided {@link HttpResponse} to an
	 * {@link JSONObject} instance.
	 * 
	 * @see #getContent(HttpResponse)
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static JSONObject toJSONObject(HttpResponse response) throws IOException {
		logger.debug(">> Converting HttpResponse content to JSON object...");
		String content = getContent(response);
		return JSONUtils.toJSONObject(StringUtils.isNotBlank(content) ? content : "{}"); // TODO: check this
	}

	/**
	 * Retrieves content data from the provided {@link HttpResponse}.
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static String getContent(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity);

		logger.debug("Length : {}", content.length());
		logger.debug("CharSet: {}", EntityUtils.getContentCharSet(entity));
		logger.debug("Data   : {}", content);
		return content;
	}
}
