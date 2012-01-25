/*
 * (c) Copyright 2012 Evrythng Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.search;

public enum SearchParameter {

	QUERY("q"), GEOCODE("geocode"), TYPE("type");

	private String name;

	private SearchParameter(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public enum Type {

		MINE("mine"), ALL("all");

		private String name;

		private Type(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		public String getName() {
			return name;
		}
	}
}
