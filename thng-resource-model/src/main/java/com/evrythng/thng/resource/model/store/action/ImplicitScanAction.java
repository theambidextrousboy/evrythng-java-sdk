/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a thng scan that is implicitly created by thng-li.
 **/
public class ImplicitScanAction extends ThngAction {

	private static final long serialVersionUID = 5893944076236289578L;

	public static final String TYPE = ActionType.Value.IMPLICIT_SCANS.value();

	public ImplicitScanAction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}

}
