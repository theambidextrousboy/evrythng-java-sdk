package net.evrythng.thng.api.json;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ContentObject extends JSONModel {

	protected JSONObject content = new JSONObject();

	public ContentObject(String type, String description, Boolean isPublic) throws JSONException {
		this.model.put(type, this.content);
		this.content.put("description", description);
		this.content.put("is_public", isPublic);
	}
	
	/**
	 * @param key
	 * @param value
	 * @throws JSONException
	 */
	protected void put(String key, String value) throws JSONException {
		this.content.put(key, value);
	}
	
	public String getDescription() throws JSONException {
		return (String) this.content.get("description");
	}

	public void setDescription(String description) throws JSONException {
		this.content.put("description", description);
	}

	public Boolean getIsPublic() throws JSONException {
		return Boolean.valueOf(this.content.get("isPublic").toString());
	}

	public void setIsPublic(Boolean isPublic) throws JSONException {
		this.content.put("isPublic", isPublic);
	}
	
}
