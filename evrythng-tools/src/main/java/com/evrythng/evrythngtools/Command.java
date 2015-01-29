/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools;

import com.beust.jcommander.JCommander;

import java.util.List;

/**
 * TODO: comment this class
 */
public interface Command {

	List<String> getNames();

	void configure(JCommander commander);

	boolean execute();
}
