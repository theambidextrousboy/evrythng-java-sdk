/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.beust.jcommander.JCommander;
import com.evrythng.evrythngtools.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public abstract class AbstractParentCommand extends AbstractCommand {

	private final List<Command> subCommands = new ArrayList<>();

	protected AbstractParentCommand(final String name, final String... aliases) {

		super(name, aliases);

	}

	protected void addSubCommand(final Command command) {

		if (commander() != null) {
			throw new IllegalStateException();
		}
		subCommands.add(command);
	}

	@Override
	public void configure(final JCommander commander) {

		super.configure(commander);

		for (Command cmd : subCommands) {
			List<String> strings = cmd.getNames().subList(1, cmd.getNames().size());
			JCommander subCmd = addCommand(commander, cmd.getNames().get(0), cmd, strings.toArray(new String[strings.size()]));
			cmd.configure(subCmd);
		}
	}

	@Override
	public final boolean execute() {

		Command cmd = (Command) commander().getCommands().get(commander().getParsedCommand()).getObjects().get(0);
		return cmd.execute();
	}

	private static JCommander addCommand(final JCommander parentCommand, final String commandName, final Object commandObject,
	                                     final String... aliases) {

		parentCommand.addCommand(commandName, commandObject, aliases);
		return parentCommand.getCommands().get(commandName);
	}
}
