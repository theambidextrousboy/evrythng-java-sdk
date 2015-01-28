/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.action;

import java.util.List;

/**
 * Model for a share
 **/
public class ShareAction extends Action {

	private static final long serialVersionUID = 4341114489496080130L;

	public static final String TYPE = ActionType.Value.SHARES.value();

	private List<String> sharedWith;

	public ShareAction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ActionVisitor visitor) {
		visitor.visit(this);
	}

	public List<String> getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(List<String> sharedWith) {
		this.sharedWith = sharedWith;
	}

}
