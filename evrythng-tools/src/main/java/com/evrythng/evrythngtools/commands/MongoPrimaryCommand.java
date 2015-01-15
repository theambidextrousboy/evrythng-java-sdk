/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.evrythng.evrythngtools.Command;
import com.evrythng.evrythngtools.Ec2Utils;
import com.evrythng.evrythngtools.MongoUtils;

import java.io.InputStreamReader;
import java.util.*;

/**
 * TODO: comment this class
 */
public class MongoPrimaryCommand implements Command {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getNames() {

		return Arrays.asList("mongo-primary", "mp");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean execute(List<String> parameters) {

		if (parameters.isEmpty()) {
			System.out.println("Sub-command expected");
			System.out.println(" - mp show");
			System.out.println(" - mp tunnel");
			return false;
		}

		String env = parameters.get(0);
		parameters.remove(0);

		String cmd = parameters.get(0);
		parameters.remove(0);

		switch (cmd) {
			case "show":
				show(env, parameters);
				break;
			case "tunnel":
				tunnel(env, parameters);
				break;
		}

		return true;
	}

	private Instance getVia(String env) {

		List<Instance> vias = Ec2Utils.getInstances(env, Ec2Utils.ROLE_ENGINE_STORE);
		if (vias.isEmpty()) {
			throw new RuntimeException("No store instance found.");
		}
		return vias.get(0);
	}

	private Instance primary(String env, Instance via) {

		List<Instance> instances = Ec2Utils.getInstances(env, Ec2Utils.ROLE_ENGINE_DB);

		return MongoUtils.getMongoPrimary(instances, Optional.of(via)).get();
	}

	private void show(String env, List<String> parameters) {

		System.out.println(primary(env, getVia(env)).getPrivateDnsName());
	}

	private void tunnel(String env, List<String> parameters) {

		int localPort;

		if (parameters.size() >= 1) {

			localPort = Integer.parseInt(parameters.get(0));
			parameters.remove(0);

		} else {

			Map<String, Integer> defaultPorts = new HashMap<>();
			defaultPorts.put("CI", 45017);
			defaultPorts.put("TEST", 40017);
			defaultPorts.put("STAGING", 41017);
			defaultPorts.put("PROD", 42017);

			if (defaultPorts.containsKey(env)) {
				localPort = defaultPorts.get(env);
			} else {
				throw new RuntimeException("Unspecified local port.");
			}
		}

		Instance via = getVia(env);
		System.out.println("Connecting via " + via.getPrivateDnsName() + "...");
		Instance primary = primary(env, via);
		System.out.println("MongoDB primary is " + primary.getPrivateDnsName() + ".");
		System.out.println("Establishing SSH tunnel on local port " + localPort + "...");

		ProcessBuilder pb = new ProcessBuilder("ssh", "-T", "-oStrictHostKeyChecking=no", "-L",
		                                       localPort + ":" + primary.getPrivateDnsName() + ":27017", via.getPrivateDnsName
				());
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);

		try {
			Process pr;
			pr = pb.start();

			// TODO fail on error
			InputStreamReader isr = new InputStreamReader(pr.getInputStream());
			if (isr.read() >= 0) {
				System.out.println("Established.");
			}

			pr.waitFor();

			System.out.println("Terminated.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
