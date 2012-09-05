package com.evrythng.api.wrapper;

import com.evrythng.api.wrapper.service.CollectionServiceImpl;
import com.evrythng.api.wrapper.service.ICollectionService;
import com.evrythng.api.wrapper.service.IProductService;
import com.evrythng.api.wrapper.service.IThngService;
import com.evrythng.api.wrapper.service.ProductServiceImpl;
import com.evrythng.api.wrapper.service.ThngServiceImpl;

/**
 *
 * @author tpham
 */
public class ApiManager {

    /**
     * API key.
     */
    private String apiKeyValue;
    /**
     * Connection timeout (in milliseconds).
     */
    private Integer connectionTimeout;
    /**
     * Read timeout (in milliseconds).
     */
    private Integer readTimeout;
    
    private IThngService thngService;
    private ICollectionService collectionService;
    private IProductService productService;
    

    /**
     * Create a new manager instance.
     */
    public ApiManager(Configuration config) {  
        this.thngService = new ThngServiceImpl(config);
        this.collectionService = new CollectionServiceImpl(config);
        this.productService = new ProductServiceImpl(config);
        
    }

    /**
     * Set default API key.
     *
     * @param value API key value.
     *
     * @return Current instance for builder pattern.
     */
    public ApiManager setApiKey(String value) {
        this.apiKeyValue = value;
        return this;
    }

    /**
     * Set default connection timeout.
     *
     * @param connectionTimeout Timeout (in milliseconds).
     *
     * @return Current instance for builder pattern.
     */
    public ApiManager setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Set default read timeout.
     *
     * @param readTimeout Timeout (in milliseconds).
     *
     * @return Current instance for builder pattern.
     */
    public ApiManager setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public IThngService thngService() {
        return this.thngService;
    }
    
    public ICollectionService collectionService() {
        return this.collectionService;
    }
    
    public IProductService productService() {
        return this.productService;
    }

}
