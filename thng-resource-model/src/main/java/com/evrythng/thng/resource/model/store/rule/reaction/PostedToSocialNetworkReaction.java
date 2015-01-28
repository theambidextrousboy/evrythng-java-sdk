/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when something was posted to the social network.
 * 
 **/
public class PostedToSocialNetworkReaction extends Reaction {

	private static final long serialVersionUID = -9083388913051716319L;

	public static final String TYPE = "postedToSocialNetwork";

	public PostedToSocialNetworkReaction() {
		setType(TYPE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
