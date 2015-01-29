/*
* (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
* www.evrythng.com
*/
package com.evrythng.evrythngtools.commands;

import com.evrythng.evrythngtools.EvrythngTools;

/**
 * TODO: comment this class
 */
public class HelpCommand extends AbstractCommand {

	public HelpCommand() {

		super("help", "-h", "--help");
	}

	@Override
	public boolean execute() {

		EvrythngTools.help();
		return true;
	}
}
