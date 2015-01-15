/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.evrythng.evrythngtools.Command;
import com.evrythng.evrythngtools.EvrythngTools;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: comment this class
 */
public class HelpCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getNames() {

		return Arrays.asList("help", "-h", "h", "--help");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean execute(List<String> parameters) {

		EvrythngTools.help();
		return true;
	}

}
