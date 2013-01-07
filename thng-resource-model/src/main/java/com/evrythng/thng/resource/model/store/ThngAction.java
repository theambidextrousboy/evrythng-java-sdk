package com.evrythng.thng.resource.model.store;

import javax.validation.ValidationException;

import com.evrythng.thng.resource.model.core.TemporalResourceModel;
import com.evrythng.thng.resource.model.utils.ObjectUtils;

/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
/**
 * Model class for a thng action. NOTE this class is not abstract because Jersey
 * must construct it when receiving a request.
 * 
 * @author Michel Yerly (my)
 * 
 */
public class ThngAction extends TemporalResourceModel {

	private String thng;
	private String product;
	private String user;
	private Location location;

	public ThngAction() {
	}

	protected ThngAction shallowCopyFrom(ThngAction action) {
		this.thng = action.thng;
		this.product = action.product;
		this.user = action.user;
		this.location = action.location;
		this.id = action.id;
		this.createdAt = action.createdAt;
		this.setCustomFields(action.getCustomFields());
		this.setTimestamp(action.getTimestamp());
		return this;
	}

	/**
	 * When overridden, this method must create an instance of the derived class
	 * and copy everything from the action passed in parameter and return it.
	 * 
	 * NOTE: this method is not abstract because this class cannot be abstract.
	 * 
	 * @param action
	 * @return
	 */
	public ThngAction downCastCopy(ThngAction action) {
		throw new RuntimeException("No implementation.");
	}

	/**
	 * @return the thngId
	 */
	public String getThng() {
		return thng;
	}

	/**
	 * @param thng
	 *            the thngId to set
	 */
	public void setThng(String thng) {
		this.thng = thng;
	}

	/**
	 * @return the userId
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the userId to set
	 */
	public void setUserId(String user) {
		this.user = user;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	public static class Location {

		private Double latitude;
		private Double longitude;
		private String place;

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public String getPlace() {
			return place;
		}

		public void setPlace(String place) {
			this.place = place;
		}

		public void validate() {
			boolean hasOne = false;
			if (latitude != null && longitude != null) {
				hasOne = true;
				if (-90 > latitude || latitude > 90 || -180 > longitude || longitude > 180) {
					throw new ValidationException("Latitude or longitude not in range.");
				}
			}

			if (place != null) {
				hasOne = true;
			}

			if (!hasOne) {
				throw new ValidationException("Please provide either a place or a latitude and longitude.");
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Location)) {
				return false;
			}
			Location oLoc = (Location) o;
			return ObjectUtils.nullSafeEquals(latitude, oLoc.latitude) && ObjectUtils.nullSafeEquals(longitude, oLoc.longitude) && ObjectUtils.nullSafeEquals(place, oLoc.place);
		}

		@Override
		public int hashCode() {
			int result = latitude != null ? latitude.hashCode() : 0;
			result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
			result = 31 * result + (place != null ? place.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "Location{" + latitude + ", " + longitude + ", place='" + place + '\'' + '}';
		}
	}
}
