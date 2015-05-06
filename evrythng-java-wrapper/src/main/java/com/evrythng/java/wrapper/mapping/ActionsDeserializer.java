/*
* (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
* www.evrythng.com
*/
package com.evrythng.java.wrapper.mapping;

import com.evrythng.java.wrapper.util.JSONUtils;
import com.evrythng.thng.resource.model.store.action.Action;
import com.evrythng.thng.resource.model.store.action.Actions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

/**
* Base class for deserializers with a type map.
*/
public final class ActionsDeserializer extends StdDeserializer<Actions> {

	private static final long serialVersionUID = 154194441824112030L;

	private static final class ActionsImpl extends Actions {

		private static final long serialVersionUID = 7999055054999253850L;
	}

	private final StdDeserializer<Action> singleActionDeserializer;

	public ActionsDeserializer(final StdDeserializer<Action> singleActionDeserializer) {

		super(Actions.class);
		if (singleActionDeserializer == null) {
			throw new IllegalArgumentException("singleActionDeserializer cannot be null");
		}
		this.singleActionDeserializer = singleActionDeserializer;
	}

	@Override
	public Actions deserialize(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		ObjectMapper mapper = JSONUtils.OBJECT_MAPPER;
		JsonNode node = mapper.readTree(jp);
		if (node.isArray()) {
			return mapper.readValue(node.toString(), ActionsImpl.class);
		} else {
			ArrayNode array = mapper.createArrayNode();
			array.add(node);
			return mapper.readValue(array.toString(), ActionsImpl.class);
		}
	}
}
