/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import javax.validation.constraints.NotNull;

/**
 * Model representation for product identifiers.
 * 
 * @author Dominique Guinard (domguinard)
 * @deprecated Use Map(String,String) instead. Use something for a single entry.
 */
public class ProductIdentifier {

	/**
	 * Type of identifier, examples are: model, mpn, upc, isbn, epc, issn, ean,
	 * jan, sn, vin, sku
	 */
	@NotNull
	private String type;

	@NotNull
	private String value;

	/**
	 * Creates a new instance of {@link ProductIdentifier}.
	 */
	public ProductIdentifier() {
		/* Allow instantiation through reflection. */
	}

	/**
	 * Creates a new instance of {@link ProductIdentifier} with the provided
	 * {@code type} and {@code value}.
	 * 
	 * @param type
	 *            e.g., GTIN
	 * @param value
	 *            e.g., 00843163050105
	 */
	public ProductIdentifier(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toLowerCase();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
