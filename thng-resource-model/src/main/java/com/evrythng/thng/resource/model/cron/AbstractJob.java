/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.thng.resource.model.cron;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.evrythng.thng.resource.model.core.ResourceModel;
import com.evrythng.thng.resource.model.store.Thng;

/**
 * A scheduled job that will do SOMETHNG, derived classes define what.
 * 
 * @author Victor Sergienko (victor)
 **/
public abstract class AbstractJob extends ResourceModel {

	/** Date the job will be executed */
	@NotNull
	protected Date fireTime;
	
	/** {@link Thng} the job is connected to, if any */
	@NotNull
	protected String owner;
	
	/** If the job was already executed/cancelled/etc */
	@NotNull
	private JobStatus status = JobStatus.NEW;

	public Date getFireTime() {
		return fireTime;
	}

	public void setFireTime(Date fire) {
		this.fireTime = fire;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

}
