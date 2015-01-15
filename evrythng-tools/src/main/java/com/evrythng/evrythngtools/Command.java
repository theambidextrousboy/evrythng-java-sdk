/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools;

import java.util.List;

/**
 * TODO: comment this class
 */
public interface Command {

	List<String> getNames();

	boolean execute(List<String> parameters);
}
