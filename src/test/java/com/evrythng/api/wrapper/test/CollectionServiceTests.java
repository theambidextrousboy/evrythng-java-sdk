package com.evrythng.api.wrapper.test;
import com.evrythng.api.wrapper.service.ICollectionService;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;
import org.junit.Test;


/**
 *
 * @author tpham
 */
public class CollectionServiceTests implements ICollectionService{

    @Test
    public int getCollectionsSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Collection> getAllColletions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Collection> getAllCollections(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Collection> getAllCollections(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Collection createCollection(Collection collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void deleteAllCollections() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Collection getCollectionById(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Collection updateCollectionById(String collectionId, Collection collection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void deleteCollectionById(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public List<Thng> getThngsOfCollectionId(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public Collection updateThngsOfCollectionId(String collectionId, List<String> thngIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void removeAllThngsOfCollectionId(String collectionId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Test
    public void removeThngFromCollectionId(String collectionId, String thngsId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
