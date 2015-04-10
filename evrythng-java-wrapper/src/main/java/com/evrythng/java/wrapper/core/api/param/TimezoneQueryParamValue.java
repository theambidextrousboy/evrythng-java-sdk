package com.evrythng.java.wrapper.core.api.param;

import com.evrythng.java.wrapper.core.api.QueryParamValue;

/**
 * Provides support for the {@code "tz"} query param.
 */
public class TimezoneQueryParamValue extends QueryParamValue {

    public static final String NAME = "tz";

    public TimezoneQueryParamValue(String value) {
        super(NAME, value);
    }

    /**
     * @param tz
     *            Id of the timezone, as per IANA.
     * @return query parameter for this timezone.
     */
    public static TimezoneQueryParamValue value(final String tz) {
        return new TimezoneQueryParamValue(tz);
    }
}
