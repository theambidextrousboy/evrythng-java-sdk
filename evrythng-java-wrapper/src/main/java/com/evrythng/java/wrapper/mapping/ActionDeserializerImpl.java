/*
 * (c) Copyright 2013 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.java.wrapper.mapping;

import com.evrythng.thng.resource.model.store.action.Action;

/**
 * Action deserializer.
 *
 **/
public class ActionDeserializerImpl extends TypeMapDeserializer<Action> implements ActionDeserializer {

	private static final long serialVersionUID = 1L;

	private Class<? extends Action> customClass;

	public ActionDeserializerImpl(Class<? extends Action> customClass) {
		super(Action.class, Action.FIELD_TYPE);
		this.customClass = customClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends Action> resolveClass(String type) {

		if (type.startsWith("_")) {
			return customClass;
		} else {
			return super.resolveClass(type);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Action> String getActionType(Class<T> actionClass) {
		return getObjectType(actionClass);
	}
}