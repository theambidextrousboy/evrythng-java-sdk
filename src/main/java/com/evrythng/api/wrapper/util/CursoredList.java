/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.api.wrapper.util;

import java.util.ArrayList;
import java.util.Collection;


/**
 * List that includes previous and next cursors for paging through items
 * returned in cursored pages.
 *
 * @author tpham
 * @param <T> the list element type
 * @copyright  2012 Evrythng Ltd London / Zurich
 */
public class CursoredList<T> extends ArrayList<T> {

    private final long previousCursor;
    private final long nextCursor;

    public CursoredList(Collection<? extends T> collection, long previousCursor, long nextCursor) {
        super(collection);
        this.previousCursor = previousCursor;
        this.nextCursor = nextCursor;
    }

    public CursoredList(int initialCapacity, long previousCursor, long nextCursor) {
        super(initialCapacity);
        this.previousCursor = previousCursor;
        this.nextCursor = nextCursor;
    }

    /**
     * The cursor to retrieve the previous page of results.
     */
    public long getPreviousCursor() {
        return previousCursor;
    }

    /**
     * The cursor to retrieve the next page of results.
     */
    public long getNextCursor() {
        return nextCursor;
    }

    /**
     * Returns true if there is a previous page of results.
     */
    public boolean hasPrevious() {
        return previousCursor > 0;
    }

    /**
     * Returns true if there is a next page of results.
     */
    public boolean hasNext() {
        return nextCursor > 0;
    }
}