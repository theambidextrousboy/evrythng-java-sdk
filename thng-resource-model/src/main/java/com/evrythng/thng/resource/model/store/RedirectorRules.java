/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

import java.util.List;

/**
 * Model representation for <em>redirector rules</em>.
 */
public class RedirectorRules extends DurableResourceModel {

	private static final long serialVersionUID = -5195473337467893558L;
	private List<RedirectorRule> rules;

	public List<RedirectorRule> getRules() {

		return rules;
	}

	public void setRules(final List<RedirectorRule> rules) {

		this.rules = rules;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		RedirectorRules that = (RedirectorRules) o;

		return !(rules != null ? !rules.equals(that.rules) : that.rules != null);

	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (rules != null ? rules.hashCode() : 0);
		return result;
	}
}
