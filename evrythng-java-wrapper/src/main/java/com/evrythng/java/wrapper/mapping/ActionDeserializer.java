/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.CustomAction;

/**
 * Action deserializer interface.
 * 
 * @author Michel Yerly (my)
 **/
public interface ActionDeserializer {

	/**
	 * Gets the action type that correspond to the class.
	 * 
	 * @return The action type, or null if the class is not recognized or is
	 *         {@link CustomAction}.
	 */
	<T extends Action> String getActionType(Class<T> actionClass);
}
