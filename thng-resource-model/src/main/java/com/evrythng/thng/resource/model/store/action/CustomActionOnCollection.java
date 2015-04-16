/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a share
 **/
public class CustomActionOnCollection extends ActionOnCollection {

	private static final long serialVersionUID = 8323923895243080100L;

	public CustomActionOnCollection(final String type) {
		super(type);
	}

	public CustomActionOnCollection() {

	}

	@Override
	public void accept(final ActionVisitor visitor) {

		// TODO _MS_ here
//		visitor.visit(this);
	}
}
