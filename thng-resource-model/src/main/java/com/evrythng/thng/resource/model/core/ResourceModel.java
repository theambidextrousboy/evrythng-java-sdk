/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import com.evrythng.commons.LowerCaseKeyMap;
import com.evrythng.commons.annotations.csv.CsvTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Abstract model representation for resources.
 */
public abstract class ResourceModel implements Serializable, WithScopeResource {

	private static final long serialVersionUID = -2842591513551534611L;
	public static final String FIELD_ID = "id";
	private String id;
	private Long createdAt;
	private Map<String, Object> customFields;
	private List<String> tags;
	private ScopeResource scopes;

	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	public Long getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(final Long createdAt) {

		this.createdAt = createdAt;
	}

	@CsvTransient
	public Map<String, Object> getCustomFields() {
		
		return customFields != null ? Collections.unmodifiableMap(customFields) : null;
	}
	
	@JsonIgnore
	public <T> T getCustomField(final String key){

		return customFields != null ? (T) customFields.get(key) : null;
	}

	public void setCustomFields(final Map<String, Object> customFields) {

		if (customFields == null){
			this.customFields = null;
		}
		else {
			this.customFields = new LowerCaseKeyMap<>();
			this.customFields.putAll(customFields);
		}
	}

	public void addCustomFields(final String key, final Object value) {

		if (customFields == null) {
			customFields = new LowerCaseKeyMap<>();
		}
		customFields.put(key, value);
	}

	public List<String> getTags() {

		return tags;
	}

	public void setTags(final List<String> tags) {

		this.tags = tags;
	}

	@Override
	public ScopeResource getScopes() {

		return scopes;
	}

	@Override
	public void setScopes(final ScopeResource scopes) {

		this.scopes = scopes;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param o the ResourceModel subclass object with which to compare.
	 * @return {@code true} if this object is the same as the argument
	 * or they both have not-null equal id's; {@code false} otherwise.
	 */
	@Override
	public boolean equals(final Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResourceModel that = (ResourceModel) o;

		return id != null && that.id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {

		return id != null ? id.hashCode() : 0;
	}
	
	private static final class WrapperMap extends HashMap<String, Object>{

		private static final long serialVersionUID = -1384867122460316824L;
		private final Map wrapped;

		private WrapperMap(final Map wrapped) {

			this.wrapped = wrapped;
		}

		@Override
		public void clear() {

			wrapped.clear();
		}

		public Object compute(final String key, final BiFunction remappingFunction) {

			return wrapped.compute(key, remappingFunction);
		}

		public Object computeIfAbsent(final String key, final Function mappingFunction) {

			return wrapped.computeIfAbsent(key, mappingFunction);
		}

		public Object computeIfPresent(final String key, final BiFunction remappingFunction) {

			return wrapped.computeIfPresent(key, remappingFunction);
		}

		@Override
		public boolean containsKey(final Object key) {

			return wrapped.containsKey(key);
		}

		@Override
		public boolean containsValue(final Object value) {

			return wrapped.containsValue(value);
		}

		@Override
		public Set<Entry<String, Object>> entrySet() {

			return wrapped.entrySet();
		}

		@Override
		public boolean equals(final Object o) {

			return wrapped.equals(o);
		}

		public void forEach(final BiConsumer action) {

			wrapped.forEach(action);
		}

		@Override
		public Object get(final Object key) {

			return wrapped.get(key);
		}

		@Override
		public Object getOrDefault(final Object key, final Object defaultValue) {

			return wrapped.getOrDefault(key, defaultValue);
		}

		@Override
		public int hashCode() {

			return wrapped.hashCode();
		}

		@Override
		public boolean isEmpty() {

			return wrapped.isEmpty();
		}

		@Override
		public Set keySet() {

			return wrapped.keySet();
		}

		public Object merge(final String key, final Object value, final BiFunction remappingFunction) {

			return wrapped.merge(key, value, remappingFunction);
		}

		public Object put(final String key, final Object value) {

			return wrapped.put(key, value);
		}

		public void putAll(final Map m) {

			wrapped.putAll(m);
		}

		public Object putIfAbsent(final String key, final Object value) {

			return wrapped.putIfAbsent(key, value);
		}

		@Override
		public Object remove(final Object key) {

			return wrapped.remove(key);
		}

		@Override
		public boolean remove(final Object key, final Object value) {

			return wrapped.remove(key, value);
		}

		public boolean replace(final String key, final Object oldValue, final Object newValue) {

			return wrapped.replace(key, oldValue, newValue);
		}

		public Object replace(final String key, final Object value) {

			return wrapped.replace(key, value);
		}

		public void replaceAll(final BiFunction function) {

			wrapped.replaceAll(function);
		}

		@Override
		public int size() {

			return wrapped.size();
		}

		@Override
		public Collection values() {

			return wrapped.values();
		}
	}
}
