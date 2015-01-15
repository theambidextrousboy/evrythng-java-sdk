/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.evrythng.evrythngtools.Command;
import com.evrythng.evrythngtools.ConsoleUtils;
import com.evrythng.evrythngtools.Ec2Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: comment this class
 */
public class InstancesCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getNames() {

		return Arrays.asList("instances", "inst");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean execute(List<String> parameters) {

		if (parameters.isEmpty()) {
			System.out.println("Error.");
			return false;
		}

		String stage = parameters.get(0);
		if ("all".equals(stage)) {
			stage = null;
		}
		parameters.remove(0);

		String role = parameters.get(0);
		if ("all".equals(role)) {
			role = null;
		}
		parameters.remove(0);

		String cmd = parameters.get(0);
		parameters.remove(0);

		switch (cmd) {
			case "show":
				show(stage, role, parameters);
				break;
		}

		return true;
	}

	private void show(String stage, String role, List<String> parameters) {

		List<Instance> instances = Ec2Utils.getInstances(stage, role);

		List<List<String>> table = new ArrayList<>(instances.size());

		for (Instance inst : instances) {

			table.add(Arrays.asList(Ec2Utils.getStage(inst).get(), Ec2Utils.getRole(inst).get(), inst.getPrivateDnsName()));
		}

		ConsoleUtils.printTable(System.out, table);
	}
}
