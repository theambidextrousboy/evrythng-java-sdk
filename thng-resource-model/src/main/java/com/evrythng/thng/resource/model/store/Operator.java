/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>operator</em>.
 */
public class Operator extends AbstractUser {

	private static final long serialVersionUID = 2282241536928517609L;
	private OperatorStatus operatorStatus;
	private String registrationCode;

	public OperatorStatus getOperatorStatus() {

		return operatorStatus;
	}

	public void setOperatorStatus(final OperatorStatus operatorStatus) {

		this.operatorStatus = operatorStatus;
	}

	public void setRegistrationCode(final String registrationCode) {

		this.registrationCode = registrationCode;
	}

	public String getRegistrationCode() {

		return registrationCode;
	}
}
