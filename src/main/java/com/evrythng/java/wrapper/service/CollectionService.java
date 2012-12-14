package com.evrythng.java.wrapper.service;

import java.util.Arrays;
import java.util.List;

import com.evrythng.java.wrapper.core.EvrythngApiBuilder.Builder;
import com.evrythng.java.wrapper.core.EvrythngServiceBase;
import com.evrythng.java.wrapper.exception.EvrythngClientException;
import com.evrythng.thng.commons.config.ApiConfiguration;
import com.evrythng.thng.resource.model.store.Collection;
import com.evrythng.thng.resource.model.store.Thng;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Service wrapper for the {@code /collections} endpoint of the EVRYTHNG API.
 * 
 * @author Pedro De Almeida (almeidap)
 */
public class CollectionService extends EvrythngServiceBase {

	public static final String PATH_COLLECTIONS = "/collections";
	public static final String PATH_COLLECTION = PATH_COLLECTIONS + "/%s";
	public static final String PATH_COLLECTION_THNGS = PATH_COLLECTION + "/thngs";
	public static final String PATH_COLLECTION_THNG = PATH_COLLECTION_THNGS + "/%s";

	public CollectionService(ApiConfiguration config) {
		super(config);
	}

	/* ***** /collections ***** */

	/**
	 * Creates a new {@link Collection}.
	 * 
	 * POST {@value #PATH_COLLECTIONS}
	 * 
	 * @param collection the instant holding the {@link Collection} resource data
	 * @return a {@link Collection} creator
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionCreator(Collection collection) throws EvrythngClientException {
		return post(PATH_COLLECTIONS, collection, new TypeReference<Collection>() {
		});
	}

	/**
	 * Retrieves the last updated {@link Collection} resources.
	 * 
	 * GET {@value #PATH_COLLECTIONS}
	 * 
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Collection>> collectionsReader() throws EvrythngClientException {
		return get(PATH_COLLECTIONS, new TypeReference<List<Collection>>() {
		});
	}

	/* ***** /collections/{id} ***** */

	/**
	 * Retrieves the referenced {@link Collection}.
	 * 
	 * GET {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionReader(String collectionId) throws EvrythngClientException {
		return get(String.format(PATH_COLLECTION, collectionId), new TypeReference<Collection>() {
		});
	}

	/**
	 * Updates the referenced {@link Collection}.
	 * 
	 * PUT {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @param collection
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Collection> collectionUpdater(String collectionId, Collection collection) throws EvrythngClientException {
		return put(String.format(PATH_COLLECTION, collectionId), collection, new TypeReference<Collection>() {
		});
	}

	/**
	 * Deletes the referenced {@link Collection}.
	 * 
	 * DELETE {@value #PATH_COLLECTION}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> collectionDeleter(String collectionId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION, collectionId));
	}

	/* ***** /collections/{id}/thngs ***** */

	/**
	 * Retrieves the last updated {@link Thng} resources linked with the referenced {@link Collection}.
	 * 
	 * GET {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<Thng>> thngsReader(String collectionId) throws EvrythngClientException {
		return get(String.format(PATH_COLLECTION_THNGS, collectionId), new TypeReference<List<Thng>>() {
		});
	}

	/**
	 * Adds the referenced {@link Thng} to the provided {@link Collection}.
	 * 
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @see #thngsAdder(String, List)
	 * @param collectionId
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<String>> thngAdder(String collectionId, String thngId) throws EvrythngClientException {
		return thngsAdder(collectionId, Arrays.asList(thngId));
	}

	/**
	 * Adds the referenced {@link Thng} resources to the provided {@link Collection}.
	 * 
	 * PUT {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @param thngs
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<List<String>> thngsAdder(String collectionId, List<String> thngs) throws EvrythngClientException {
		return put(String.format(PATH_COLLECTION_THNGS, collectionId), thngs, new TypeReference<List<String>>() {
		});
	}

	/* ***** /collections/{id}/thngs/{id} ***** */

	/**
	 * Removes the referenced {@link Thng} from the provided {@link Collection}.
	 * 
	 * DELETE {@value #PATH_COLLECTION_THNG}
	 * 
	 * @param collectionId
	 * @param thngId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngRemover(String collectionId, String thngId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION_THNG, collectionId, thngId));
	}

	/**
	 * Removes all {@link Thng} resources from the provided {@link Collection}.
	 * 
	 * DELETE {@value #PATH_COLLECTION_THNGS}
	 * 
	 * @param collectionId
	 * @return
	 * @throws EvrythngClientException
	 */
	public Builder<Boolean> thngsRemover(String collectionId) throws EvrythngClientException {
		return delete(String.format(PATH_COLLECTION_THNGS, collectionId));
	}
}
