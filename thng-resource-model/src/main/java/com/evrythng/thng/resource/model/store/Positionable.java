package com.evrythng.thng.resource.model.store;

import com.evrythng.thng.resource.model.store.geojson.GeoJsonPoint;

/**
 * Interface for a object that can be geographically positioned
 * 
 */
public interface Positionable extends Traceable {

	public GeoJsonPoint getPosition();

	public void setPosition(GeoJsonPoint position);

}
