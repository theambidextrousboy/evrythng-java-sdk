package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.AbstractApiService;
import com.evrythng.api.wrapper.core.ApiBuilder;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.GlobalSearchResult;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * thng-store Search API wrapper.
 *
 * @author Victor Sergienko (victor)
 */
public class SearchService extends AbstractApiService {
	public static final String PATH_SEARCH_USER_ACTIONS = "/search";
	public static final String SEARCH_TYPES = "types";
	public static final String SEARCH_TYPE_ACTION_CHECKIN = "actionCheckin";
	public static final String SEARCH_USER = "user";

	public SearchService(ApiConfiguration config) {
		super(config);
	}

	// FIXME: use "<? extends ResourceModel>" - just no idea how to deserialize
	public ApiBuilder.Builder<GlobalSearchResult> searchReader(String userId) throws EvrythngClientException {
		return get(PATH_SEARCH_USER_ACTIONS, new TypeReference<GlobalSearchResult>() {})
				.queryParam(SEARCH_TYPES, SEARCH_TYPE_ACTION_CHECKIN)
				.queryParam(SEARCH_USER, userId);
	}

}
