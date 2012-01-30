/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.wrapper;

import java.util.ResourceBundle;

/**
 * 
 * @author almeidap
 * 
 */
public final class Configuration {
	/**
	 * The filename of the configuration properties file.
	 */
	private static final String BUNDLE_NAME = "evrythng-config";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/* Global */
	public static final String ENCODING = Configuration.getString("evrythng.api.configuration.encoding");

	/* Server */
	public static final String SERVER_SCHEME = Configuration.getString("evrythng.api.configuration.server.scheme");
	public static final String SERVER_HOST = Configuration.getString("evrythng.api.configuration.server.host");
	public static final int SERVER_PORT = Configuration.getInt("evrythng.api.configuration.server.port"); // -1 means no port is used

	/* HTTP Headers */
	public static final String HTTP_CONTENT_TYPE = Configuration.getString("evrythng.api.configuration.http.content.type");
	public static final String HTTP_ACCEPT = Configuration.getString("evrythng.api.configuration.http.accept");

	/* BASE PATHs */
	public static final String PATH_BASE = "/";

	/* Thngs PATHs */
	public static final String PATH_THNGS = "/thngs";
	public static final String PATH_THNG = PATH_THNGS + "/%s";
	public static final String PATH_PROPERTIES = PATH_THNG + "/properties";
	public static final String PATH_PROPERTY = PATH_PROPERTIES + "/%s";

	/* Collections PATHs */
	public static final String PATH_COLLECTIONS = "/collections";
	public static final String PATH_COLLECTION = PATH_COLLECTIONS + "/%s";
        public static final String PATH_COLLECTION_THNGS = PATH_COLLECTION + "/thngs";
        public static final String PATH_COLLECTION_THNG = PATH_COLLECTION_THNGS + "/%s";

	/* Search PATHs */
	public static final String PATH_SEARCH = "/search";

	private Configuration() {
		/* Hide default constructor */
	}

	protected static String getString(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}

	protected static int getInt(String key) {
		return Integer.valueOf(Configuration.getString(key));
	}
}
