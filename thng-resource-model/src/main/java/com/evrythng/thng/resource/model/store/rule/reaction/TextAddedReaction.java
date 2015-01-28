/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when text has been added to the action response.
 **/
public class TextAddedReaction extends Reaction {

	private static final long serialVersionUID = 1215620134284365423L;

	public static final String TYPE = "textAdded";

	private String text;

	public TextAddedReaction() {
		setType(TYPE);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}
}
