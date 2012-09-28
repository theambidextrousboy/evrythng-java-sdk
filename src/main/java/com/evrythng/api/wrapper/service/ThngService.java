package com.evrythng.api.wrapper.service;

import java.util.List;

import org.apache.http.HttpResponse;

import com.evrythng.api.wrapper.ApiConfiguration;
import com.evrythng.api.wrapper.core.AbstractApiService;
import com.evrythng.api.wrapper.core.ApiBuilder;
import com.evrythng.api.wrapper.core.ApiBuilder.Builder;
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

	public Builder<Thng> createThng(Thng thng) {
		return ApiBuilder.post(config.getKey(), absoluteUri(PATH_THNGS), thng, new TypeReference<Thng>() {
		});
	}

	public Builder<List<String>> createThngs(List<Thng> thngs) {
		return ApiBuilder.put(config.getKey(), absoluteUri(PATH_THNGS_BULK), thngs, new TypeReference<List<String>>() {
		});
	}

	public Builder<List<Thng>> getThngs() {
		return ApiBuilder.get(config.getKey(), absoluteUri(PATH_THNGS), new TypeReference<List<Thng>>() {
		});
	}

	public Builder<Thng> getThng(String thngId) {
		return ApiBuilder.get(config.getKey(), absoluteUri(String.format(PATH_THNG, thngId)), new TypeReference<Thng>() {
		});
	}

	public Builder<Thng> updateThng(String thngId, Thng thng) {
		return ApiBuilder.put(config.getKey(), absoluteUri(String.format(PATH_THNG, thngId)), thng, new TypeReference<Thng>() {
		});
	}

	public Builder<HttpResponse> deleteThng(String thngId) {
		return ApiBuilder.delete(config.getKey(), absoluteUri(String.format(PATH_THNG, thngId)));
	}

	public Builder<List<Property>> getThngProperties(String thngId) {
		return ApiBuilder.get(config.getKey(), absoluteUri(String.format(PATH_THNG_PROPERTIES, thngId)), new TypeReference<List<Property>>() {
		});
	}

	public Builder<List<PropertyValue>> getThngProperty(String thngId, String key) {
		return ApiBuilder.get(config.getKey(), absoluteUri(String.format(PATH_THNG_PROPERTY, thngId, key)), new TypeReference<List<PropertyValue>>() {
		});
	}

	public Builder<List<Property>> createThngProperties(String thngId, List<Property> properties) {
		return ApiBuilder.put(config.getKey(), absoluteUri(String.format(PATH_THNG_PROPERTIES, thngId)), properties, new TypeReference<List<Property>>() {
		});
	}

	public Builder<HttpResponse> deleteThngProperty(String thngId, String key) {
		return ApiBuilder.delete(config.getKey(), absoluteUri(String.format(PATH_THNG_PROPERTY, thngId, key)));
	}
}
