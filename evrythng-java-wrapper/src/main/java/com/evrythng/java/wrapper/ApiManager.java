/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper;

import org.apache.commons.lang3.StringUtils;

import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.ProductService;
import com.evrythng.java.wrapper.service.SearchService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.commons.config.ApiConfiguration;

/**
 * Manager for the EVRYTHNG API.
 * 
 * @author Thomas Pham (tpham)
 * @author Pedro De Almeida (almeidap)
 */
public class ApiManager {

	private final ApiConfiguration config;

	private ThngService thngService;
	private CollectionService collectionService;
	private ProductService productService;
	private SearchService searchService;

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@link ApiConfiguration}.
	 */
	public ApiManager(ApiConfiguration config) {
		checkConfiguration(config);
		this.config = config;
		this.thngService = new ThngService(this.config);
		this.collectionService = new CollectionService(this.config);
		this.productService = new ProductService(this.config);
		this.searchService = new SearchService(this.config);
	}

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@code apiKey} for building an {@link ApiConfiguration} with default
	 * values.
	 * 
	 * @param apiKey
	 *            the API key for authorization
	 */
	public ApiManager(String apiKey) {
		this(new ApiConfiguration(apiKey));
	}

	/**
	 * Checks that the provided {@link ApiConfiguration} is valid.
	 * 
	 * @param apiConfiguration
	 *            the {@link ApiConfiguration} to be verified
	 */
	protected void checkConfiguration(ApiConfiguration apiConfiguration) {
		if (StringUtils.isBlank(apiConfiguration.getUrl())) {
			throw new IllegalStateException(String.format("URL of provided API configuration is invalid: [url=%s]", apiConfiguration.getUrl()));
		}

		if (StringUtils.isBlank(apiConfiguration.getKey())) {
			throw new IllegalStateException(String.format("API key of provided API configuration is invalid: [key=%s]", apiConfiguration.getKey()));
		}
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dev.evrythng.com/documentation/api#thngs">Thngs</a> API.
	 * 
	 * @see ThngService
	 */
	public ThngService thngService() {
		return this.thngService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dev.evrythng.com/documentation/api#collections">Collections
	 * </a> API.
	 * 
	 * @see CollectionService
	 */
	public CollectionService collectionService() {
		return this.collectionService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dev.evrythng.com/documentation/api#products">Products</a>
	 * API.
	 * 
	 * @see ProductService
	 */
	public ProductService productService() {
		return productService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dev.evrythng.com/documentation/api#search">Search</a>
	 * API.
	 * 
	 * @see SearchService
	 */
	public SearchService searchService() {
		return searchService;
	}

	public ApiConfiguration getConfig() {
		return config;
	}
}
