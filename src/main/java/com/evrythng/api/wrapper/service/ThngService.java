package com.evrythng.api.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.AbstractApiService;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
import com.evrythng.api.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ThngService extends AbstractApiService {

	public static final String PATH_THNGS = "/thngs";
	public static final String PATH_THNGS_BULK = PATH_THNGS + "/bulk";
	public static final String PATH_THNG = PATH_THNGS + "/%s";
	public static final String PATH_THNG_PROPERTIES = PATH_THNG + "/properties";
	public static final String PATH_THNG_PROPERTY = PATH_THNG_PROPERTIES + "/%s";
	public static final String PATH_THNG_LOCATION = PATH_THNG + "/location";

	public ThngService(ApiConfiguration config) {
		super(config);
	}

	/* ***** Thng ***** */

	public Builder<Thng> thngCreator(Thng thng) throws EvrythngClientException {
		return post(PATH_THNGS, thng, new TypeReference<Thng>() {
		});
	}

	public Builder<List<String>> thngsCreator(List<Thng> thngs) throws EvrythngClientException {
		return put(PATH_THNGS_BULK, thngs, new TypeReference<List<String>>() {
		});
	}

	public Builder<List<Thng>> thngsReader() throws EvrythngClientException {
		return get(PATH_THNGS, new TypeReference<List<Thng>>() {
		});
	}

	public Builder<Thng> thngReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG, thngId), new TypeReference<Thng>() {
		});
	}

	public Builder<Thng> thngUpdater(String thngId, Thng thng) throws EvrythngClientException {
		return put(String.format(PATH_THNG, thngId), thng, new TypeReference<Thng>() {
		});
	}

	public Builder<Boolean> thngDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG, thngId));
	}

	/* ***** Thng > Properties ***** */

	public Builder<List<Property>> propertiesCreator(String thngId, Property property) throws EvrythngClientException {
		return propertiesCreator(thngId, Arrays.asList(property));
	}

	public Builder<List<Property>> propertiesCreator(String thngId, List<Property> properties) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTIES, thngId), properties, new TypeReference<List<Property>>() {
		});
	}

	public Builder<List<Property>> propertiesReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTIES, thngId), new TypeReference<List<Property>>() {
		});
	}

	public Builder<List<PropertyValue>> propertyReader(String thngId, String key) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTY, thngId, key), new TypeReference<List<PropertyValue>>() {
		});
	}

	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, String value) throws EvrythngClientException {
		return propertyUpdater(thngId, key, new PropertyValue(value));
	}

	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, PropertyValue value) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTY, thngId, key), Arrays.asList(value), new TypeReference<List<PropertyValue>>() {
		});
	}

	public Builder<Boolean> propertyDeleter(String thngId, String key) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_PROPERTY, thngId, key));
	}

	/* ***** Thng > Location ***** */

	public Builder<List<Location>> locationReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_LOCATION, thngId), new TypeReference<List<Location>>() {
		});
	}

	public Builder<List<Location>> locationUpdater(String thngId, Location location) throws EvrythngClientException {
		return locationUpdater(thngId, Arrays.asList(location));
	}

	public Builder<List<Location>> locationUpdater(String thngId, List<Location> locations) throws EvrythngClientException {
		return put(String.format(PATH_THNG_LOCATION, thngId), locations, new TypeReference<List<Location>>() {
		});
	}

	public Builder<Boolean> locationDeleter(String thngId, String key) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_LOCATION, thngId));
	}
}
