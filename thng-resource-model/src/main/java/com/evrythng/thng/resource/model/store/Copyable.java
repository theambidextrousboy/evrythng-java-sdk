package com.evrythng.thng.resource.model.store;

/**
 * Interface to represent object that can copy (all member variable overwritten by values from another object of the same type)
 * @author colin
 *
 */
public interface Copyable<TYPE> {
	public void copy(TYPE obj);
}
