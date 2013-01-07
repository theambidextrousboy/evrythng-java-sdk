package com.evrythng.thng.resource.model.cron;

import com.evrythng.thng.resource.model.core.ResourceModel;

import javax.validation.constraints.NotNull;

/** 
 * Email DTO - an object holding single email message;
 * Mandatory fields are: <code>from</code>, <code>to</code>, <code>subject</code>,
 * and (<code>body</code> OR <code>bodyUrl</code>).
 * 
 * @author Victor Sergienko (victor)
 **/
public final class Email extends ResourceModel {

	@NotNull
	private String from;

	@NotNull
	private String to;

	@NotNull
	private String subject;

	/** Either body or body should be non-empty */
	private String body;
	
	private String bodyUrl;

	@NotNull
	private String mimeType = "text/html";

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBodyUrl() {
		return bodyUrl;
	}

	public void setBodyUrl(String bodyUrl) {
		this.bodyUrl = bodyUrl;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
