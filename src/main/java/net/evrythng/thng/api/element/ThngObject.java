package net.evrythng.thng.api.element;

import net.evrythng.thng.api.json.ContentObject;

import org.json.JSONException;

public class ThngObject extends ContentObject {


	public ThngObject(String id, String description, Boolean isPublic) throws JSONException {
		super("thing", description, isPublic); // FIXME: change to 'thng' when API has been updated
		
		// Add specific attributes:
		this.put("identifier", id);
	}

	public String getId() throws JSONException {
		return (String) this.content.get("id");
	}

	public void setId(String id) throws JSONException {
		this.content.put("id", id);
	}
}
