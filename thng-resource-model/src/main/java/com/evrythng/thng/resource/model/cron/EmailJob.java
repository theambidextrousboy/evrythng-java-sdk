/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.thng.resource.model.cron;

import javax.validation.constraints.NotNull;

/** 
 * An email scheduled to be sent to external party.
 * @author Victor Sergienko (victor)
 **/
public class EmailJob extends AbstractJob {
	
	@NotNull
	private Email email;

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String toString() {
		return String.format("%s {id: %s, fires: %s}", 
				getClass().getSimpleName(), getId(), getFireTime().toString());
	}

}
