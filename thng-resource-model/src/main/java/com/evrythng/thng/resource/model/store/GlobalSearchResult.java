package com.evrythng.thng.resource.model.store;

import java.io.Serializable;
import java.util.List;

/**
 * Search results.
 */
public class GlobalSearchResult implements Serializable {

	private static final long serialVersionUID = 2580537661302182631L;
	/**
	 * Found thngs
	 */
	private List<Thng> thngs;
	private Long thngsResultCount;
	/**
	 * Found products
	 */
	private List<Product> products;
	private Long productsResultCount;
	/**
	 * Found collections
	 */
	private List<Collection> collections;
	private Long collectionsResultCount;
	/**
	 * Found places
	 */
	private List<Place> places;
	private Long placesResultCount;

	public List<Thng> getThngs() {

		return thngs;
	}

	public void setThngs(final List<Thng> thngs) {

		this.thngs = thngs;
	}

	public Long getThngsResultCount() {

		return thngsResultCount;
	}

	public void setThngsResultCount(final Long thngsResultCount) {

		this.thngsResultCount = thngsResultCount;
	}

	public List<Product> getProducts() {

		return products;
	}

	public void setProducts(final List<Product> products) {

		this.products = products;
	}

	public Long getProductsResultCount() {

		return productsResultCount;
	}

	public void setProductsResultCount(final Long productsResultCount) {

		this.productsResultCount = productsResultCount;
	}

	public List<Collection> getCollections() {

		return collections;
	}

	public void setCollections(final List<Collection> collections) {

		this.collections = collections;
	}

	public Long getCollectionsResultCount() {

		return collectionsResultCount;
	}

	public void setCollectionsResultCount(final Long collectionsResultCount) {

		this.collectionsResultCount = collectionsResultCount;
	}

	public List<Place> getPlaces() {

		return places;
	}

	public void setPlaces(final List<Place> places) {

		this.places = places;
	}

	public Long getPlacesResultCount() {

		return placesResultCount;
	}

	public void setPlacesResultCount(final Long placesResultCount) {

		this.placesResultCount = placesResultCount;
	}
}
