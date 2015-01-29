/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Reaction when text has been added to the action response.
 **/
public class TierLevelChangedReaction extends Reaction {

	private static final long serialVersionUID = 4259099929012220973L;

	public static final String TYPE = "tierLevelChanged";

	private String tierLevel;
	private String scheme;

	public TierLevelChangedReaction() {
		setType(TYPE);
	}

	public String getTierLevel() {
		return tierLevel;
	}

	public void setTierLevel(String tierLevel) {
		this.tierLevel = tierLevel;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
