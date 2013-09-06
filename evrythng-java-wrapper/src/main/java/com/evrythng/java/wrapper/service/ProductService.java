package com.evrythng.java.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Product;
import com.evrythng.thng.resource.model.store.Property;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * TODO: comment this class!
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ProductService extends EvrythngServiceBase {

	public static final String PATH_PRODUCTS = "/products";
	public static final String PATH_PRODUCT = PATH_PRODUCTS + "/%s";
	public static final String PATH_PRODUCT_PROPERTIES = PATH_PRODUCT + "/properties";
	public static final String PATH_PRODUCT_PROPERTY = PATH_PRODUCT_PROPERTIES + "/%s";

	public ProductService(ApiManager apiManager) {
		super(apiManager);
	}

	/* ***** /products ***** */

	/**
	 * POST {@value #PATH_PRODUCTS}
	 * 
	 * @param product
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Product> productCreator(Product product) throws EvrythngClientException {
		return post(PATH_PRODUCTS, product, new TypeReference<Product>() {
		});
	}

	/**
	 * GET {@value #PATH_PRODUCTS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Product>> productsReader() throws EvrythngClientException {
		return get(PATH_PRODUCTS, new TypeReference<List<Product>>() {
		});
	}

	/* ***** /products/{id} ***** */

	/**
	 * GET {@value #PATH_PRODUCT}
	 * 
	 * @param productId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Product> productReader(String productId) throws EvrythngClientException {
		return get(String.format(PATH_PRODUCT, productId), new TypeReference<Product>() {
		});
	}

	/**
	 * PUT {@value #PATH_PRODUCT}
	 * 
	 * @param productId
	 * @param product
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Product> productUpdater(String productId, Product product) throws EvrythngClientException {
		return put(String.format(PATH_PRODUCT, productId), product, new TypeReference<Product>() {
		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT}
	 * 
	 * @param productId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> productDeleter(String productId) throws EvrythngClientException {
		return delete(String.format(PATH_PRODUCT, productId));
	}

	/* ***** /products/{id}/properties ***** */

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 * 
	 * @see #productsCreator(List)
	 * @param productId
	 * @param property
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String productId, Property property) throws EvrythngClientException {
		return propertiesCreator(productId, Arrays.asList(property));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 * 
	 * @param productId
	 * @param properties
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String productId, List<Property> properties) throws EvrythngClientException {
		return put(String.format(PATH_PRODUCT_PROPERTIES, productId), properties, new TypeReference<List<Property>>() {
		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTIES}
	 * 
	 * @param productId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesReader(String productId) throws EvrythngClientException {
		return get(String.format(PATH_PRODUCT_PROPERTIES, productId), new TypeReference<List<Property>>() {
		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTIES}
	 * 
	 * @param productId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertiesDeleter(String productId) throws EvrythngClientException {
		return delete(String.format(PATH_PRODUCT_PROPERTIES, productId));
	}

	/* ***** /products/{id}/properties/{key} ***** */

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTY}
	 * 
	 * @param productId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertyReader(String productId, String key) throws EvrythngClientException {
		return get(String.format(PATH_PRODUCT_PROPERTY, productId, key), new TypeReference<List<Property>>() {
		});
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param productId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertyUpdater(String productId, String key, String value) throws EvrythngClientException {
		return propertyUpdater(productId, key, new Property(null, value));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param productId
	 * @param key
	 * @param value
	 * @param timestamp
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertyUpdater(String productId, String key, String value, long timestamp) throws EvrythngClientException {
		return propertyUpdater(productId, key, new Property(null, value, timestamp));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 * 
	 * @param productId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertyUpdater(String productId, String key, Property value) throws EvrythngClientException {
		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Arrays.asList(value), new TypeReference<List<Property>>() {
		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTY}
	 * 
	 * @param productId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertyDeleter(String productId, String key) throws EvrythngClientException {
		return delete(String.format(PATH_PRODUCT_PROPERTY, productId, key));
	}
}
