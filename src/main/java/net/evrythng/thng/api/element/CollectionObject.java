package net.evrythng.thng.api.element;

import net.evrythng.thng.api.json.ContentObject;

import org.json.JSONException;

public class CollectionObject extends ContentObject {

	public CollectionObject(String name, String description, Boolean isPublic) throws JSONException {
		super("collection", description, isPublic);
		
		// Add specific attributes:
		this.put("name", name);
	}

	public String getName() throws JSONException {
		return (String) this.content.get("name");
	}

	public void setName(String name) throws JSONException {
		this.content.put("name", name);
	}
}
