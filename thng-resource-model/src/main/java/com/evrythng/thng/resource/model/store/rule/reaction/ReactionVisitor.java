/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store.rule.reaction;

/**
 * Defines the visit methods for the visitor pattern in the reactions.
 **/
public interface ReactionVisitor {

	void visit(MultimediaContentGrantedReaction reaction);

	void visit(LoyaltyPointsChangedReaction reaction);

	void visit(PhysicalAssetGrantedReaction reaction);

	void visit(TextAddedReaction reaction);

	void visit(TierLevelChangedReaction reaction);

	void visit(PostedToSocialNetworkReaction reaction);

	void visit(ActionTagsSetReaction reaction);

	void visit(DataAddedReaction reaction);

	void visit(RedirectionReaction reaction);
}
