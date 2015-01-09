/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import com.evrythng.java.wrapper.exception.WrappedRuntimeException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.io.InputStream;

/**
 * JSON utilities based on Jackson {@link ObjectMapper}.
 */
public final class JSONUtils {

	/**
	 * ObjectMapper singleton.
	 */
	public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

	private JSONUtils() {
		/* Hide default constructor */
	}

	/**
	 * Deserializes the provided {@code json} {@link String} to a native
	 * {@code type} representation.
	 *
	 * @return Deserialized native instance
	 *
	 * @deprecated since 1.15
	 */
	@Deprecated
	public static <T> T read(final String json, final Class<T> type) {
		try {
			return OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			throw new WrappedRuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@code json} {@link String} to a native
	 * {@code type} representation.
	 *
	 * @return Deserialized native instance
	 */
	public static <T> T read(final String json, final TypeReference<T> type) {
		try {
			return OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			throw new WrappedRuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@link InputStream} to a native {@code type}
	 * representation.
	 *
	 * @return Deserialized native instance
	 *
	 * @deprecated since 1.15
	 */
	@Deprecated
	public static <T> T read(final InputStream inputStream, final TypeReference<T> type) {
		try {
			return OBJECT_MAPPER.readValue(inputStream, type);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			throw new WrappedRuntimeException(e);
		}
	}

	/**
	 * Deserializes the provided {@link InputStream} to a native
	 * {@code valueType} representation.
	 *
	 * @return Deserialized native instance
	 *
	 * @deprecated since 1.15
	 */
	@Deprecated
	public static <T> T read(final InputStream inputStream, final Class<T> valueType) {
		try {
			return OBJECT_MAPPER.readValue(inputStream, valueType);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			throw new WrappedRuntimeException(e);
		}
	}

	/**
	 * Converts the provided {@code object} to an JSON {@link String}.
	 */
	public static String write(final Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			throw new WrappedRuntimeException(e);
		}
	}

	/**
	 * Creates a preconfigured {@link ObjectMapper}.
	 */
	private static ObjectMapper createObjectMapper() {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		mapper.setDateFormat(new ISO8601DateFormat());

		return mapper;
	}
}
