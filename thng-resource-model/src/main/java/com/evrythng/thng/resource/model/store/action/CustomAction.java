/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a custom action.
 **/
public class CustomAction extends ThngAction {

	private static final long serialVersionUID = 1740656538764246719L;

	public CustomAction() {

	}

	public CustomAction(String type) {
		setType(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}

}
