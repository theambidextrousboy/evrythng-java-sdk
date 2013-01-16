package com.evrythng.java.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.store.Location;
import com.evrythng.thng.resource.model.store.Property;
import com.evrythng.thng.resource.model.store.PropertyValue;
import com.evrythng.thng.resource.model.store.Redirector;
import com.evrythng.thng.resource.model.store.Thng;
import com.evrythng.thng.resource.model.store.ThngActionCheckin;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /thngs} endpoint of the EVRYTHNG Engine API.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class ThngService extends EvrythngServiceBase {

	public static final String PATH_THNGS = "/thngs";
	public static final String PATH_THNG = PATH_THNGS + "/%s";

	public static final String PATH_THNG_PROPERTIES = PATH_THNG + "/properties";
	public static final String PATH_THNG_PROPERTY = PATH_THNG_PROPERTIES + "/%s";

	public static final String PATH_THNG_LOCATION = PATH_THNG + "/location";

	public static final String PATH_THNG_ACTIONS_CHECKINS = PATH_THNG + "/actions/checkins";
	public static final String PATH_THNG_ACTIONS_CHECKIN = PATH_THNG_ACTIONS_CHECKINS + "/%s";

	public static final String PATH_THNG_REDIRECTOR = PATH_THNG + "/redirector";
	public static final String PATH_THNG_REDIRECTOR_QR = PATH_THNG_REDIRECTOR + "/qr";

	public ThngService(ApiConfiguration config) {
		super(config);
	}

	/* ***** Thng ***** */

	/**
	 * Creates a new {@link Thng}.
	 * 
	 * POST {@value #PATH_THNGS}
	 * 
	 * @param thng
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngCreator(Thng thng) throws EvrythngClientException {
		return post(PATH_THNGS, thng, new TypeReference<Thng>() {
		});
	}

	/**
	 * Retrieves the lst updates {@link Thng} resources.
	 * 
	 * GET {@value #PATH_THNGS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Thng>> thngsReader() throws EvrythngClientException {
		return get(PATH_THNGS, new TypeReference<List<Thng>>() {
		});
	}

	/* ***** /thngs/{id} ***** */

	/**
	 * Retrieves the referenced {@link Thng}.
	 * 
	 * GET {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG, thngId), new TypeReference<Thng>() {
		});
	}

	/**
	 * Updates the referenced {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @param thng
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Thng> thngUpdater(String thngId, Thng thng) throws EvrythngClientException {
		return put(String.format(PATH_THNG, thngId), thng, new TypeReference<Thng>() {
		});
	}

	/**
	 * Deletes the referenced {@link Thng}.
	 * 
	 * DELETE {@value #PATH_THNG}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG, thngId));
	}

	/* ***** /thngs/{id}/properties ***** */

	/**
	 * Creates a new {@link Property} on the referenced {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @see #thngsCreator(List)
	 * @param thngId
	 * @param property
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String thngId, Property property) throws EvrythngClientException {
		return propertiesCreator(thngId, Arrays.asList(property));
	}

	/**
	 * Creates multiple {@link Property} resources on the referenced
	 * {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @param properties
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesCreator(String thngId, List<Property> properties) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTIES, thngId), properties, new TypeReference<List<Property>>() {
		});
	}

	/**
	 * Retrieves the last updated {@link Property} resources from the referenced
	 * {@link Thng}.
	 * 
	 * GET {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Property>> propertiesReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTIES, thngId), new TypeReference<List<Property>>() {
		});
	}

	/**
	 * Deletes all {@link Property} resources from the referenced {@link Thng}.
	 * 
	 * DELETE {@value #PATH_THNG_PROPERTIES}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertiesDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_PROPERTIES, thngId));
	}

	/* ***** /thngs/{id}/properties/{key} ***** */

	/**
	 * Retrieves the last values of the {@link Property} named {@code key} from
	 * the referenced {@link Thng}.
	 * 
	 * GET {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyReader(String thngId, String key) throws EvrythngClientException {
		return get(String.format(PATH_THNG_PROPERTY, thngId, key), new TypeReference<List<PropertyValue>>() {
		});
	}

	/**
	 * Updates the {@link Property} named {@code key} of the referenced
	 * {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param thngId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, String value) throws EvrythngClientException {
		return propertyUpdater(thngId, key, new PropertyValue(value));
	}

	/**
	 * Updates the {@link Property} named {@code key} of the referenced
	 * {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @see #propertiesCreator(String, Property)
	 * @param thngId
	 * @param key
	 * @param value
	 * @param timestamp
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, String value, long timestamp) throws EvrythngClientException {
		return propertyUpdater(thngId, key, new PropertyValue(value, timestamp));
	}

	/**
	 * Updates the {@link Property} named {@code key} of the referenced
	 * {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @param value
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<PropertyValue>> propertyUpdater(String thngId, String key, PropertyValue value) throws EvrythngClientException {
		return put(String.format(PATH_THNG_PROPERTY, thngId, key), Arrays.asList(value), new TypeReference<List<PropertyValue>>() {
		});
	}

	/**
	 * Deletes the {@link Property} named {@code key} from the referenced
	 * {@link Thng}.
	 * 
	 * DELETE {@value #PATH_THNG_PROPERTY}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> propertyDeleter(String thngId, String key) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_PROPERTY, thngId, key));
	}

	/* ***** /thngs/{id}/location ***** */

	/**
	 * Retrieves the last {@link Location} resources from the referenced
	 * {@link Thng}.
	 * 
	 * GET {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_LOCATION, thngId), new TypeReference<List<Location>>() {
		});
	}

	/**
	 * Updates the current {@link Location} of the referenced {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_LOCATION}
	 * 
	 * @see #locationUpdater(String, List)
	 * @param thngId
	 * @param location
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationUpdater(String thngId, Location location) throws EvrythngClientException {
		return locationUpdater(thngId, Arrays.asList(location));
	}

	/**
	 * Updates the referenced {@link Thng} with multiple {@link Location}
	 * resources.
	 * 
	 * PUT {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @param locations
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Location>> locationUpdater(String thngId, List<Location> locations) throws EvrythngClientException {
		return put(String.format(PATH_THNG_LOCATION, thngId), locations, new TypeReference<List<Location>>() {
		});
	}

	/**
	 * Deletes the {@link Location} of the referenced {@link Thng}.
	 * 
	 * DELETE {@value #PATH_THNG_LOCATION}
	 * 
	 * @param thngId
	 * @param key
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> locationDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_LOCATION, thngId));
	}

	/* ***** /thngs/{id}/actions/checkins ***** */

	/**
	 * Checks in into the referenced {@link Thng}, i.e. claims the "ownership"
	 * of the {@link Thng}.
	 * 
	 * POST {@value #PATH_THNG_ACTIONS_CHECKINS}
	 * 
	 * @param thngId
	 * @param checkin
	 * @param singleCheckin
	 *            If false, verifies that the thng has no checkin yet.
	 *            Otherwise, throws a ConflictException.
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<ThngActionCheckin> checkinAdder(String thngId, ThngActionCheckin checkin, boolean singleCheckin) throws EvrythngClientException {
		return put(String.format(PATH_THNG_ACTIONS_CHECKINS, thngId), checkin, new TypeReference<ThngActionCheckin>() {
		}).queryParam("single", String.valueOf(singleCheckin));
	}

	/**
	 * Gets all the checkins that occured for the referenced {@link Thng}.
	 * 
	 * GET {@value #PATH_THNG_ACTIONS_CHECKINS}
	 * 
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<ThngActionCheckin>> checkinsReader(String thngId) throws EvrythngClientException {
		return this.get(String.format(PATH_THNG_ACTIONS_CHECKINS, thngId), new TypeReference<List<ThngActionCheckin>>() {
		});
	}

	/**
	 * Removes all checkins from the referenced {@link Thng}.
	 * 
	 * @param thngId
	 * @param checkinId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> checkinsRemover(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_ACTIONS_CHECKINS, thngId));
	}

	/**
	 * Checks in into the referenced {@link Thng}, i.e. claim the "ownership" of
	 * the {@link Thng}. Allows
	 * multiple checkins
	 * 
	 * POST {@value #PATH_THNG_ACTIONS_CHECKINS}
	 * 
	 * @param thngId
	 * @param checkin
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<ThngActionCheckin> checkinAdder(String thngId, ThngActionCheckin checkin) throws EvrythngClientException {
		return checkinAdder(thngId, checkin, false);
	}

	/**
	 * Updates an existing checkin from the referenced {@link Thng}.
	 * 
	 * PUT {@value #PATH_THNG_ACTIONS_CHECKINS}
	 * 
	 * @param thngId
	 * @param id
	 * @param update
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<ThngActionCheckin> checkinUpdater(String thngId, String id, ThngActionCheckin update) throws EvrythngClientException {
		return put(String.format(PATH_THNG_ACTIONS_CHECKIN, thngId, id), update, new TypeReference<ThngActionCheckin>() {
		});
	}

	/**
	 * Removes checkins from the referenced {@link Thng}.
	 * 
	 * @param thngId
	 * @param checkinId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> checkinRemover(String thngId, String checkinId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_ACTIONS_CHECKIN, thngId, checkinId));
	}

	/**
	 * Retrieves a checkin give provided {@code id} from the referenced
	 * {@link Thng}.
	 * 
	 * @param thngId
	 * @param checkinId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<ThngActionCheckin> checkinReader(String thngId, String checkinId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_ACTIONS_CHECKIN, thngId, checkinId), new TypeReference<ThngActionCheckin>() {
		});
	}

	/* ***** /thngs/{id}/redirector ***** */

	/**
	 * Creates a redirector for the Thng.
	 */
	public Builder<Redirector> redirectorCreator(String thngId, Redirector redirection) throws EvrythngClientException {
		return post(String.format(PATH_THNG_REDIRECTOR, thngId), redirection, new TypeReference<Redirector>() {
		});
	}

	/**
	 * Retrieves the redirector for the Thng.
	 */
	public Builder<Redirector> redirectorReader(String thngId) throws EvrythngClientException {
		return get(String.format(PATH_THNG_REDIRECTOR, thngId), new TypeReference<Redirector>() {
		});
	}

	/**
	 * Deletes the redirector for the Thng.
	 */
	public Builder<Boolean> redirectorDeleter(String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_THNG_REDIRECTOR, thngId));
	}

	/**
	 * Updates the redirector for the Thng.
	 */
	public Builder<Redirector> redirectorUpdater(String thngId, Redirector redirection) throws EvrythngClientException {
		return put(String.format(PATH_THNG_REDIRECTOR, thngId), redirection, new TypeReference<Redirector>() {
		});
	}
}
