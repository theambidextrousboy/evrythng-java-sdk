/*
* (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
* www.evrythng.com
*/
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.beust.jcommander.Parameter;
import com.evrythng.evrythngtools.Ec2Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: comment this class
 */
public class AppStatusCommand extends AbstractCommand {

	static Map<String, String> appUrls = new HashMap<>();

	static {
		appUrls.put("store-app", "http://localhost:6080/thng-store/v1");
		appUrls.put("li-app", "http://localhost:6081/thng-li/v1");
		appUrls.put("cron-app", "http://localhost:6082/thng-cron/v1");
	}

	private static final String DEFAULT_PATH = "/version";
	@Parameter(names = "--env", description = "Environment. If omitted, uses all environments.")
	String environment = null;
	@Parameter(names = "--role", description = "Role. If omitted, uses all known roles.")
	String role = null;
	@Parameter(names = "--path", description = "Status path (with leading slash). Default: " + DEFAULT_PATH)
	String path = "/version";

	public AppStatusCommand() {

		super("as", "appstatus");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean execute() {

		if (path == null) {
			path = "/version";
		}

		List<Instance> instances = Ec2Utils.getInstances(environment, role);

		try {
			for (Instance instance : instances) {

				Optional<String> thisRole = Ec2Utils.getRole(instance);

				if (!thisRole.isPresent()) {
					continue;
				}

				String appUrl = appUrls.get(thisRole.get());
				if (appUrl == null) {
					continue;
				}

				String cmd = "curl '" + appUrl + "/status" + path + "'";

				ProcessBuilder pb = new ProcessBuilder("ssh", "-oStrictHostKeyChecking=no", instance.getPrivateDnsName(), cmd);
				pb.redirectError(ProcessBuilder.Redirect.INHERIT);
				Process pr;
				pr = pb.start();
				try (InputStreamReader sr = new InputStreamReader(pr.getInputStream())) {
					int x;
					StringBuilder sb = new StringBuilder();
					while ((x = sr.read()) >= 0) {
						if (sb.length() > 0 || (char) x == '{') {
							sb.append((char) x);
						}
					}
					String statusJson = sb.toString();
					pr.waitFor();

					System.out.println(
							instance.getInstanceId() + "   " + instance.getState() + "   " + Ec2Utils.getStage(instance).get()
									+ "   " + thisRole.get() + "   " + statusJson);
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException();
		}

		return true;
	}
}
