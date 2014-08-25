/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.access;

/**
 * Exchanged information during registration / validation / authentication
 * 
 **/
public class Credentials {

	/** Value of getStatus() */
	public static final String STATUS_ACTIVE = "active";
	public static final String STATUS_INACTIVE = "inactive";
	public static final String STATUS_ANONYMOUS = "anonymous";

	private String evrythngUser;
	private String activationCode;
	private String email;
	private String password;
	private String status;
	private String evrythngApiKey;

	public Credentials() {
		// nop
	}

	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the evrythngUser (userId)
	 */
	public String getEvrythngUser() {
		return evrythngUser;
	}

	/**
	 * @param evrythngUser
	 *            the evrythngUser (userId) to set
	 */
	public void setEvrythngUser(String evrythngUser) {
		this.evrythngUser = evrythngUser;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * @param activationCode
	 *            the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the evrythngApiKey
	 */
	public String getEvrythngApiKey() {
		return evrythngApiKey;
	}

	/**
	 * @param evrythngApiKey
	 *            the evrythngApiKey to set
	 */
	public void setEvrythngApiKey(String evrythngApiKey) {
		this.evrythngApiKey = evrythngApiKey;
	}

}
