/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.util;

import com.evrythng.java.wrapper.exception.WrappedRuntimeException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	public static <T> T read(String json, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
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
	public static <T> T read(String json, TypeReference<T> type) {
		try {
			return OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			// Wrap into unchecked exception:
			System.out.println(e);
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
	public static <T> T read(InputStream inputStream, TypeReference<T> type) throws JsonParseException, JsonMappingException, IOException {
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
	public static <T> T read(InputStream inputStream, Class<T> valueType) {
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
	public static String write(Object object) {
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
		SimpleModule evrythngModule = new SimpleModule("evrythng", new Version(3, 0, 0, "SNAPSHOT", "com.evrythng", "evrythng-java-wrapper"));

		final DateFormat dateFormat = new SimpleDateFormat("SSSSSS"); // milliseconds
		evrythngModule.addDeserializer(Calendar.class, new JsonDeserializer<Calendar>() {
			@Override
			public Calendar deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
				try {
					Calendar c = Calendar.getInstance();
					c.setTime(dateFormat.parse(arg0.getText()));
					return c;
				} catch (ParseException e) {
					return null;
				}
			}
		});

		mapper.registerModule(evrythngModule);

		// Serialization/deserialization configuration:
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return mapper;
	}
}
