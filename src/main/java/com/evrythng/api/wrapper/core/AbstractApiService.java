package com.evrythng.api.wrapper.core;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.util.URIBuilder;

/**
 * API service which facilitates provides helper
 * methods for performing remote method calls as well as deserializing the
 * corresponding JSON responses.
 * 
 * @author tpham
 */
public abstract class AbstractApiService {

	private static final Logger logger = LoggerFactory.getLogger(AbstractApiService.class);
	private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

	protected ApiConfiguration config;

	public AbstractApiService(ApiConfiguration config) {
		this.config = config;
	}

	protected URI absoluteUri(String relativePath) {
		if (!relativePath.startsWith("/")) {
			relativePath = String.format("/%s", relativePath);
		}
		return URIBuilder.fromUri(String.format("%s%s", config.getUrl(), relativePath)).build();
	}
}
