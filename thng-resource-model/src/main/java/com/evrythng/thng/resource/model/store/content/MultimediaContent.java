/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.content;

/**
 * Rest model for a MultimediaContent.
 */
public class MultimediaContent extends Content {

	private static final long serialVersionUID = -1826858368423203504L;

	private String htmlFragment;

	public MultimediaContent() {
	}

	public String getHtmlFragment() {
		return htmlFragment;
	}

	public void setHtmlFragment(String htmlFragment) {
		this.htmlFragment = htmlFragment;
	}

}
