package com.evrythng.api.wrapper.service;

import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;

/**
 *
 * @author tpham
 */
public interface CollectionService {

    // /collections
    public int getCollectionsSize(); // +
    public List<Collection> getAllColletions();
    public List<Collection> getAllCollections(int page);
    public List<Collection> getAllCollections(int page, int size);
    public Collection createCollection(Collection collection);
    public void deleteAllCollections();
    
    // /collections/{collectionId}
    public Collection getCollectionById(String collectionId);
    public Collection updateCollectionById(String collectionId, Collection collection);
    public void deleteCollectionById(String collectionId);
    
    // /collections/{collectionId}/thngs
    public List<Thng> getThngsOfCollectionId(String collectionId);
    public Collection updateThngsOfCollectionId(String collectionId, List<String> thngIds);
    public void removeAllThngsOfCollectionId(String collectionId);
    
    // /collections/{collectionId}/thngs/{thngId}
    public void removeThngFromCollectionId(String collectionId, String thngsId);
    
}
