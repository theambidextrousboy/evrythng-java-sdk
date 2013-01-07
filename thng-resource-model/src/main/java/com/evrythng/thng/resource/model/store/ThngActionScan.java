/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model class for a scan thng action
 * 
 * @author Michel Yerly (my)
 **/
public class ThngActionScan extends ThngAction {
	public ThngActionScan downCastCopy(ThngAction action) {
		return (ThngActionScan) new ThngActionScan().shallowCopyFrom(action);
	}

}
