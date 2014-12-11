package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Interface for a object that can be geographically positioned
 */
public interface Positionable extends Traceable {

	GeoJsonPoint getPosition();

	void setPosition(GeoJsonPoint position);
}
