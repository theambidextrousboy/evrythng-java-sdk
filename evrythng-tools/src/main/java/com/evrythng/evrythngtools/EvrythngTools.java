package com.evrythng.evrythngtools;

import com.evrythng.evrythngtools.commands.AppStatusCommand;
import com.evrythng.evrythngtools.commands.HelpCommand;
import com.evrythng.evrythngtools.commands.InstancesCommand;
import com.evrythng.evrythngtools.commands.MongoPrimaryCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */

/**
 * TODO: comment this class
 */
public class EvrythngTools {

	private static final List<Command> commands = new ArrayList<>();

	public static void main(String[] args) throws IOException, InterruptedException {

		commands.add(new HelpCommand());
		commands.add(new MongoPrimaryCommand());
		commands.add(new InstancesCommand());
		commands.add(new AppStatusCommand());

		if (args.length == 0) {
			help();
			System.exit(-1);
		}

		String cmdName = args[0];
		Optional<Command> cmd = findCommand(cmdName);

		List<String> parameters = new LinkedList<>(Arrays.asList(args));
		parameters.remove(0);

		if (cmd.isPresent()) {
			if (!cmd.get().execute(parameters)) {
				System.exit(-1);
			}
		} else {
			help();
			System.exit(-1);
		}
	}

	private static Optional<Command> findCommand(String name) {

		return commands.stream().filter((c) -> c.getNames().contains(name)).findFirst();
	}

	public static void help() {

		System.out.println("Available commands are:");

		commands.stream().sorted((a, b) -> a.getNames().get(0).compareTo(b.getNames().get(0))).forEach((cmd) -> {
			System.out.println(" - " + cmd.getNames());
		});
	}
}
