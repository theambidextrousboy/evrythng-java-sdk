package com.evrythng.thng.resource.model.store;

import java.io.Serializable;

/**
 * Model class for time information.
 */
public class TimeInfo implements Serializable {

    private static final long serialVersionUID = 9138301959922746759L;

    private Long timestamp;
    private Long offset;
    private String localTime;
    private Long nextChange;

    /**
     * Gets the Unix timestamp in milliseconds (number of milliseconds elapsed since epoch).
     */
    public Long getTimestamp() {

        return timestamp;
    }

    /**
     * Sets the Unix timestamp to set in milliseconds (number of milliseconds elapsed since epoch).
     */
    public void setTimestamp(final Long timestamp) {

        this.timestamp = timestamp;
    }

    /**
     * Gets the offset of the local time compared to UTC in milliseconds.
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * Sets the offset of the local time compared to UTC in milliseconds.
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }

    /**
     * Gets the local time as a ISO 8061 formatted string.
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * Sets the local time as a ISO 8061 formatted string.
     */
    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    /**
     * Gets the next expected change in the offset in milliseconds since epoch.
     */
    public Long getNextChange() {
        return nextChange;
    }

    /**
     * Sets the next expected change in the offset in milliseconds since epoch.
     */
    public void setNextChange(Long nextChange) {
        this.nextChange = nextChange;
    }
}
