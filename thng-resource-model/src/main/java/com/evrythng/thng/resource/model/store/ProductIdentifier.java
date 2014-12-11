/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

/**
 * Model representation for product identifiers.
 *
 * @deprecated Use Map(String,String) instead. Use something for a single entry.
 */
@Deprecated
public class ProductIdentifier {

	/**
	 * Type of identifier, examples are: model, mpn, upc, isbn, epc, issn, ean,
	 * jan, sn, vin, sku
	 */
	private String type;
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
	 * @param type  e.g., GTIN
	 * @param value e.g., 00843163050105
	 */
	public ProductIdentifier(final String type, final String value) {

		this.type = type;
		this.value = value;
	}

	public String getType() {

		return type;
	}

	public void setType(final String type) {

		this.type = type.toLowerCase();
	}

	public String getValue() {

		return value;
	}

	public void setValue(final String value) {

		this.value = value;
	}
}
