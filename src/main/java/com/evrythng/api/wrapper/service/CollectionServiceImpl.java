package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;

/**
 *
 * @author tpham
 */
public class CollectionServiceImpl extends EvrythngApiService implements ICollectionService {
    
    public CollectionServiceImpl(Configuration config){
        super(config);
    }

    public int getCollectionsSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Collection> getAllColletions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Collection> getAllCollections(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Collection> getAllCollections(int page, int size) {
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection createCollection(Collection collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteAllCollections() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection getCollectionById(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection updateCollectionById(String collectionId, Collection collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteCollectionById(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Thng> getThngsOfCollectionId(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection updateThngsOfCollectionId(String collectionId, List<String> thngIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeAllThngsOfCollectionId(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeThngFromCollectionId(String collectionId, String thngsId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
