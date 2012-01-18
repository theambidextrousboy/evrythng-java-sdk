package net.evrythng.thng.api.wrapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.evrythng.thng.api.model.Model;
import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.result.ThngArrayResult;
import net.evrythng.thng.api.result.ThngResult;
import net.evrythng.thng.api.search.GeoCode;
import net.evrythng.thng.api.search.SearchParameter;
import net.evrythng.thng.api.search.SearchParameter.Type;
import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.evrythng.thng.api.utils.JSONUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;

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
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper for the REST API of Evrythng.net.
 * 
 * @author <a href="mailto:pedro@evrythng.net">Pedro De Almeida</a>
 */
public class ThngAPIWrapper {

	private static final Logger logger = LoggerFactory.getLogger(ThngAPIWrapper.class);

	/**
	 * API token for HTTP authentication.
	 */
	private final String token;

	/**
	 * Client HTTP connection.
	 */
	private final HttpClient httpClient = new DefaultHttpClient();

	/**
	 * Creates a new instance of {@link ThngAPIWrapper} using the provided
	 * <code>token</code> for authentication.
	 * 
	 * {@link ThngAPIWrapper} uses values from the {@link Configuration} class
	 * which is initialized with the <code>config.properties</code> file.
	 * 
	 * @see Configuration
	 * @param token
	 *            the API token for HTTP authentication
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
		httpRequest.setHeader("Content-Type", Configuration.HTTP_CONTENT_TYPE);
		httpRequest.setHeader("Accept", Configuration.HTTP_ACCEPT);
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
		String query = queryParams != null ? URLEncodedUtils.format(queryParams, Configuration.ENCODING) : null;
		return URIUtils.createURI(Configuration.SERVER_SCHEME, Configuration.SERVER_HOST, Configuration.SERVER_PORT, path, query, null);
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
		logger.debug(">> Executing request: {}", request.getRequestLine());

		// Debug only:
		HttpComponentsUtils.printResquestHeaders(request); // FIXME: useful?

		// Add required headers:
		addHeaders(request);

		// Execute request:
		HttpResponse response = httpClient.execute(request);

		// Debug only:
		// ThngAPIWrapperUtils.printResponseInfo(response);
		HttpComponentsUtils.printResponseHeaders(response);

		return response;
	}

	/**
	 * Executes the given {@link HttpEntityEnclosingRequestBase}
	 * <code>request</code> after
	 * encapsulating the provided {@link JSON} <code>content</code>.
	 * 
	 * @see #execute(HttpRequestBase)
	 * @param request
	 * @param content
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse execute(HttpEntityEnclosingRequestBase request, JSON content) throws ClientProtocolException, IOException {
		logger.debug(">> Setting entity content: {}", content.toString());

		// Set entity content:
		request.setEntity(new StringEntity(content.toString()));

		// Delegate:
		return this.execute(request);
	}

	/**
	 * Executes a {@link HttpGet} request to the given <code>path</code>.
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse get(String path) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = this.createURI(path);
		HttpGet request = new HttpGet(uri);
		// Delegate:
		return this.execute(request);
	}

	/**
	 * Executes a {@link HttpGet} request to the given <code>path</code> with
	 * the provided <code>parameters</code> list.
	 * 
	 * @param path
	 * @param parameters
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private HttpResponse get(String path, List<NameValuePair> parameters) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = this.createURI(path, parameters);
		HttpGet request = new HttpGet(uri);
		// Delegate:
		return this.execute(request);
	}

	/**
	 * Executes a {@link HttpPost} request to the given <code>path</code> using
	 * the provided {@link JSON} content.
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse post(String path, JSON content) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = this.createURI(path);
		HttpPost request = new HttpPost(uri);
		// Delegate:
		return this.execute(request, content);
	}

	/**
	 * Executes a {@link HttpPut} request to the given <code>path</code> using
	 * the provided {@link JSON} content.
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse put(String path, JSON content) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = this.createURI(path);
		HttpPut request = new HttpPut(uri);
		// Delegate:
		return this.execute(request, content);
	}

	/**
	 * Executes a {@link HttpDelete} request to the given <code>path</code>.
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResponse delete(String path) throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = this.createURI(path);
		HttpDelete request = new HttpDelete(uri);
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
	private Collection<Thng> search(List<NameValuePair> parameters) throws URISyntaxException, ClientProtocolException, IOException {
		// Perform the GET request:
		HttpResponse response = this.get(Configuration.PATH_SEARCH, parameters);

		// Wrap response content into a collection of Thng:
		return JSONUtils.toCollection(HttpComponentsUtils.toJSONArray(response), Thng.class);
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
		// Perform the GET request:
		HttpResponse response = this.get(Configuration.PATH_BASE);

		// Check returned status code:
		// TODO: log some information (ping delay, time,etc...)
		return response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK;
	}

	/* *** THNGS *** */

