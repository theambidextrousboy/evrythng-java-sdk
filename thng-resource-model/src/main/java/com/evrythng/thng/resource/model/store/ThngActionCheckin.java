/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model class for a check-in thng action.
 * 
 * @author Michel Yerly (my)
 **/
@Deprecated
public class ThngActionCheckin extends ThngAction {

	public ThngActionCheckin downCastCopy(ThngAction action) {
		return (ThngActionCheckin) new ThngActionCheckin().shallowCopyFrom(action);
	}
}
