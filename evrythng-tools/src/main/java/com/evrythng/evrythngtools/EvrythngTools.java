/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

package com.evrythng.evrythngtools;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.evrythng.evrythngtools.commands.AbstractParentCommand;
import com.evrythng.evrythngtools.commands.AppStatusCommand;
import com.evrythng.evrythngtools.commands.HelpCommand;
import com.evrythng.evrythngtools.commands.InstancesCommand;
import com.evrythng.evrythngtools.commands.MongoPrimaryCommand;

/**
 * TODO: comment this class
 */
public class EvrythngTools {

	private static class Arguments {

	}

	private static JCommander jCommander;

	public static class MainCommand extends AbstractParentCommand {

		protected MainCommand() {

			super("main");
			addSubCommand(new HelpCommand());
			addSubCommand(new MongoPrimaryCommand());
			addSubCommand(new AppStatusCommand());
			addSubCommand(new InstancesCommand());
		}
	}

	public static void main(final String[] args) {

		Arguments arguments = new Arguments();

		jCommander = new JCommander(arguments);

		MainCommand mainCommand = new MainCommand();
		mainCommand.configure(jCommander);

		try {
			jCommander.parse(args);
		} catch (MissingCommandException e) {
			jCommander.usage();
			System.exit(-1);
		}
		if (jCommander.getParsedCommand() == null) {
			jCommander.usage();
			System.exit(-1);
		}

		JCommander cmdJCommander = jCommander.getCommands().get(jCommander.getParsedCommand());
		Command cmd = (Command) cmdJCommander.getObjects().get(0);

		cmd.execute();
	}

	public static void help() {

		jCommander.usage();
	}
}
