/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a share
 **/
public abstract class ActionOnCollection extends Action {

	private static final long serialVersionUID = -3695192368487217322L;

	private String collectionId;

	public ActionOnCollection(final String type) {
		setType(type);
	}



	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}
}
