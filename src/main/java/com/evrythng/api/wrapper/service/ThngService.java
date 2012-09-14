package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;

/**
 * 
 * @author tpham
 */
public class ThngService extends AbstractApiService {

	public ThngService(Configuration config) {
		super(config);
		if (config.isWrapperBehindAccessController()) {
			this.contextPath = config.getThngServiceContextPath();
		}
	}

	public Integer getAllThngsSize() {
		URI uri = this.buildUri("/thngs");
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
	}

	public List<Thng> getAllThngs() {
		URI uri = this.buildUri("/thngs");
		return this.restTemplate.get(uri, new TypeReference<List<Thng>>() {});
	}

	public List<Thng> getAllThngs(int page) {
		URI uri = this.buildUri("thngs", buildPagingParameters(page));
		return this.restTemplate.get(uri, new TypeReference<List<Thng>>() {
		});
	}

	public List<Thng> getAllThngs(int page, int size) {
		URI uri = this.buildUri("thngs", buildPagingParameters(page, size));
		return this.restTemplate.get(uri, new TypeReference<List<Thng>>() {
		});
	}

	public Thng createThng(Thng thng) {
		URI uri = this.buildUri("/thngs");
		return this.restTemplate.post(uri, thng, new TypeReference<Thng>() {
		});
	}

