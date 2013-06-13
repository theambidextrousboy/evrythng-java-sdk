package com.evrythng.thng.resource.model.store;

import java.util.List;

/**
 * Search results.
 * 
 * @author Victor Sergienko (victor)
 * @author Michel Yerly (my)
 */
public class GlobalSearchResult {

	/**
	 * Found thngs
	 */
	private List<Thng> thngs;

	/**
	 * Found products
	 */
	private List<Product> products;

	/**
	 * Found collections
	 */
	private List<Collection> collections;

	/**
	 * @return the thngs
	 */
	public List<Thng> getThngs() {
		return thngs;
	}

	/**
	 * @param thngs
	 *            the thngs to set
	 */
	public void setThngs(List<Thng> thngs) {
		this.thngs = thngs;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * @return the collections
	 */
	public List<Collection> getCollections() {
		return collections;
	}

	/**
	 * @param collections
	 *            the collections to set
	 */
	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}
}
