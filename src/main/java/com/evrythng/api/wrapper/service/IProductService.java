/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evrythng.api.wrapper.service;

import com.evrythng.thng.resource.model.store.Product;
import java.util.List;

/**
 *
 * @author tpham
 */
public interface IProductService {

    
    // products
    public List<Product> getAllProductsSize(); // +
    public List<Product> getAllProducts();
    public List<Product> getAllProducts(int page);
    public List<Product> getAllProducts(int page, int size);
    public Product createProduct(Product product);
    // public Product searchProductByFN(String fn);
    // public List<Product> searchProductsByFN(String fn);
    
    // /products/{productId}
    public Product getProductById(String productId);
    public Product updateProductById(String productId, Product product);
    public void deleteProductById(String producId);
    
}
