package net.evrythng.thng.api.wrapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.evrythng.thng.api.element.CollectionObject;
import net.evrythng.thng.api.element.ThngObject;
import net.evrythng.thng.api.element.ThngProperty;
import net.evrythng.thng.api.wrapper.utils.ThngAPIWrapperUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Wrapper for the REST API of Evrythng.net.
 * 
 * @author <a href="mailto:pedro@evrythng.net">Pedro De Almeida</a>
 */
public class ThngAPIWrapper {

	public static final String ENCODING = "UTF-8";
	public static final String CONTENT_TYPE = "application/json";
	public static final String ACCEPT_JSON = "application/vnd.evrythng-v1+json";

	private static final String SERVER_SCHEME = "https";
	private static final String SERVER_HOST = "evrythng.net";
	private static final int SERVER_PORT = -1; // -1 means no port is used

	/* BASE PATHs */
	private static final String PATH_BASE = "/";

	/* Thngs PATHs */
	private static final String PATH_THNGS = "/thngs";
	private static final String PATH_THNG = PATH_THNGS + "/%s";
	private static final String PATH_PROPERTIES = PATH_THNG + "/properties";
	private static final String PATH_PROPERTY = PATH_PROPERTIES + "/%s";

	/* Collections PATHs */
	private static final String PATH_COLLECTIONS = "/collections";
	private static final String PATH_COLLECTION = PATH_COLLECTIONS + "/%s";

	/* Search PATHs */
	private static final String PATH_SEARCH = "/search";

	/**
	 * API token for HTTP authentication.
	 */
	private String token;

	/**
	 * Client HTTP connection.
	 */
	private HttpClient httpClient = new DefaultHttpClient();

	/**
	 * Creates a new instance of {@link ThngAPIWrapper} using the provided
	 * <code>token</code> for authentication.
	 * 
	 * @param token
	 */
	public ThngAPIWrapper(String token) {
		this.token = token;
	}

	/**
	 * Adds required headers to the provided {@link HttpRequestBase} object.
	 * 
	 * @param httpRequest
	 *            the request object to which headers will be added
	 */
	private void addHeaders(HttpRequestBase httpRequest) {
		httpRequest.setHeader("Content-Type", CONTENT_TYPE);
		httpRequest.setHeader("Accept", ACCEPT_JSON);
		httpRequest.setHeader("X-Evrythng-Token", this.token);
	}

	/**
	 * Creates an URI for accessing a specific <code>path</code> from Evrythng's
	 * API.
	 * 
	 * @see #createURI(String, List)
	 * @param path
	 * @return
	 * @throws URISyntaxException
	 */
	private URI createURI(String path) throws URISyntaxException {
		// Delegate:
		return this.createURI(path, null);
	}

	/**
	 * Creates an URI using the provided query <code>parameters</code> for
	 * accessing a specific <code>path</code> from Evrythng's API.
	 * 
	 * @param path
	 * @param queryParams
	 * @return
	 * @throws URISyntaxException
	 */
	private URI createURI(String path, List<NameValuePair> queryParams) throws URISyntaxException {
		String query = queryParams != null ? URLEncodedUtils.format(queryParams, ENCODING) : null;
		return URIUtils.createURI(SERVER_SCHEME, SERVER_HOST, SERVER_PORT, path, query, null);
	}

	/**
	 * Executes the given {@link HttpRequestBase} request. This method also adds
	 * the required headers for accessing the <a
	 * href="http://evrythng.net">Evrythng.net</a> API.
	 * 
	 * @see #addHeaders(HttpRequestBase)
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse execute(HttpRequestBase request) throws IOException, ClientProtocolException {
		System.out.println(">> Executing request: " + request.getRequestLine());

		// Debug only:
		ThngAPIWrapperUtils.printResquestHeaders(request); // FIXME: useful?

		// Add required headers:
		addHeaders(request);

		// Eecute request:
		HttpResponse response = httpClient.execute(request);

		// Debug only:
		//ThngAPIWrapperUtils.printResponseInfo(response);
		ThngAPIWrapperUtils.printResponseHeaders(response);

		return response;
	}

	/**
	 * Executes the given {@link HttpEntityEnclosingRequestBase} request after
	 * encapsulating the provided <code>content</code>.
	 * 
	 * @see #execute(HttpRequestBase)
	 * @param request
	 * @param content
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse execute(HttpEntityEnclosingRequestBase request, JSONObject content) throws IOException, ClientProtocolException {
		System.out.println(">> Setting entity content: " + content.toString());

		// Set entity content:
		request.setEntity(new StringEntity(content.toString()));

		// Delegate:
		return this.execute(request);
	}

	/**
	 * Search elements using the provided <code>parameters</code> collection.
	 * 
	 * @param parameters
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	private JSONArray search(List<NameValuePair> parameters) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {

		// Create and execute request:
		URI uri = this.createURI(PATH_SEARCH, parameters);
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/* ***** PUBLIC METHODS ***** */

