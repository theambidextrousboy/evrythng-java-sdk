/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Olten
 * www.evrythng.com
 */
package com.evrythng.commons;


/**
 * Return type of a bulk update operation for the different service. Contains
 * only the number of update results.
 * 
 **/
public class BulkUpdate {

	public final long updatedCount;

	public BulkUpdate(long updatedCount) {
		if (updatedCount < 0) {
			throw new IllegalArgumentException("Negative : " + updatedCount);
		}
		this.updatedCount = updatedCount;
	}
}
