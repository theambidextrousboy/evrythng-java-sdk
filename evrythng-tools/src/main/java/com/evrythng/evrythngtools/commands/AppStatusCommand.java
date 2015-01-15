/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools.commands;

import com.amazonaws.services.ec2.model.Instance;
import com.evrythng.evrythngtools.Command;
import com.evrythng.evrythngtools.Ec2Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: comment this class
 */
public class AppStatusCommand implements Command {

	static Map<String, String> appUrls = new HashMap<>();

	static {
		appUrls.put("thng-store", "http://localhost:6080/thng-store/v1");
		appUrls.put("thng-li", "http://localhost:6081/thng-li/v1");
		appUrls.put("thng-cron", "http://localhost:6082/thng-cron/v1");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getNames() {

		return Arrays.asList("appstatus", "as");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean execute(List<String> parameters) {

		if (parameters.size() < 3) {
			System.out.println("Error.");
			return false;
		}

		String stage = parameters.get(0);
		if ("all".equals(stage)) {
			stage = null;
		}
		parameters.remove(0);

		String app = parameters.get(0);
		String[] apps;
		if ("all".equals(app)) {
			app = null;
			apps = appUrls.keySet().toArray(new String[0]);
		} else {
			apps = app.split(",");
		}
		parameters.remove(0);

		String path = parameters.get(0);
		parameters.remove(0);

		List<Instance> instances = Ec2Utils.getInstances(stage, Ec2Utils.ROLE_ENGINE_STORE);

		try {
			for (Instance instance : instances) {
				for (String a : apps) {
					String appUrl = appUrls.get(a);
					if (appUrl == null) {
						continue;
					}
					String cmd = "curl '" + appUrl + "/status" + path + "'";

					ProcessBuilder pb = new ProcessBuilder("ssh", "-oStrictHostKeyChecking=no", instance.getPrivateDnsName(),
					                                       cmd);
					pb.redirectError(ProcessBuilder.Redirect.INHERIT);
					Process pr;
					pr = pb.start();
					InputStreamReader sr = new InputStreamReader(pr.getInputStream());
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
									+ "   " + a + "   " + statusJson);
				}
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException();
		}

		return true;
	}

	private void show(String stage, String role, List<String> parameters) {

		List<Instance> instances = Ec2Utils.getInstances(stage, role);

		for (Instance inst : instances) {

			System.out.println(
					Ec2Utils.getStage(inst).get() + "   " + Ec2Utils.getRole(inst).get() + "   " + inst.getPrivateDnsName());
		}
	}
}
