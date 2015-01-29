/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParametersDelegate;
import com.evrythng.evrythngtools.Ec2Utils;
import com.evrythng.evrythngtools.MongoUtils;
import com.evrythng.evrythngtools.commands.arguments.EnvironmentArgument;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: comment this class
 */
public class MongoPrimaryCommand extends AbstractParentCommand {

	public MongoPrimaryCommand() {

		super("mp", "mongo-primary");
		addSubCommand(new Tunnel());
		addSubCommand(new Show());
	}

	private static class Show extends AbstractCommand {

		@ParametersDelegate
		private EnvironmentArgument environment = new EnvironmentArgument();

		protected Show() {

			super("show");
		}

		@Override
		public boolean execute() {

			System.out.println(primary(environment.value, getVia(environment.value)).getPrivateDnsName());
			return true;
		}
	}

	private static class Tunnel extends AbstractCommand {

		@ParametersDelegate
		private EnvironmentArgument environment = new EnvironmentArgument();
		@Parameter(names = { "-p", "--port" }, description = "The local port for the tunnel.")
		private int localPort = -1;

		protected Tunnel() {

			super("tunnel");
		}

		@Override
		public boolean execute() {

			if (localPort < 0) {
				Map<String, Integer> defaultPorts = new HashMap<>();
				defaultPorts.put("CI", 45017);
				defaultPorts.put("TEST", 40017);
				defaultPorts.put("STAGING", 41017);
				defaultPorts.put("PROD", 42017);

				if (defaultPorts.containsKey(environment.value)) {
					localPort = defaultPorts.get(environment.value);
				} else {
					throw new RuntimeException("Unspecified local port.");
				}
			}
			tunnel(environment.value, localPort);

			return true;
		}

		private void tunnel(final String env, final int localPort) {

			Instance via = getVia(env);
			System.out.println("Connecting via " + via.getPrivateDnsName() + "...");
			Instance primary = primary(env, via);
			System.out.println("MongoDB primary is " + primary.getPrivateDnsName() + ".");
			System.out.println("Establishing SSH tunnel on local port " + localPort + "...");

			ProcessBuilder pb = new ProcessBuilder("ssh", "-T", "-oStrictHostKeyChecking=no", "-L",
			                                       localPort + ":" + primary.getPrivateDnsName() + ":27017",
			                                       via.getPrivateDnsName());
			pb.redirectError(ProcessBuilder.Redirect.INHERIT);

			try {
				Process pr;
				pr = pb.start();

				// TODO fail on error
				try (InputStreamReader isr = new InputStreamReader(pr.getInputStream())) {
					if (isr.read() >= 0) {
						System.out.println("Established.");
					}

					pr.waitFor();
				}

				System.out.println("Terminated.");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static Instance getVia(final String env) {

		List<Instance> vias = Ec2Utils.getInstances(env, Ec2Utils.ROLE_ENGINE_STORE);
		if (vias.isEmpty()) {
			throw new RuntimeException("No store instance found.");
		}
		return vias.get(0);
	}

	private static Instance primary(final String env, final Instance via) {

		List<Instance> instances = Ec2Utils.getInstances(env, Ec2Utils.ROLE_ENGINE_DB);

		return MongoUtils.getMongoPrimary(instances, Optional.of(via)).get();
	}
}