	/**
	 * Gets the all accessible <code>thngs</code>.
	 * 
	 * @return The user <code>thng</code>'s collection as
	 *         {@link ThngArrayResult}.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws IllegalStateException
	 * @throws URISyntaxException
	 */
	public ThngArrayResult getThngs() throws URISyntaxException, ClientProtocolException, IOException {
		// Perform the GET request:
		HttpResponse response = this.get(Configuration.PATH_THNGS);

		// Convert HTTP response to an array result and return it:
		return new ThngArrayResult(response);
	}

	/**
	 * Creates a {@link Thng} using the provided <code>thng</code> instance.
	 * 
	 * @param thng
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Thng createThng(Model thng) throws URISyntaxException, ClientProtocolException, IOException, InstantiationException, IllegalAccessException {
		// Perform the POST request:
		HttpResponse response = this.post(Configuration.PATH_THNGS, thng.toJSONObject());

		// Wrap response into a Thng:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
	}

	/**
	 * Creates multiple {@link Thng}s using the provided <code>thngs</code>
	 * collection.
	 * 
	 * @param thng
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public ThngArrayResult createThngs(Model... thngs) throws URISyntaxException, ClientProtocolException, IOException {
		// Perform the POST request:
		HttpResponse response = this.post(Configuration.PATH_THNGS, JSONArray.fromObject(thngs, JSONUtils.getConfig()));

		// Wrap response into a ThngResult:
		return new ThngArrayResult(response);
	}

	/**
	 * Gets the {@link Thng} referenced by the provided <code>id</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted <code>thng</code>
	 * @return the requested {@link Thng} object.
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Thng getThng(String id) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
		// Perform the GET request:
		HttpResponse response = this.get(String.format(Configuration.PATH_THNG, id));

		// Wrap response into a Thng:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
	}

	/**
	 * Updates the {@link Thng} referenced by the provided <code>id</code>.
	 * 
	 * @param thng
	 *            the {@link Thng} to update
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Thng updateThng(Thng thng) throws URISyntaxException, ClientProtocolException, IOException, InstantiationException, IllegalAccessException {
		// Perform the PUT request:
		HttpResponse response = this.put(String.format(Configuration.PATH_THNG, thng.getId()), thng.toJSONObject());

		// Wrap response into a Thng:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
	}

	/**
	 * Deletes the {@link Thng} referenced by the provided <code>id</code>.
	 * 
	 * TODO: check return type: ThngResult or void?
	 * 
	 * @param id
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public ThngResult deleteThng(String id) throws URISyntaxException, ClientProtocolException, IOException {
		// Perform the DELETE request:
		HttpResponse response = this.delete(String.format(Configuration.PATH_THNG, id));

		// Consume entity content to avoid connection errors:
		EntityUtils.consume(response.getEntity());

		// Wrap response into a ThngResult:
		return new ThngResult(response);
	}

	/* *** PROPERTIES *** */

	/**
	 * Creates a new property on the {@link Thng} referenced by the given
	 * <code>id</code> using the provided {@link Property}.
	 * 
	 * @param id
	 * @param property
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <K> Property<K> createProperty(String id, Property<K> property) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
		// Perform the POST request:
		HttpResponse response = this.post(String.format(Configuration.PATH_PROPERTIES, id), property.toJSONObject());

		// Wrap response into a Property:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Property.class);
	}

	/**
	 * Creates new properties on the {@link Thng} referenced by the given
	 * <code>id</code> using the provided {@link Property} collection.
	 * 
	 * @param id
	 * @param property
	 * @return a {@link JSONArray} of {@link Property}'s
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	public JSONArray createProperties(String id, Property<?>... properties) throws ClientProtocolException, URISyntaxException, IOException {
		// Encapsulate properties:
		JSONArray content = new JSONArray();
		for (Property<?> item : properties) {
			content.add(item.toJSONObject());
		}

		// Perform the POST request:
		HttpResponse response = this.post(String.format(Configuration.PATH_PROPERTIES, id), content);

		// Convert response content to JSON and return it:
		return HttpComponentsUtils.toJSONArray(response);
	}

	/**
	 * Gets all properties of the {@link Thng} referenced by the provided
	 * <code>id</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted {@link Thng}
	 * @return a {@link JSONArray} of {@link Property}
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	public JSONArray getProperties(String id) throws ClientProtocolException, URISyntaxException, IOException {
		// Perform the GET request:
		HttpResponse response = this.get(String.format(Configuration.PATH_PROPERTIES, id));

		// Convert response content to JSON and return it:
		return HttpComponentsUtils.toJSONArray(response);
	}

	/**
	 * Gets the <code>key</code> property on the {@link Thng} referenced
	 * by the provided <code>id</code>.
	 * 
	 * @param id
	 * @param key
	 * @return the last value of {@link Property} key
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <K> Property<K> getProperty(String id, String key) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
		// Perform the GET request:
		HttpResponse response = this.get(String.format(Configuration.PATH_PROPERTY, id, key));

		// Wrap response into a Property:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Property.class); // FIXME: generic parameter is lost!
	}

	/**
	 * Deletes the <code>key</code> property on the {@link Thng} referenced by
	 * the given <code>id</code>.
	 * 
	 * @param id
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	public ThngResult deleteProperty(String id, String key) throws ClientProtocolException, URISyntaxException, IOException {
		// Perform the GET request:
		HttpResponse response = this.delete(String.format(Configuration.PATH_PROPERTY, id, key));

		// Consume entity content to avoid connection errors:
		EntityUtils.consume(response.getEntity());

		// Wrap response into a ThngResult:
		return new ThngResult(response);
	}

	/* *** COLLECTIONS *** */

