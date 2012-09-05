package com.evrythng.api.wrapper.test;
import com.evrythng.api.wrapper.service.IProductService;
import com.evrythng.thng.resource.model.store.Product;
import java.util.List;
import org.junit.Test;


/**
 *
 * @author tpham
 */
public class ProductServiceTests implements IProductService{

    @Test
    public List<Product> getAllProductsSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Product> getAllProducts(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Product> getAllProducts(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Product createProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Product getProductById(String productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Product updateProductById(String productId, Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void deleteProductById(String producId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
