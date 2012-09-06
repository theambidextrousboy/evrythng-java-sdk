package com.evrythng.api.wrapper.service;

import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import java.util.List;

/**
 *
 * @author tpham
 */
public interface ThngService {
    
// /thngs
public int getAllThngsSize(); // +
public List<Thng> getAllThngs();
public List<Thng> getAllThngs(int page);
public List<Thng> getAllThngs(int page, int size);
public Thng createThng(Thng thng);

// /thngs/{thngId}
public Thng getThngById(String thngId);
public Thng updateThngById(String thngId, Thng thng);
public void deleteThngById(String thngId);

// /thngs/{thngId}/properties
public int getThngPropertiesSize(String thngId); // +
public List<String> getThngPropertyKeys(String thngId); // +
public List<Property> getThngProperties(String thngId);
public List<Property> getThngProperties(String thngId, int page);
public List<Property> getThngProperties(String thngId, int page, int size);
public Property pushThngProperty(String thngId, Property prop);
public List<Property> pushThngProperties(String thngId, List<Property> properties);
public void deleteThngProperties(String thngId);

// /thngs/{thngId}/properties/{key}
public int getThngPropertyValuesSize(String thngId, String key); // +
public PropertyValue getLatestThngPropertyValue(String thngId, String key);
public List<PropertyValue> getThngPropertyValues(String thngId, String key);
public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page);
public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page, int size);
public List<PropertyValue> getThngPropertyValuesFrom(String thngId, String key, int page, int size, long from);
public List<PropertyValue> getThngPropertyValuesBetween(String thngId, String key, int page, int size, long from, long to);
public List<PropertyValue> getThngPropertyValuesTo(String thngId, String key, int page, int size, long to);
public PropertyValue pushThngPropertyValueByKey(String thngId, String key, String value);
public PropertyValue pushThngPropertyValueByKeyAt(String thngId, String key, String value, long timestamp); // +
public List<PropertyValue> pushThngPropertyValuesByKey(String thngId, String key, List<String> values);
public void deleteAllThngPropertyValuesByKey(String thngId, String key);
public void deleteThngPropertyValuesByKeyTo(String thngId, String key, long to);

// /thngs/{thngId}/location
public int getThngLocationSize(String thngId); // +
public Location getLatestThngLocation(String thngId);
public List<Location> getThngLocations(String thngId);
public List<Location> getThngLocations(String thngId, int page);
public List<Location> getThngLocations(String thngId, int page, int size);
public List<Location> getThngLocationsFrom(String thngId, int page, int size, long from);
public List<Location> getThngLocationsBetween(String thngId, int page, int size, long from, long to);
public List<Location> getThngLocationsTo(String thngId, int page, int size, long to);
public Location pushThngLocation(String thngId, long longitude, long latitude);
public Location pushThngLocationAt(String thngId, long longitude, long latitude, long timestamp);
public void deleteAllThngLocation(String thngId);
public void deleteThngLocationTo(String thngId, long to);

    
}
