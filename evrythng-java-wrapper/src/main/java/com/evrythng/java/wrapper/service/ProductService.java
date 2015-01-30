package com.evrythng.java.wrapper.service;

import com.evrythng.java.wrapper.ApiManager;
import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.AbstractProperty;
import com.evrythng.thng.resource.model.store.Product;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.Redirector;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collections;
import java.util.List;

/**
 * Service wrapper for the {@code /products} endpoint of the EVRYTHNG Engine API.
 */
public class ProductService extends EvrythngServiceBase {

	public static final String PATH_PRODUCTS = "/products";
	public static final String PATH_PRODUCT = PATH_PRODUCTS + "/%s";
	public static final String PATH_PRODUCT_PROPERTIES = PATH_PRODUCT + "/properties";
	public static final String PATH_PRODUCT_PROPERTY = PATH_PRODUCT_PROPERTIES + "/%s";
	public static final String PATH_PRODUCT_REDIRECTOR = PATH_PRODUCT + "/redirector";
	public static final String PATH_PRODUCT_REDIRECTOR_QR = PATH_PRODUCT_REDIRECTOR + "/qr";

	public ProductService(final ApiManager apiManager) {

		super(apiManager);
	}

	/* ***** /products ***** */

	/**
	 * POST {@value #PATH_PRODUCTS}
	 *
	 * @param product {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productCreator(final Product product) throws EvrythngClientException {

		return post(PATH_PRODUCTS, product, new TypeReference<Product>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCTS}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<Product>> productsReader() throws EvrythngClientException {

		return get(PATH_PRODUCTS, new TypeReference<List<Product>>() {

		});
	}

	/* ***** /products/{id} ***** */

	/**
	 * GET {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT, productId), new TypeReference<Product>() {

		});
	}

	/**
	 * Batch update a list of {@link Product}. Select the {Product}s to update using query parameters ?ids or ?filter.
	 * <p>
	 * PUT {@value #PATH_PRODUCTS}
	 *
	 * @param product {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> productsUpdater(final Product product) throws EvrythngClientException {

		return putMultiple(PATH_PRODUCTS, product);
	}

	/**
	 * PUT {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 * @param product   {@link Product} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Product> productUpdater(final String productId, final Product product) throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT, productId), product, new TypeReference<Product>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> productDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT, productId));
	}

	/**
	 * Bulk delete some products. Select the {Product}s to delete using query parameters ?ids or ?filter.
	 * <p>
	 * DELETE {@value #PATH_PRODUCTS}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Long> productsDeleter() throws EvrythngClientException {

		return deleteMultiple(PATH_PRODUCTS);
	}

	/* ***** /products/{id}/properties ***** */

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 * @param property  {@link Property} instance
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @see #propertiesCreator(String, List)
	 * @deprecated use {@link #abstractPropertiesCreator(String, java.util.List)} instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertiesCreator(final String productId, final Property property)
			throws EvrythngClientException {

		return propertiesCreator(productId, Collections.singletonList(property));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId  product id
	 * @param properties list of {@link Property}
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @deprecated use {@link #abstractPropertiesCreator(String, java.util.List)} instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertiesCreator(final String productId, final List<Property> properties)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTIES, productId), properties, new TypeReference<List<Property>>() {

		});
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId  product id
	 * @param properties list of {@link AbstractProperty}
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<AbstractProperty<?>>> abstractPropertiesCreator(final String productId,
	                                                                    final List<AbstractProperty<?>> properties)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTIES, productId), properties, new TypeReference<List<AbstractProperty<?>>>
				() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @deprecated use {@link #abstractPropertiesReader(String)} instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertiesReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTIES, productId), new TypeReference<List<Property>>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<AbstractProperty<?>>> abstractPropertiesReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTIES, productId), new TypeReference<List<AbstractProperty<?>>>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTIES}
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> propertiesDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_PROPERTIES, productId));
	}

	/* ***** /products/{id}/properties/{key} ***** */

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @deprecated use {@link #abstractPropertyReader(String, String)} instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertyReader(final String productId, final String key) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTY, productId, key), new TypeReference<List<Property>>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<AbstractProperty<?>>> abstractPropertyReader(final String productId, final String key)
			throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_PROPERTY, productId, key), new TypeReference<List<AbstractProperty<?>>>() {

		});
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 * @param value     value
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @see #propertiesCreator(String, Property)
	 * @deprecated use {@link #abstractPropertyUpdater(String, String, AbstractProperty)}  instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertyUpdater(final String productId, final String key, final String value)
			throws EvrythngClientException {

		return propertyUpdater(productId, key, new Property(null, value));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 * @param value     value
	 * @param timestamp timestamp
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @see #propertiesCreator(String, Property)
	 * @deprecated use {@link #abstractPropertyUpdater(String, String, AbstractProperty)}  instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertyUpdater(final String productId, final String key, final String value,
	                                               final long timestamp) throws EvrythngClientException {

		return propertyUpdater(productId, key, new Property(null, value, timestamp));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 * @param value     value
	 *
	 * @return a pre-configured {@link Builder}
	 *
	 * @deprecated use {@link #abstractPropertyUpdater(String, String, AbstractProperty)}  instead
	 */
	// TODO _MS_
	@Deprecated
	public Builder<List<Property>> propertyUpdater(final String productId, final String key, final Property value)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Collections.singletonList(value),
		           new TypeReference<List<Property>>() {

		           });
	}

	/**
	 * PUT {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 * @param update    property update
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<List<AbstractProperty<?>>> abstractPropertyUpdater(final String productId, final String key,
	                                                                  final AbstractProperty<?> update)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_PROPERTY, productId, key), Collections.singletonList(update),
		           new TypeReference<List<AbstractProperty<?>>>() {

		           });
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_PROPERTY}
	 *
	 * @param productId product id
	 * @param key       key
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> propertyDeleter(final String productId, final String key) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_PROPERTY, productId, key));
	}

	/* ***** /products/{id}/redirector ***** */

	/**
	 * POST {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Creates a redirector for the Product.
	 *
	 * @param productId   product id
	 * @param redirection {@link Redirector} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorCreator(final String productId, final Redirector redirection)
			throws EvrythngClientException {

		return post(String.format(PATH_PRODUCT_REDIRECTOR, productId), redirection, new TypeReference<Redirector>() {

		});
	}

	/**
	 * GET {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Retrieves the redirector for the Product.
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorReader(final String productId) throws EvrythngClientException {

		return get(String.format(PATH_PRODUCT_REDIRECTOR, productId), new TypeReference<Redirector>() {

		});
	}

	/**
	 * DELETE {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Deletes the redirector for the Product.
	 *
	 * @param productId product id
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Boolean> redirectorDeleter(final String productId) throws EvrythngClientException {

		return delete(String.format(PATH_PRODUCT_REDIRECTOR, productId));
	}

	/**
	 * PUT {@value #PATH_PRODUCT_REDIRECTOR}
	 * <p>
	 * Updates the redirector for the Product.
	 *
	 * @param productId   product id
	 * @param redirection {@link Redirector} instance
	 *
	 * @return a pre-configured {@link Builder}
	 */
	public Builder<Redirector> redirectorUpdater(final String productId, final Redirector redirection)
			throws EvrythngClientException {

		return put(String.format(PATH_PRODUCT_REDIRECTOR, productId), redirection, new TypeReference<Redirector>() {

		});
	}
}
