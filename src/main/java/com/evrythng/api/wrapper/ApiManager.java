package com.evrythng.api.wrapper;

import com.evrythng.api.wrapper.service.CollectionService;
import com.evrythng.api.wrapper.service.ProductService;
import com.evrythng.api.wrapper.service.ThngService;

/**
 * 
 * @author tpham
 */
public class ApiManager {

	private ThngService thngService;
	private CollectionService collectionService;
	private ProductService productService;

	/**
	 * Create a new manager instance.
	 */
	public ApiManager(Configuration config) {
		thngService = new ThngService(config);
		collectionService = new CollectionService(config);
		productService = new ProductService(config);
	}

	public ApiManager(String accessToken) {
		this(new Configuration(accessToken));
	}

	public ThngService thngService() {
		return this.thngService;
	}

	public CollectionService collectionService() {
		return this.collectionService;
	}

	public ProductService productService() {
		return this.productService;
	}

}
