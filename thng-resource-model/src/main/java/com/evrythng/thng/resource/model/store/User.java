/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>users</em>.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class User extends DurableResourceModel {

	/**
	 * The user's unique email address.
	 */
	@NotNull
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotEmpty
	// Note: @Size below is used only for validation. Stored passwords are 255 characters long.
	@Size(min = 6, max = 30)
	private String password;

	/**
	 * The user's first name.
	 */
	@NotNull
	@NotBlank
	private String firstName;

	/**
	 * The user's last name.
	 */
	@NotNull
	@NotBlank
	private String lastName;

	/**
	 * The user's timezone offset from <strong>UTC</strong>.
	 * 
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets">http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets</a>
	 */
	private String timezone;

	/**
	 * The user's locale.
	 * 
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/ISO_639">http://en.wikipedia.org/wiki/ISO_639</a>
	 */
	private String locale;

	/**
	 * A picture of the user encoded in a Base64 string.
	 * TODO: Test this with base 64 strings!
	 */
	private String photo;

	/**
	 * Tells if the user can log in or not.
	 */
	private Boolean canLogin = true;

	private Map<String, Object> attributes;

	/* *** Getters / Setters *** */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean isCanLogin() {
		// TODO: Explicitly changes null to true. Think of a better approach.
		return canLogin == null || canLogin;
	}

	public void setCanLogin(Boolean canLogin) {
		this.canLogin = canLogin;
	}
}
