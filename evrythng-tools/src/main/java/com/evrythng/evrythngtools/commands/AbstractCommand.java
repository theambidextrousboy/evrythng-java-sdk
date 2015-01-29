/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.beust.jcommander.JCommander;
import com.evrythng.evrythngtools.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 */
public abstract class AbstractCommand implements Command {

	private JCommander commander = null;
	private final List<String> names = new ArrayList<>();

	protected AbstractCommand(final String name, final String... aliases) {

		if (name == null) {
			throw new NullPointerException();
		}
		names.add(name);

		if (aliases != null) {

			for (String alias : aliases) {
				if (alias == null) {
					throw new NullPointerException();
				}
				names.add(alias);
			}
		}
	}

	@Override
	public final List<String> getNames() {

		return Collections.unmodifiableList(names);
	}

	@Override
	public void configure(final JCommander commander) {

		if (this.commander != null) {
			throw new IllegalStateException();
		}
		this.commander = commander;
	}

	protected final JCommander commander() {

		return commander;
	}
}
