/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.service.ApplicationService;
import com.evrythng.java.wrapper.service.AuthService;
import com.evrythng.java.wrapper.service.CollectionService;
import com.evrythng.java.wrapper.service.PlaceService;
import com.evrythng.java.wrapper.service.ProductService;
import com.evrythng.java.wrapper.service.ScanService;
import com.evrythng.java.wrapper.service.SearchService;
import com.evrythng.java.wrapper.service.ThngService;
import com.evrythng.thng.commons.config.ApiConfiguration;
import org.apache.commons.lang3.StringUtils;

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
	private ApplicationService applicationService;
	private AuthService authService;
	private ScanService scanThngService;
	private PlaceService placeService;

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@link ApiConfiguration}.
	 */
	public ApiManager(final ApiConfiguration config) {
		checkConfiguration(config);
		this.config = config;
		this.thngService = new ThngService(this);
		this.collectionService = new CollectionService(this);
		this.productService = new ProductService(this);
		this.searchService = new SearchService(this);
		this.applicationService = new ApplicationService(this);
		this.authService = new AuthService(this);
		this.scanThngService = new ScanService(this);
		this.placeService = new PlaceService(this);
	}

	/**
	 * Creates a new {@link ApiManager} instance using the provided
	 * {@code apiKey} for building an {@link ApiConfiguration} with default
	 * values.
	 * 
	 * @param apiKey
	 *            the API key for authorization
	 */
	public ApiManager(final String apiKey) {
		this(new ApiConfiguration(apiKey));
	}

	/**
	 * Checks that the provided {@link ApiConfiguration} is valid.
	 * 
	 * @param apiConfiguration
	 *            the {@link ApiConfiguration} to be verified
	 */
	protected void checkConfiguration(final ApiConfiguration apiConfiguration) {
		if (StringUtils.isBlank(apiConfiguration.getUrl())) {
			throw new IllegalStateException(String.format("URL of provided API configuration is invalid: [url=%s]", apiConfiguration.getUrl()));
		}

		if (StringUtils.isBlank(apiConfiguration.getKey())) {
			throw new IllegalStateException(String.format("API key of provided API configuration is invalid: [key=%s]", apiConfiguration.getKey()));
		}
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#thngs">Thngs</a> API.
	 * 
	 * @see ThngService
	 */
	public ThngService thngService() {
		return this.thngService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#collections">Collections
	 * </a> API.
	 * 
	 * @see CollectionService
	 */
	public CollectionService collectionService() {
		return this.collectionService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#products">Products</a>
	 * API.
	 * 
	 * @see ProductService
	 */
	public ProductService productService() {
		return productService;
	}

	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#search">Search</a>
	 * API.
	 * 
	 * @see SearchService
	 */
	public SearchService searchService() {
		return searchService;
	}

	/**
	 * Returns a preconfigures EVRYTHNG service for accessing the <a
	 * href="https://dashboard.evrythng.com/developers/apidoc#applications">
	 * Applications</a>
	 * API.
	 * 
	 * @see ApplicationService
	 */
	public ApplicationService applicationService() {
		return applicationService;
	}

	public AuthService authService() {
		return authService;
	}

	public ApiConfiguration getConfig() {
		return config;
	}
	
	/**
	 * Returns a preconfigured EVRYTHNG service for accessing the ScanThng API.
	 * 
	 * @see ScanService
	 */
	public ScanService scanThngService(){
		return scanThngService;
	}

	public void onBuilderCreated(final Builder<?> builder) {
	}

	public PlaceService placeService() {

		return placeService;
	}

}
