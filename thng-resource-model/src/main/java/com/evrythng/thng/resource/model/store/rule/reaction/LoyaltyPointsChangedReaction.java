/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

import com.evrythng.thng.resource.model.store.LoyaltyPoints;

/**
 * Reaction when loyalty points were granted.
 **/
public class LoyaltyPointsChangedReaction extends Reaction {

	private static final long serialVersionUID = -343569540115074755L;

	public static final String TYPE = "loyaltyPointsChanged";

	private LoyaltyPoints pointChanges;
	private LoyaltyPoints points;

	public LoyaltyPointsChangedReaction() {
		setType(TYPE);
	}

	public LoyaltyPoints getPointChanges() {
		return pointChanges;
	}

	public void setPointChanges(LoyaltyPoints pointChanges) {
		this.pointChanges = pointChanges;
	}

	public LoyaltyPoints getPoints() {
		return points;
	}

	public void setPoints(LoyaltyPoints points) {
		this.points = points;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(ReactionVisitor visitor) {
		visitor.visit(this);
	}

}
