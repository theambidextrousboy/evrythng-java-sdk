/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when data has been added to the action response. This is hipster.
 * Use it wisely.
 * 
 **/
public class DataAddedReaction extends Reaction {

	private static final long serialVersionUID = 2820875931797220143L;

	public static final String TYPE = "dataAdded";

	private Object data;

	public DataAddedReaction() {
		setType(TYPE);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
