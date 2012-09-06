package com.evrythng.api.wrapper;

import com.evrythng.api.wrapper.service.CollectionServiceImpl;
import com.evrythng.api.wrapper.service.CollectionService;
import com.evrythng.api.wrapper.service.ProductService;
import com.evrythng.api.wrapper.service.ThngService;
import com.evrythng.api.wrapper.service.ProductServiceImpl;
import com.evrythng.api.wrapper.service.ThngServiceImpl;

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
        this.thngService = new ThngServiceImpl(config);
        this.collectionService = new CollectionServiceImpl(config);
        this.productService = new ProductServiceImpl(config);
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