	/**
	 * Creates a collection of <code>thngs</code> using the provided
	 * {@link CollectionObject}.
	 * 
	 * @param collection
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ThngCollection createCollection(ThngCollection collection) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
		// Perform the POST request:
		HttpResponse response = this.post(Configuration.PATH_COLLECTIONS, collection.toJSONObject());

		// Wrap response into a ThngCollection:
		// return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class); // TODO Uncomment this!

		// FIXME: change API to return a simple JSON Object instead of Array and remove this!
		JSONArray array = HttpComponentsUtils.toJSONArray(response);
		return this.getCollection(array.getString(0));
	}

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
	public Collection<ThngCollection> getCollections() throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Perform the GET request:
		HttpResponse response = this.get(Configuration.PATH_COLLECTIONS);

		// Wrap response content into a collection of ThngCollection:
		return JSONUtils.toCollection(HttpComponentsUtils.toJSONArray(response), ThngCollection.class);
	}

	/**
	 * Gets the collection of <code>thngs</code> referenced by the provided
	 * <code>id</code>.
	 * 
	 * @param id
	 *            the identifier referencing the targeted collection of
	 * @return the required ThngCollection.
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ThngCollection getCollection(String id) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
		// Perform the GET request:
		HttpResponse response = this.get(String.format(Configuration.PATH_COLLECTION, id));

		// Wrap response content into a Property:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class);
	}

	/**
	 * Updates the {@link Thng} referenced by the provided <code>id</code>.
	 * 
	 * @param collection
	 *            the {@link ThngCollection} to update
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ThngCollection updateCollection(ThngCollection collection) throws URISyntaxException, ClientProtocolException, IOException, InstantiationException, IllegalAccessException {
		// Perform the PUT request:
		HttpResponse response = this.put(String.format(Configuration.PATH_COLLECTION, collection.getId()), collection.toJSONObject());

		// Wrap response into a Thng:
		return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class);
	}

	/**
	 * Deletes the {@link ThngCollection} referenced by the provided
	 * <code>id</code>.
	 * 
	 * TODO: check return type: ThngResult or void?
	 * 
	 * @param id
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public ThngResult deleteCollection(String id) throws URISyntaxException, ClientProtocolException, IOException {
		// Perform the DELETE request:
		HttpResponse response = this.delete(String.format(Configuration.PATH_COLLECTION, id));

		// Consume entity content to avoid connection errors:
		EntityUtils.consume(response.getEntity());

		// Wrap response into a ThngResult:
		return new ThngResult(response);
	}

	/* *** SEARCH *** */

	/**
	 * Searches for all <code>thngs</code> within user space.
	 * 
	 * FIXME: unrestricted search should not be allowed!
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 */
	public Collection<Thng> search() throws ClientProtocolException, URISyntaxException, IOException {
		// Delegate:
		return this.search(new ArrayList<NameValuePair>());
	}

	/**
	 * Searches {@link Thng}s using provided <code>query</code> pattern.
	 * The text of the query will be searched into to the name and description
	 * fields (case-independant search) of all your thngs and all public thngs
	 * 
	 * <em>Note:</em> URL encoding will be performed on <code>query</code>.
	 * 
	 * @see #search(SearchParameter, String)
	 * @param query
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public Collection<Thng> search(String query) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Delegate:
		return this.search(SearchParameter.QUERY, query);
	}

	/**
	 * Searches {@link Thng}s using provided {@link SearchParameter}
	 * <code>parameter</code> and <code>value</code>.
	 * 
	 * <em>Note:</em> URL encoding will be performed on every parameter
	 * <code>value</code>.
	 * 
	 * @see #search(List)
	 * 
	 * @param query
	 * @param geoCode
	 * @param type
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public Collection<Thng> search(String query, GeoCode geoCode, Type type) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Build query parameters:
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(SearchParameter.QUERY.getName(), query));
		parameters.add(new BasicNameValuePair(SearchParameter.GEOCODE.getName(), geoCode.toString()));
		parameters.add(new BasicNameValuePair(SearchParameter.TYPE.getName(), type.toString()));

		// Delegate:
		return this.search(parameters);
	}

	/**
	 * Searches {@link Thng}s using provided {@link SearchParameter}
	 * <code>parameter</code> and <code>value</code>.
	 * 
	 * <em>Note:</em> URL encoding will be performed on provided parameter
	 * <code>value</code>.
	 * 
	 * @see #search(List)
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
	public Collection<Thng> search(SearchParameter parameter, String value) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
		// Build query parameters:
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(parameter.toString(), value));

		// Delegate:
		return this.search(parameters);
	}
}