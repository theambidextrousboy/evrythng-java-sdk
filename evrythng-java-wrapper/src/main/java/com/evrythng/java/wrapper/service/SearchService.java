package com.evrythng.java.wrapper.service;

import java.util.EnumSet;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.core.EvrythngType;
import com.evrythng.thng.resource.model.store.GlobalSearchResult;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /search} endpoints of the EVRYTHNG Engine API.
 * 
 * @author Michel Yerly (my)
 */
public class SearchService extends EvrythngServiceBase {

	public static final String PATH_SEARCH = "/search";

	public static final String QP_SEARCH_ALL = "q";
	public static final String QP_TYPES = "types";

	public static final String QP_PRODUCT_IDENTIFIERS = "identifiers";
	public static final String QP_CUSTOM_FIELDS = "customFields";
	public static final String QP_TAGS = "tags";
	public static final String QP_PROPERTIES = "properties";
	public static final String QP_LATITUDE = "lat";
	public static final String QP_LONGITUDE = "lon";
	public static final String QP_MAX_DISTANCE = "maxDist";

	public SearchService(ApiConfiguration config) {
		super(config);
	}

	/**
	 * Searches evrythng entities using a string search criterion.
	 * 
	 * @param types
	 *            The entity types to search for.
	 * @param searchText
	 *            The search criterion.
	 * @return The search results.
	 */
	public Builder<GlobalSearchResult> search(EnumSet<EvrythngType> types, String searchText) throws EvrythngClientException {

		return createBuilder(types).queryParam(QP_SEARCH_ALL, searchText);
	}

	/**
	 * @see {@link #search(EnumSet, String)}
	 */
	public Builder<GlobalSearchResult> search(EvrythngType type, String searchText) throws EvrythngClientException {

		return search(EnumSet.of(type), searchText);
	}

	/**
	 * Searches evrythng entities using criteria for specific fields. Use query
	 * params to specify the fields.
	 * 
	 * @see #QP_CUSTOM_FIELDS
	 * @see #QP_PRODUCT_IDENTIFIERS
	 * @see #QP_PROPERTIES
	 * @see #QP_TAGS
	 * @see #qpCustomField(String)
	 * @see #qpProductIdentifier(String)
	 * @see #qpProperty(String)
	 * 
	 * @param types
	 *            The entity types to search for.
	 * @return The search results.
	 */
	public Builder<GlobalSearchResult> fieldSearch(EnumSet<EvrythngType> types) throws EvrythngClientException {

		return createBuilder(types);
	}

	/**
	 * @see {@link #fieldSearch(EnumSet)}
	 */
	public Builder<GlobalSearchResult> fieldSearch(EvrythngType type) throws EvrythngClientException {

		return fieldSearch(EnumSet.of(type));
	}

	/**
	 * Performs a geo-location based search.
	 * 
	 * @param types
	 *            The entity types to search for.
	 * @param latitude
	 *            The latitude in degrees.
	 * @param longitude
	 *            The longitude in degrees.
	 * @param maxDistance
	 *            The maximal distance in kilometers to search.
	 * @return The search results.
	 */
	public Builder<GlobalSearchResult> geoSearch(EnumSet<EvrythngType> types, double latitude, double longitude, double maxDistance) throws EvrythngClientException {

		return createBuilder(types).queryParam(QP_LATITUDE, String.valueOf(latitude)).queryParam(QP_LONGITUDE, String.valueOf(longitude)).queryParam(QP_MAX_DISTANCE, String.valueOf(maxDistance));
	}

	/**
	 * @see {@link #geoSearch(EnumSet, double, double, double)}
	 */
	public Builder<GlobalSearchResult> geoSearch(EvrythngType type, double latitude, double longitude, double maxDistance) throws EvrythngClientException {

		return geoSearch(EnumSet.of(type), latitude, longitude, maxDistance);
	}

	/**
	 * Creates the query parameter name for a product identifier.
	 * 
	 * @param identifierName
	 *            The identifier name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpProductIdentifier(String identifierName) {
		return QP_PRODUCT_IDENTIFIERS + "." + identifierName;
	}

	/**
	 * Creates the query parameter name for a custom field.
	 * 
	 * @param customFieldName
	 *            The custom field name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpCustomField(String customFieldName) {
		return QP_CUSTOM_FIELDS + "." + customFieldName;
	}

	/**
	 * Creates the query parameter name for a property name.
	 * 
	 * @param propertyName
	 *            The property name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpProperty(String propertyName) {
		return QP_PROPERTIES + "." + propertyName;
	}

	private Builder<GlobalSearchResult> createBuilder(EnumSet<EvrythngType> types) throws EvrythngClientException {

		StringBuilder sb = new StringBuilder();

		Builder<GlobalSearchResult> b = get(PATH_SEARCH, new TypeReference<GlobalSearchResult>() {
		});

		if (types.size() != EvrythngType.values().length) {
			for (EvrythngType type : types) {
				sb.append(type.getJsonValue()).append(',');
			}
			sb.delete(sb.length() - 1, sb.length());
			b = b.queryParam(QP_TYPES, sb.toString());
		}

		return b;
	}
}
