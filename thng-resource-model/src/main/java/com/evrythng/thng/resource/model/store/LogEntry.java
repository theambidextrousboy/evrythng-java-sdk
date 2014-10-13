/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

// TODO make filterable
/**
 * Model representation for <em>log entries</em>.
 **/
public final class LogEntry extends TemporalResourceModel {

	public static enum LogLevel {

		DEBUG("debug"), INFO("info"), WARN("warn"), ERROR("error");

		private static Map<String, LogLevel> names = new HashMap<>();

		private final String name;

		private LogLevel(final String name) {
			this.name = name;
		}

		static {
			names = EnumUtils.createNames(values());
		}

		@JsonValue
		@Override
		public String toString() {
			return name;
		}

		@JsonCreator
		public static LogLevel fromString(String name) {
			return EnumUtils.fromString(names, name);
		}
	}

	private static final long serialVersionUID = 1L;

	private LogLevel logLevel;
	private String message;
	private String app;

	public void setLogLevel(final LogLevel logLevel) {

		this.logLevel = logLevel;
	}

	public void setMessage(final String message) {

		this.message = message;
	}

	public void setApp(final String app) {

		this.app = app;
	}

	public LogLevel getLogLevel() {

		return logLevel;
	}

	public String getMessage() {

		return message;
	}

	public String getApp() {

		return app;
	}

	@Override
	public String toString() {

		return "LogEntry{" + "logLevel=" + logLevel + ", message='" + message + '\'' + ", app='" + app + '\'' + ", createdAt='" + getCreatedAt() + '\'' + '}';
	}
}
