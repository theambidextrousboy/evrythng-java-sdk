package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Product;
import java.util.List;

/**
 *
 * @author tpham
 */
public class ProductServiceImpl extends EvrythngApiService implements IProductService  {
    
    public ProductServiceImpl(Configuration config){
        super(config);
    }

    public List<Product> getAllProductsSize() {
        throw new UnsupportedOperationException("Not supported yet.");
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
