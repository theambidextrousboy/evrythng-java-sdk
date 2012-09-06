package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.params.HttpParams;

/**
 *
 * @author tpham
 */
public class ThngServiceImpl extends EvrythngApiService implements ThngService {
    
    public ThngServiceImpl(Configuration config){
        super(config);
    }

    public int getAllThngsSize() {
    	Header xResultCountHeader  = this.getHeader("/thngs", null, "x-result-count");
    	return Integer.valueOf(xResultCountHeader.getValue()).intValue();
    }

    public List<Thng> getAllThngs() {
         return this.get("/thngs", null, new TypeReference<List<Thng>>() { });       
    }

    public List<Thng> getAllThngs(int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Thng> getAllThngs(int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Thng createThng(Thng thng) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Thng getThngById(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Thng updateThngById(String thngId, Thng thng) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteThngById(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getThngPropertiesSize(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getThngPropertyKeys(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Property> getThngProperties(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Property> getThngProperties(String thngId, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Property> getThngProperties(String thngId, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Property pushThngProperty(String thngId, Property prop) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Property> pushThngProperties(String thngId, List<Property> properties) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteThngProperties(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getThngPropertyValuesSize(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PropertyValue getLatestThngPropertyValue(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValues(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValuesFrom(String thngId, String key, int page, int size, long from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValuesBetween(String thngId, String key, int page, int size, long from, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> getThngPropertyValuesTo(String thngId, String key, int page, int size, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PropertyValue pushThngPropertyValueByKey(String thngId, String key, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PropertyValue pushThngPropertyValueByKeyAt(String thngId, String key, String value, long timestamp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<PropertyValue> pushThngPropertyValuesByKey(String thngId, String key, List<String> values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteAllThngPropertyValuesByKey(String thngId, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteThngPropertyValuesByKeyTo(String thngId, String key, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getThngLocationSize(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Location getLatestThngLocation(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocations(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocations(String thngId, int page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocations(String thngId, int page, int size) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocationsFrom(String thngId, int page, int size, long from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocationsBetween(String thngId, int page, int size, long from, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Location> getThngLocationsTo(String thngId, int page, int size, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Location pushThngLocation(String thngId, long longitude, long latitude) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Location pushThngLocationAt(String thngId, long longitude, long latitude, long timestamp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteAllThngLocation(String thngId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteThngLocationTo(String thngId, long to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