	public Thng getThngById(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId);
		return this.restTemplate.get(uri, new TypeReference<Thng>() {
		});
	}

	public Thng updateThngById(String thngId, Thng thng) {
		URI uri = this.buildUri("/thngs/" + thngId);
		return this.restTemplate.put(uri, thng, new TypeReference<Thng>() {
		});
	}

	public void deleteThngById(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId);
		this.restTemplate.delete(uri);
	}

	public int getThngPropertiesSize(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
	}

	public List<String> getThngPropertyKeys(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		List<Property> properties = this.restTemplate.get(uri, new TypeReference<List<Property>>() {
		});
		List<String> keys = new ArrayList<String>();
		for (Property prop : properties) {
			keys.add(prop.getKey());
		}
		return keys;
	}

	public List<Property> getThngProperties(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		return this.restTemplate.get(uri, new TypeReference<List<Property>>() {
		});
	}

	public List<Property> getThngProperties(String thngId, int page) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties", buildPagingParameters(page));
		return this.restTemplate.get(uri, new TypeReference<List<Property>>() {
		});
	}

	public List<Property> getThngProperties(String thngId, int page, int size) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties", buildPagingParameters(page, size));
		return this.restTemplate.get(uri, new TypeReference<List<Property>>() {
		});
	}

	public Property pushThngProperty(String thngId, Property prop) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		List<Property> proterties = new ArrayList<Property>();
		proterties.add(prop);
		List<Property> properties = this.restTemplate.put(uri, proterties, new TypeReference<List<Property>>() {
		});
		return properties.get(0);
	}

	public List<Property> pushThngProperties(String thngId, List<Property> properties) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		return this.restTemplate.put(uri, properties, new TypeReference<List<Property>>() {
		});
	}

	public void deleteThngProperties(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties");
		this.restTemplate.delete(uri);
	}

	public int getThngPropertyValuesSize(String thngId, String key) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
	}

	public PropertyValue getLatestThngPropertyValue(String thngId, String key) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		List<PropertyValue> listValues = this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
		if (listValues != null && listValues.size() > 0)
			return listValues.get(0);
		return null;
	}

	public List<PropertyValue> getThngPropertyValues(String thngId, String key) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, buildPagingParameters(page));
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public List<PropertyValue> getThngPropertyValues(String thngId, String key, int page, int size) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, buildPagingParameters(page, size));
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public List<PropertyValue> getThngPropertyValuesFrom(String thngId, String key, int page, int size, long from) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, buildPagingParametersFrom(page, size, from));
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public List<PropertyValue> getThngPropertyValuesBetween(String thngId, String key, int page, int size, long from, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, buildPagingParametersFromTo(page, size, from, to));
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public List<PropertyValue> getThngPropertyValuesTo(String thngId, String key, int page, int size, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, buildPagingParametersTo(page, size, to));
		return this.restTemplate.get(uri, new TypeReference<List<PropertyValue>>() {
		});
	}

	public PropertyValue pushThngPropertyValueByKey(String thngId, String key, String value) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		PropertyValue propertyValue = new PropertyValue();
		propertyValue.setValue(value);
		List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
		propertyValueList.add(propertyValue);
		List<PropertyValue> propertyValues = this.restTemplate.put(uri, propertyValueList, new TypeReference<List<PropertyValue>>() {
		});
		return propertyValues.get(0);
	}

	public PropertyValue pushThngPropertyValueByKeyAt(String thngId, String key, String value, long timestamp) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		PropertyValue propertyValue = new PropertyValue();
		propertyValue.setValue(value);
		propertyValue.setTimestamp(timestamp);
		List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
		propertyValueList.add(propertyValue);
		List<PropertyValue> propertyValues = this.restTemplate.put(uri, propertyValueList, new TypeReference<List<PropertyValue>>() {
		});
		return propertyValues.get(0);
	}

	public List<PropertyValue> pushThngPropertyValuesByKey(String thngId, String key, List<String> values) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
		for (String value : values) {
			PropertyValue propertyValue = new PropertyValue();
			propertyValue.setValue(value);
			propertyValueList.add(propertyValue);
		}
		return this.restTemplate.put(uri, propertyValueList, new TypeReference<List<PropertyValue>>() {
		});
	}

	public void deleteAllThngPropertyValuesByKey(String thngId, String key) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key);
		this.restTemplate.delete(uri);
	}

	public void deleteThngPropertyValuesByKeyTo(String thngId, String key, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/properties/" + key, "to", String.valueOf(to));
		this.restTemplate.delete(uri);
	}

	public int getThngLocationSize(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		Header xResultCountHeader = this.restTemplate.getHttpHeader(uri, "x-result-count");
		return Integer.valueOf(xResultCountHeader.getValue()).intValue();
	}

	public Location getLatestThngLocation(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		List<Location> locations = this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
		if (locations != null && locations.size() > 0)
			return locations.get(0);
		return null;
	}

	public List<Location> getThngLocations(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public List<Location> getThngLocations(String thngId, int page) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", buildPagingParameters(page));
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public List<Location> getThngLocations(String thngId, int page, int size) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", buildPagingParameters(page, size));
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public List<Location> getThngLocationsFrom(String thngId, int page, int size, long from) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", buildPagingParametersFrom(page, size, from));
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public List<Location> getThngLocationsBetween(String thngId, int page, int size, long from, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", buildPagingParametersFromTo(page, size, from, to));
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public List<Location> getThngLocationsTo(String thngId, int page, int size, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", buildPagingParametersTo(page, size, to));
		return this.restTemplate.get(uri, new TypeReference<List<Location>>() {
		});
	}

	public Location pushThngLocation(String thngId, double longitude, double latitude) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		List<Location> locations = new ArrayList<Location>();
		Location aLocation = new Location();
		aLocation.setLatitude(latitude);
		aLocation.setLongitude(longitude);
		locations.add(aLocation);
		List<Location> pushedLocations = this.restTemplate.put(uri, locations, new TypeReference<List<Location>>() {
		});
		return pushedLocations.get(0);
	}

	public Location pushThngLocationAt(String thngId, double longitude, double latitude, long timestamp) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		List<Location> locations = new ArrayList<Location>();
		Location aLocation = new Location();
		aLocation.setLatitude(latitude);
		aLocation.setLongitude(longitude);
		aLocation.setTimestamp(timestamp);
		locations.add(aLocation);
		List<Location> pushedLocations = this.restTemplate.put(uri, locations, new TypeReference<List<Location>>() {
		});
		return pushedLocations.get(0);
	}

	public List<Location> pushThngLocation(String thngId, List<Location> locations) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		return this.restTemplate.put(uri, locations, new TypeReference<List<Location>>() {
		});

	}

	public void deleteAllThngLocation(String thngId) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location");
		this.restTemplate.delete(uri);
	}

	public void deleteThngLocationTo(String thngId, long to) {
		URI uri = this.buildUri("/thngs/" + thngId + "/location", "to", String.valueOf(to));
		this.restTemplate.delete(uri);
	}

}
