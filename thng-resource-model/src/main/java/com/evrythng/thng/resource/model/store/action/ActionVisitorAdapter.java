/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Adapter class for {@link ActionVisitor}.
 **/
public class ActionVisitorAdapter implements ActionVisitor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(CustomAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(CheckinAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ScanAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ImplicitScanAction action) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void visit(ShareAction action) {
	}

}
