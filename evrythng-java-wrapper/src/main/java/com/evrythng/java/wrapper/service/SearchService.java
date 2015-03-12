package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.core.api.param.LatQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.LonQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.MaxDistQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.QSearchQueryParamValue;
import com.evrythng.java.wrapper.core.api.param.TypesQueryParamValue;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.core.EvrythngType;
import com.evrythng.thng.resource.model.store.GlobalSearchResult;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.EnumSet;

/**
 * Service wrapper for the {@code /search} endpoints of the EVRYTHNG Engine API.
 *
 * @author Michel Yerly (my)
 */
public class SearchService extends EvrythngServiceBase {

	public static final String PATH_SEARCH = "/search";
	/**
	 * @deprecated since 1.16 - use {@link QSearchQueryParamValue} instead.
	 */
	@Deprecated
	public static final String QP_SEARCH_ALL = "q";
	/**
	 * @deprecated since 1.16 - use {@link TypesQueryParamValue} instead.
	 */
	@Deprecated
	public static final String QP_TYPES = "types";
	public static final String QP_IDENTIFIERS = "identifiers";
	public static final String QP_CUSTOM_FIELDS = "customFields";
	/**
	 * @deprecated since 1.16 - use
	 * {@link com.evrythng.java.wrapper.core.api.param.TagsQueryParamValue}
	 * instead.
	 */
	@Deprecated
	public static final String QP_TAGS = "tags";
	public static final String QP_PROPERTIES = "properties";
	/**
	 * @deprecated since 1.16 - use {@link LatQueryParamValue} instead
	 */
	@Deprecated
	public static final String QP_LATITUDE = "lat";
	/**
	 * @deprecated since 1.16 - use {@link LonQueryParamValue} instead
	 */
	@Deprecated
	public static final String QP_LONGITUDE = "lon";
	/**
	 * @deprecated since 1.16 - use {@link MaxDistQueryParamValue} instead
	 */
	@Deprecated
	public static final String QP_MAX_DISTANCE = "maxDist";

	public SearchService(final ApiManager apiManager) {

		super(apiManager);
	}

	/**
	 * Searches evrythng entities using a string search criterion.
	 *
	 * @param types      The entity types to search for.
	 * @param searchText The search criterion.
	 * @return The search results.
	 */
	public Builder<GlobalSearchResult> search(final EnumSet<EvrythngType> types, final String searchText) throws EvrythngClientException {

		return createBuilder(types).queryParam(QSearchQueryParamValue.pattern(searchText));
	}

	/**
	 * @param type       The entity type to search for.
	 * @param searchText The search criterion.
	 * @return The search results.
	 * @see #search(EnumSet, String)
	 */
	public Builder<GlobalSearchResult> search(final EvrythngType type, final String searchText) throws EvrythngClientException {

		return search(EnumSet.of(type), searchText);
	}

	/**
	 * @param type The entity type to search for.
	 * @return The search results.
	 * @see #search(EnumSet, String)
	 */
	public Builder<GlobalSearchResult> search(final EvrythngType type) throws EvrythngClientException {

		return search(EnumSet.of(type), "");
	}

	/**
	 * Searches evrythng entities using criteria for specific fields. Use query
	 * params to specify the fields.
	 *
	 * @param types The entity types to search for.
	 * @return The search results.
	 * @see #QP_CUSTOM_FIELDS
	 * @see #QP_PROPERTIES
	 * @see #QP_TAGS
	 * @see #qpCustomField(String)
	 * @see #qpProperty(String)
	 */
	public Builder<GlobalSearchResult> fieldSearch(final EnumSet<EvrythngType> types) throws EvrythngClientException {

		return createBuilder(types);
	}

	/**
	 * @param type The entity type to search for.
	 * @see #fieldSearch(EnumSet)
	 */
	public Builder<GlobalSearchResult> fieldSearch(final EvrythngType type) throws EvrythngClientException {

		return fieldSearch(EnumSet.of(type));
	}

	/**
	 * Performs a geo-location based search.
	 *
	 * @param types       The entity types to search for.
	 * @param latitude    The latitude in degrees.
	 * @param longitude   The longitude in degrees.
	 * @param maxDistance The maximal distance in kilometers to search.
	 * @return The search results.
	 */
	public Builder<GlobalSearchResult> geoSearch(final EnumSet<EvrythngType> types, final double latitude, final double longitude, final double maxDistance) throws EvrythngClientException {

		return createBuilder(types).queryParam(LatQueryParamValue.lat(latitude)).queryParam(LonQueryParamValue.lon(longitude)).queryParam(MaxDistQueryParamValue.maxDist(maxDistance));
	}

	/**
	 * @param type        The entity type to search for.
	 * @param latitude    The latitude in degrees.
	 * @param longitude   The longitude in degrees.
	 * @param maxDistance The maximal distance in kilometers to search.
	 * @return The search results.
	 * @see #geoSearch(EnumSet, double, double, double)
	 */
	public Builder<GlobalSearchResult> geoSearch(final EvrythngType type, final double latitude, final double longitude, final double maxDistance) throws EvrythngClientException {

		return geoSearch(EnumSet.of(type), latitude, longitude, maxDistance);
	}

	/**
	 * Creates the query parameter name for an identifier.
	 *
	 * @param identifierName The identifier name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpIdentifier(final String identifierName) {

		return QP_IDENTIFIERS + "." + identifierName;
	}

	/**
	 * Creates the query parameter name for a custom field.
	 *
	 * @param customFieldName The custom field name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpCustomField(final String customFieldName) {

		return QP_CUSTOM_FIELDS + "." + customFieldName;
	}

	/**
	 * Creates the query parameter name for a property name.
	 *
	 * @param propertyName The property name, for example "ean" or "upc".
	 * @return The query parameter name.
	 */
	public static String qpProperty(final String propertyName) {

		return QP_PROPERTIES + "." + propertyName;
	}

	private Builder<GlobalSearchResult> createBuilder(final EnumSet<EvrythngType> types) throws EvrythngClientException {

		StringBuilder sb = new StringBuilder();

		Builder<GlobalSearchResult> b = get(PATH_SEARCH, new TypeReference<GlobalSearchResult>() {

		});

		if (types.size() != EvrythngType.values().length) {
			for (EvrythngType type : types) {
				sb.append(type.getJsonValue()).append(',');
			}
			sb.delete(sb.length() - 1, sb.length());
			b = b.queryParam(TypesQueryParamValue.types(sb.toString()));
		}

		return b;
	}
}
