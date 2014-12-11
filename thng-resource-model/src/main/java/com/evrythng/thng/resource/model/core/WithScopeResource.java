/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

/**
 * Defining behaviour for classes with {@code ScopeResource}.
 */
public interface WithScopeResource {

	ScopeResource getScopes();

	void setScopes(final ScopeResource scopes);
}
