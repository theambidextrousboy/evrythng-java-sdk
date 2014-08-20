/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.commons;

import java.util.List;

/**
 * Return type of a batch update operation for the different service. Contains
 * the
 * updated items, with a max of {@value #MAX_UPDATED_RESULT_RETURNED} items.
 * Along with the real amount of document updated. Immutable.
 * 
 **/
public class BatchUpdate<E> {

	public static int MAX_UPDATED_RESULT_RETURNED = 100;

	private final List<E> updatedItems;

	private final long updatedCount;

	public BatchUpdate(List<E> updatedItems, long updatedCount) {
		if (updatedCount < 0) {
			throw new IllegalArgumentException("Negative : " + updatedItems);
		}
		this.updatedItems = updatedItems;
		this.updatedCount = updatedCount;
	}

	/**
	 * @return the updatedItems
	 */
	public List<E> getUpdatedItems() {
		return updatedItems;
	}

	/**
	 * @return the updatedCount
	 */
	public long getUpdatedCount() {
		return updatedCount;
	}

}
