/*
* (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
* www.evrythng.com
*/
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.beust.jcommander.Parameter;
import com.evrythng.evrythngtools.ConsoleUtils;
import com.evrythng.evrythngtools.Ec2Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: comment this class
 */
public class InstancesCommand extends AbstractParentCommand {

	private static class Show extends AbstractCommand {

		@Parameter(names = "--env", description = "Environment(s). If omitted, uses all environments.")
		String environment = null;
		@Parameter(names = "--role", description = "Role(s). If omitted, uses all roles.")
		String role = null;

		private Show() {

			super("show");
		}

		@Override
		public boolean execute() {

			List<Instance> instances = Ec2Utils.getInstances(environment, role);

			List<List<String>> table = new ArrayList<>(instances.size());

			for (Instance inst : instances) {

				table.add(Arrays.asList(Ec2Utils.getStage(inst).orElse("?"), Ec2Utils.getRole(inst).orElse("?"),
				                        inst.getPrivateDnsName()));
			}

			ConsoleUtils.printTable(System.out, table);

			return true;
		}
	}

	public InstancesCommand() {

		super("inst", "instances");
		addSubCommand(new Show());
	}

}
