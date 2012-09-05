package com.evrythng.api.wrapper.test;

import com.evrythng.api.wrapper.ApiManager;
import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.service.IThngService;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tpham
 */
public class ThngServiceTests implements IThngService {
    
    private ApiManager apiManager;

    @Before
    public void init() {
        apiManager = new ApiManager(new Configuration());
        apiManager.setConnectionTimeout(30);
        apiManager.setReadTimeout(20);
        apiManager.setApiKey("");
        
    }

    @Ignore
    @Test
    public int getAllThngsSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Thng> getAllThngs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Thng> getAllThngs(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Thng> getAllThngs(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Thng createThng(Thng thng) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Thng getThngById(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Thng updateThngById(String thngId, Thng thng) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngById(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public int getThngPropertiesSize(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<String> getThngPropertyKeys(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Property> getThngProperties(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Property> getThngProperties(String thngId, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Property> getThngProperties(String thngId, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Property pushThngProperty(String thngId, Property prop) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Property> pushThngProperties(String thngId, List<Property> properties) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngProperties(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public int getThngPropertyValuesSize(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public PropertyValue getLatestThngPropertyValue(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValues(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValuesFrom(String thngId, String key, int page, int size, long from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValuesBetween(String thngId, String key, int page, int size, long from, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> getThngPropertyValuesTo(String thngId, String key, int page, int size, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public PropertyValue pushThngPropertyValueByKey(String thngId, String key, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public PropertyValue pushThngPropertyValueByKeyAt(String thngId, String key, String value, long timestamp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<PropertyValue> pushThngPropertyValuesByKey(String thngId, String key, List<String> values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteAllThngPropertyValuesByKey(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngPropertyValuesByKeyTo(String thngId, String key, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public int getThngLocationSize(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Location getLatestThngLocation(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocations(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocations(String thngId, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocations(String thngId, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocationsFrom(String thngId, int page, int size, long from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocationsBetween(String thngId, int page, int size, long from, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public List<Location> getThngLocationsTo(String thngId, int page, int size, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Location pushThngLocation(String thngId, long longitude, long latitude) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public Location pushThngLocationAt(String thngId, long longitude, long latitude, long timestamp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteAllThngLocation(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Ignore
    @Test
    public void deleteThngLocationTo(String thngId, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
