/*
 * (c) Copyright 2014 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for <em>operator</em>.
 */
public class Operator extends AbstractUser {

	private static final long serialVersionUID = 2282241536928517609L;
	private Birthday birthday;
	private Gender   gender;

	public Birthday getBirthday() {

		return birthday;
	}

	public void setBirthday(final Birthday birthday) {

		this.birthday = birthday;
	}

	public Gender getGender() {

		return gender;
	}

	public void setGender(final Gender gender) {

		this.gender = gender;
	}
}
