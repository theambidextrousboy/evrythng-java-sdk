package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.util.List;

import org.apache.http.Header;

/**
 *
 * @author tpham
 */
public class CollectionService extends AbstractApiService {
    
    public CollectionService(Configuration config){
        super(config);
		if (config.isWrapperBehindAccessController()) {
			this.contextPath = config.getCollectionServiceContextPath();
		}
    }

    public int getCollectionsSize() {
    	URI uri = this.buildUri("/collections");
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
    }

    public List<Collection> getAllColletions() {
    	URI uri = this.buildUri("/collections");
    	return this.restTemplate.get(uri, new TypeReference<List<Collection>>() {});
    }

    public List<Collection> getAllCollections(int page) {
    	URI uri = this.buildUri("/collections", buildPagingParameters(page));
    	return this.restTemplate.get(uri, new TypeReference<List<Collection>>() {});
    }

    public List<Collection> getAllCollections(int page, int size) {
    	URI uri = this.buildUri("/collections", buildPagingParameters(page,size));
    	return this.restTemplate.get(uri, new TypeReference<List<Collection>>() {});
    }

    public Collection createCollection(Collection collection) {
    	URI uri = this.buildUri("/collections");
    	return this.restTemplate.post(uri, collection, new TypeReference<Collection>() {});
    }

    public void deleteAllCollections() {
    	URI uri = this.buildUri("/collections");
    	this.restTemplate.delete(uri);
    }

    public Collection getCollectionById(String collectionId) {
    	URI uri = this.buildUri("/collections/"+collectionId);
    	return this.restTemplate.get(uri,new TypeReference<Collection>() {});
    }

    public Collection updateCollectionById(String collectionId, Collection collection) {
    	URI uri = this.buildUri("/collections/"+collectionId);
    	return this.restTemplate.put(uri, collection, new TypeReference<Collection>() {});
    }

    public void deleteCollectionById(String collectionId) {
    	URI uri = this.buildUri("/collections/"+collectionId);
    	this.restTemplate.delete(uri);
    }

    public List<String> getThngIdsOfCollectionId(String collectionId) {
    	URI uri = this.buildUri("/collections/"+collectionId+"/thngs");
    	return this.restTemplate.get(uri,new TypeReference<List<String>>() {});
    }

    public List<String> updateThngIdsOfCollectionId(String collectionId, List<String> thngIds) {
    	URI uri = this.buildUri("/collections/"+collectionId+"/thngs");
    	return this.restTemplate.put(uri, thngIds, new TypeReference<List<String>>() {});
    }

    public void removeAllThngIdsOfCollectionId(String collectionId) {
    	URI uri = this.buildUri("/collections/"+collectionId+"/thngs");
    	this.restTemplate.delete(uri);
    }

    public void removeThngIdFromCollectionId(String collectionId, String thngId) {
    	URI uri = this.buildUri("/collections/"+collectionId+"/thngs/"+thngId);
    	this.restTemplate.delete(uri);
    }
    
}
