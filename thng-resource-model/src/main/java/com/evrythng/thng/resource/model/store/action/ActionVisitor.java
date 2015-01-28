/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

/**
 * Visitor interface for actions.
 **/
public interface ActionVisitor {

	void visit(CustomAction action);

	void visit(CheckinAction action);

	void visit(ScanAction action);

	void visit(ImplicitScanAction action);

	void visit(ShareAction action);
}
