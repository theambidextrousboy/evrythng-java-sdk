/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.core;

import java.util.Comparator;

/**
 * Abstract model representation for temporal resources.
 * 
 * @author Pedro De Almeida (almeidap)
 * @author Michel Yerly (my)
 * 
 **/
public abstract class TemporalResourceModel extends ResourceModel {

	public static final Comparator<TemporalResourceModel> REVERSE_TIME_COMPARATOR = new Comparator<TemporalResourceModel>() {

		@Override
		public int compare(TemporalResourceModel arg0, TemporalResourceModel arg1) {
			if (arg0.getTimestamp() > arg1.getTimestamp()) {
				return -1;
			} else if (arg0.getTimestamp() < arg1.getTimestamp()) {
				return 1;
			} else {
				return 0;
			}
		}
	};

	private Long timestamp;

	protected TemporalResourceModel() {
	}

	protected TemporalResourceModel(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}