/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.api.wrapper.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * JSON utilities based on Jackson {@link ObjectMapper}.
 * 
 * TODO: inject <em>static</em> resources (Logger, ConfiguredObjectMapper) once
 * supported (cf. https://issues.jboss.org/browse/CDI-51)
 * 
 * @author Dominique Guinard (dom)
 **/
public final class JSONUtils {

	private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);
	
	/**
     * ObjectMapper singleton.
     */
    private static ObjectMapper MAPPER = null;

	private JSONUtils() {
		/* Hide default constructor */
	}

	/**
	 * Binds the current {@code json} {@link String} with the provided
	 * {@code klass}.
	 * 
	 * @param json
	 * @param klass
	 * @return
	 */
	public static <K> K toBean(String json, Class<K> klass) {
		K result = null;
		try {
			result = getObjectMapper().readValue(json, klass);
		} catch (Exception e) {
			// Wrap any exception into an unchecked exception:
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Converts the provided {@code object} to an JSON {@link String}.
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		String result = null;
		try {
			result = getObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			// Wrap any exception into an unchecked exception:
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Logs the provided {@code items} {@link Collection} by outputting
	 * each item as JSON {@link String}.
	 * 
	 * @see #toString(Object)
	 * 
	 * @param items
	 */
	public static void debug(Collection<?> items) {
		logger.debug("> Logging {} item(s):", items.size());
		int count = 1;
		for (Object item : items) {
			logger.debug(">> {} => {}", count++, toString(item));
		}
	}
	
	/**
     * Create an {@link ObjectMapper} and register all of the custom types
     * needed
     * in order to properly deserialize complex evrythng-specific types.
     *
     * @return Assembled Jackson mapper instance.
     */
    public static ObjectMapper getObjectMapper() {
        if (MAPPER == null) {
            MAPPER = createObjectMapper();
        }
        return MAPPER;
    }
	
	private static ObjectMapper createObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule evrythngModule = new SimpleModule("evrythng", new Version(3, 0, 0, "SNAPSHOT"));

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
        return mapper;
    }
}
