package net.evrythng.thng.api.model;

/**
 * 
 * 
 * @author almeidap
 *
 */
public class ThngCollection extends Model {
	
	private String id;
	
	private String name;
	private String description;
	private Boolean isPublic;
	
	/**
	 * Creates a new empty instance of {@link ThngCollection}.
	 * This also allows dynamic instantiation.
	 */
	public ThngCollection() {
	}

	public ThngCollection(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}
	
	public ThngCollection(String name, String description, Boolean isPublic) {
		this(name, description);
		this.setIsPublic(isPublic);
	}
	
	@Override
	public String toString() {
		return super.toString() + " [name=" + this.getName() + "]";
	}
	
	/* *** GETTTERS / SETTERS *** */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

}
