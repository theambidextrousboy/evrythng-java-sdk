/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.core.DurableResourceModel;

/**
 * Model representation for <em>users</em>.
 */
public class User extends DurableResourceModel {

	/**
	 * The user's unique email address.
	 */
	private String email;

	private String password;

	/**
	 * The user's first name.
	 */
	private String firstName;

	/**
	 * The user's last name.
	 */
	private String lastName;

	/**
	 * The user's birthday as http://en.wikipedia.org/wiki/ISO_8601
	 * i.e., YYYY-MM-DD
	 */
	private String birthday;

	/**
	 * The user's timezone offset from <strong>UTC</strong>.
	 * 
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets">http://en.wikipedia.org/wiki/Time_zones#List_of_UTC_offsets</a>
	 */
	private String timezone;

	/**
	 * The user's locale.
	 * The basic format is ''ll_CC'', where ''ll'' is a two-letter language
	 * code,
	 * and ''CC'' is a two-letter country code. For instance, 'en_US' represents
	 * US English.
	 * 
	 * @see <a
	 *      href="https://developers.facebook.com/docs/internationalization/">https://developers.facebook.com/docs/internationalization/</a>
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
	private Boolean canLogin;

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
		return canLogin;
	}

	public void setCanLogin(Boolean canLogin) {
		this.canLogin = canLogin;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