	/* *** GLOBAL *** */

	/**
	 * Checks connectivity to the Evrythng.net API.
	 * 
	 * @return <code>true</code> if connection succeeds (i.e. returns HTTP code
	 *         200), <code>false</code> otherwise
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * 
	 */
	public boolean ping() throws ClientProtocolException, IOException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_BASE);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = this.execute(httpGet);

		return response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK;
	}

	/* *** THNGS *** */

	/**
	 * Gets the all accessible <code>thngs</code>.
	 * 
	 * @return The user <code>thng</code>'s collection as {@link JSONObject}.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws IllegalStateException
	 * @throws URISyntaxException
	 */
	public JSONArray getAllThngs() throws ClientProtocolException, IOException, IllegalStateException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_THNGS);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = this.execute(httpGet);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/**
	 * Creates a <code>thng</code> using the provided <code>id</code>,
	 * <code>description</code> and <code>isPublic</code> parameter values.
	 * 
	 * @see #createThng(ThngObject)
	 * @param id
	 * @param description
	 * @param isPublic
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParseException
	 */
	public JSONObject createThng(String id, String description, Boolean isPublic) throws JSONException, ClientProtocolException, IOException, ParseException, URISyntaxException {
		// Prepare the 'thng' object:
		ThngObject thng = new ThngObject(id, description, isPublic);

		// Delegate:
		return this.createThng(thng);
	}

	/**
	 * Creates a <code>thng</code> using the provided {@link ThngObject}.
	 * 
	 * @param thng
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public JSONObject createThng(ThngObject thng) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_THNGS);
		HttpPost request = new HttpPost(uri);
		HttpResponse response = this.execute(request, thng.toJSONObject());

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/**
	 * Gets the <code>thng</code> referenced by the provided
	 * <code>identifier</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted <code>thng</code>
	 * @return The thng as JSON.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public JSONObject getThng(String id) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_THNG, id));
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/**
	 * Creates a new property on the <code>thng</code> referenced by the
	 * given <code>identifier</code> using the provided <code>title</code> and
	 * <code>text</code>.
	 * 
	 * @see #createThngProperty(String, ThngProperty)
	 * @param id
	 * @param title
	 *            the title attribute of the property
	 * @param text
	 *            the text attribute of the property
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public JSONObject createThngProperty(String id, String title, Object text) throws JSONException, ClientProtocolException, IOException, URISyntaxException {
		// Delegate:
		return this.createThngProperty(id, new ThngProperty<Object>(title, text));
	}

	/**
	 * Creates a new property on the <code>thng</code> referenced by the
	 * given <code>identifier</code> using the provided {@link ThngProperty}.
	 * 
	 * @param id
	 * @param property
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public JSONObject createThngProperty(String id, ThngProperty<?> property) throws JSONException, ClientProtocolException, IOException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_PROPERTIES, id));
		HttpPost request = new HttpPost(uri);
		HttpResponse response = this.execute(request, property.toJSONObject());

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/**
	 * Deletes the <code>title</code> property on the <code>thng</code>
	 * referenced by the
	 * given <code>identifier</code>.
	 * 
	 * @param id
	 * @param title
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public JSONObject deleteThngProperty(String id, String title) throws JSONException, ClientProtocolException, IOException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_PROPERTY, id, title));
		HttpDelete request = new HttpDelete(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/* *** PROPERTIES *** */

	/**
	 * Creates new properties on the <code>thng</code> referenced by the
	 * given <code>identifier</code> using the provided {@link ThngProperty}
	 * collection.
	 * 
	 * @param id
	 * @param property
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public JSONArray createThngProperties(String id, ThngProperty<?>... properties) throws JSONException, ClientProtocolException, IOException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_PROPERTIES, id));
		HttpPost request = new HttpPost(uri);

		// Encapsulate properties:
		JSONObject content = new JSONObject();
		JSONArray array = new JSONArray();
		content.put("properties", array);

		for (ThngProperty<?> item : properties) {
			array.put(item.toJSONObject());
		}

		HttpResponse response = this.execute(request, content);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/**
	 * Gets all properties on the <code>thng</code> referenced by the provided
	 * <code>id</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted <code>thng</code>
	 * @return The thng as JSON.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public JSONArray getThngProperties(String id) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_PROPERTIES, id));
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/**
	 * Gets the <code>title</code> property on the <code>thng</code> referenced
	 * by the provided <code>id</code>.
	 * 
	 * @param id
	 * @param title
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public JSONObject getThngProperty(String id, String title) throws ParseException, IOException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_PROPERTY, id, title));
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/* *** COLLECTIONS *** */

	/**
	 * Gets all available collections.
	 * 
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public JSONArray getAllCollections() throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_COLLECTIONS);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = this.execute(httpGet);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/**
	 * Gets the collection of <code>thngs</code> referenced by the provided
	 * <code>identifier</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted collection of
	 *            <code>thngs</code>
	 * @return The thng as JSON.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public JSONObject getCollection(String id) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_COLLECTION, id));
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/**
	 * Creates a collection of <code>thngs</code> using the provided
	 * <code>id</code>, <code>description</code> and <code>isPublic</code>
	 * parameter values.
	 * 
	 * @see #createCollection(CollectionObject)
	 * @param id
	 * @param description
	 * @param isPublic
	 * @return
	 * @throws JSONException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ParseException
	 */
	public JSONObject createCollection(String id, String description, Boolean isPublic) throws JSONException, ClientProtocolException, IOException, ParseException, URISyntaxException {

		// Prepare the 'collection' object:
		CollectionObject collection = new CollectionObject(id, description, isPublic);

		// Delegate:
		return this.createCollection(collection);
	}

	/**
	 * Creates a collection of <code>thngs</code> using the provided
	 * {@link CollectionObject}.
	 * 
	 * @param collection
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public JSONObject createCollection(CollectionObject collection) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_COLLECTIONS);
		HttpPost request = new HttpPost(uri);
		HttpResponse response = this.execute(request, collection.toJSONObject());

		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONObject(response);
	}

	/**
	 * Edits the collection of <code>thngs</code> referenced by the provided
	 * <code>id</code> with the new values from the {@link CollectionObject}
	 * instance.
	 * 
	 * @param id
	 * @param collection
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public Boolean editCollection(String id, CollectionObject collection) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_COLLECTION, id));
		HttpPut request = new HttpPut(uri);
		HttpResponse response = this.execute(request, collection.toJSONObject());

		// FIXME: should collection edition return something?
		// Convert response content to JSON and return it:
		// return ThngAPIWrapperUtils.toJSONObject(response);
		return response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK;
	}

	/**
	 * Deletes the collection of <code>thngs</code> referenced by the provided
	 * <code>id</code>.
	 * 
	 * @param id
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public Boolean deleteCollection(String id) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(String.format(PATH_COLLECTION, id));
		HttpDelete request = new HttpDelete(uri);
		HttpResponse response = this.execute(request);

		// FIXME: should collection delete return something?
		// Convert response content to JSON and return it:
		// return ThngAPIWrapperUtils.toJSONObject(response);
		return response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK;
	}

	/* *** SEARCH *** */

	/**
	 * Searches for all <code>thngs</code> within user space.
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public JSONArray search() throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Create and execute request:
		URI uri = this.createURI(PATH_SEARCH);
		HttpGet request = new HttpGet(uri);
		HttpResponse response = this.execute(request);

		// FIXME: should collection delete return something?
		// Convert response content to JSON and return it:
		return ThngAPIWrapperUtils.toJSONArray(response);
	}

	/**
	 * Searches <code>thngs</code> using provided <code>query</code> pattern.
	 * 
	 * <em>Note:</em> URL encoding will be performed on <code>query</code>.
	 * 
	 * @see #search(SearchParameterEnum, String)
	 * @param query
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public JSONArray search(String query) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Delegate:
		return this.search(SearchParameterEnum.QUERY, query);
	}

	/**
	 * Searches <code>thngs</code> using provided {@link SearchParameterEnum}
	 * <code>parameter</code> and <code>value</code>.
	 * 
	 * <em>Note:</em> URL encoding will be performed on every parameter <code>value</code>.
	 * 
	 * @param parameter
	 * @param value
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public JSONArray search(SearchParameterEnum parameter, String value) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {

		// Build query parameters:
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(parameter.toString(), value));

		// Delegate:
		return this.search(parameters);
	}
}