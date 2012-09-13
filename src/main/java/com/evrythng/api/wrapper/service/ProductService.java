package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Product;

import java.net.URI;
import java.util.List;

import org.apache.http.Header;

/**
 *
 * @author tpham
 */
public class ProductService extends AbstractApiService   {
    
    public ProductService(Configuration config){
        super(config);
        if (config.getProductServiceContextPath() != null && !config.getProductServiceContextPath().isEmpty()) {
			this.contextPath = config.getProductServiceContextPath();
		}
    }

    public int getAllProductsSize() {
    	URI uri = this.buildUri("/products");
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
    }

    public List<Product> getAllProducts() {
    	throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Product> getAllProducts(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Product> getAllProducts(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Product createProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Product getProductById(String productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Product updateProductById(String productId, Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteProductById(String producId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
