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

	public Builder<GlobalSearchResult> search(EnumSet<EvrythngType> types, String searchText) throws EvrythngClientException {

		return createBuilder(types).queryParam(QP_SEARCH_ALL, searchText);
	}

	public Builder<GlobalSearchResult> search(EvrythngType type, String searchText) throws EvrythngClientException {

		return search(EnumSet.of(type), searchText);
	}

	public Builder<GlobalSearchResult> fieldSearch(EnumSet<EvrythngType> types) throws EvrythngClientException {

		return createBuilder(types);
	}

	public Builder<GlobalSearchResult> fieldSearch(EvrythngType type) throws EvrythngClientException {

		return fieldSearch(EnumSet.of(type));
	}

	public Builder<GlobalSearchResult> geoSearch(EnumSet<EvrythngType> types, double latitude, double longitude, double maxDistance) throws EvrythngClientException {

		return createBuilder(types).queryParam(QP_LATITUDE, String.valueOf(latitude)).queryParam(QP_LONGITUDE, String.valueOf(longitude)).queryParam(QP_MAX_DISTANCE, String.valueOf(maxDistance));
	}

	public Builder<GlobalSearchResult> geoSearch(EvrythngType type, double latitude, double longitude, double maxDistance) throws EvrythngClientException {

		return geoSearch(EnumSet.of(type), latitude, longitude, maxDistance);
	}

	public static String qpProductIdentifier(String identifierName) {
		return QP_PRODUCT_IDENTIFIERS + "." + identifierName;
	}

	public static String qpCustomField(String customFieldName) {
		return QP_CUSTOM_FIELDS + "." + customFieldName;
	}

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
