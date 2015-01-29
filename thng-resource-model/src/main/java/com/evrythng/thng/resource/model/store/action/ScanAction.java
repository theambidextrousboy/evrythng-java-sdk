/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Model for a thng scan
 **/
public class ScanAction extends ThngAction {

	private static final long serialVersionUID = -3694545415219237620L;

	public static final String TYPE = ActionType.Value.SCANS.value();

	public ScanAction() {
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
