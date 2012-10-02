package com.evrythng.api.wrapper;

import com.evrythng.api.wrapper.service.CollectionService;
import com.evrythng.api.wrapper.service.ThngService;

/**
 * 
 * @author tpham
 */
public class ApiManager {

	private final ApiConfiguration config;

	private ThngService thngService;
	private CollectionService collectionService;

	/**
	 * Create a new manager instance.
	 */
	public ApiManager(ApiConfiguration config) {
		this.config = config;
		thngService = new ThngService(config);
		collectionService = new CollectionService(config);
	}

	public ApiManager(String accessToken) {
		this(new ApiConfiguration(accessToken));
	}

	public ThngService thngService() {
		return this.thngService;
	}

	public CollectionService collectionService() {
		return this.collectionService;
	}

	public ApiConfiguration getConfig() {
		return config;
	}
}
