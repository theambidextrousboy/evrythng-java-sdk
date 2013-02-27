/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 * 
 */
package com.evrythng.thng.resource.model.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This defines the format of an error message as sent by the API. E.g., in JSON
 * such a message looks like: <code>{"status": 201, "errors":["message", "...", "moreInfo" : URL]}</code>
 * 
 * @author Dominique Guinard (domguinard)
 **/
public class ErrorMessage {

	private static final String MORE_INFO_ROOT = "https://dev.evrythng.com/documentation/api";
	/** HTTP status */
	private int status;
	/** Human-friendly error(s) description(s) */
	private List<String> errors = new ArrayList<String>();
	private String moreInfo = MORE_INFO_ROOT;

	/**
	 * Creates a new ErrorMessage.
	 */
	public ErrorMessage() {
	}

	/**
	 * Creates a new ErrorMessage.
	 * 
	 * @param status
	 */
	public ErrorMessage(int status) {
		this.status = status;
	}

	/**
	 * Creates a new ErrorMessage containing a single human-friendly description
	 * message.
	 * 
	 * @param status
	 * @param error
	 */
	public ErrorMessage(int status, String error) {
		this(status, Arrays.asList(error));
	}

	/**
	 * Creates a new ErrorMessage containing a single human-friendly description
	 * message.
	 * 
	 * @param code
	 * @param error
	 * @param moreInfo Page or anchor in the documentation for more information e.g., #search
	 */
	public ErrorMessage(int code, String error, String moreInfo) {
		this(code, Arrays.asList(error), "");
	}

	/**
	 * Creates a new error message containing several errors.
	 * 
	 * @param code
	 * @param errors
	 */
	public ErrorMessage(int code, List<String> errors) {
		this(code, errors, "");
	}

	/**
	 * @param status
	 * @param errors
	 * @param moreInfo Page or anchor in the documentation for more information e.g., #search
	 */
	public ErrorMessage(int status, List<String> errors, String moreInfo) {
		this.status = status;
		this.errors = errors;
		this.moreInfo = String.format("%s%s", MORE_INFO_ROOT, moreInfo);
	}

	public int getStatus() {
		return status;
	}

	public void setStatusCode(int statusCode) {
		this.status = statusCode;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
