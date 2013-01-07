/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.thng.resource.model.cron;

/** 
 * Scheduled job status.
 * @author Victor Sergienko (victor)
 **/
public enum JobStatus {
	NEW, EXECUTING, EXECUTED, FAILED, CANCELLED
}
