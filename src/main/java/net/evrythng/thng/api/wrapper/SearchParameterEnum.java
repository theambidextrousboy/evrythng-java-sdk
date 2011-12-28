package net.evrythng.thng.api.wrapper;

public enum SearchParameterEnum {

	QUERY("q"), GEOCODE("geocode", false), THNGSPACE("thngspace");
	
	private String name;
	private boolean urlEncoded = true;

	private SearchParameterEnum(String name) {
		this.name = name;
	}
	
	private SearchParameterEnum(String name, boolean urlEncoded) {
		this(name);
		this.urlEncoded = urlEncoded;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public boolean isUrlEncoded() {
		return urlEncoded;
	}
	
	public String getParameterName() {
		return name;
	}
}
