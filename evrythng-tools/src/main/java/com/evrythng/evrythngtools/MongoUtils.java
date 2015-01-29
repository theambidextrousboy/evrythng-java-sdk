/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import com.amazonaws.services.ec2.model.Instance;
import com.google.gson.Gson;

/**
 * TODO: comment this class
 */
public class MongoUtils {

	private MongoUtils() {

	}

	public static class RsStatus {

		static class Member {

			public String name;
			public int state;
		}

		public List<Member> members;
	}

	public static Optional<Instance> getMongoPrimary(final List<Instance> mongoInstances, final Optional<Instance> via) {

		Instance instance = mongoInstances.get(0);

		RsStatus status = getMongoRsStatus(instance, via);

		Optional<RsStatus.Member> primary = status.members.stream().filter(m -> m.state == 1).findFirst();
		if (primary.isPresent()) {
			String name = primary.get().name;
			name = name.substring(0, name.indexOf(':'));
			String fName = name;
			return mongoInstances.stream().filter(i -> fName.equals(i.getPrivateDnsName())).findFirst();
		} else {
			return Optional.empty();
		}
	}

	public static RsStatus getMongoRsStatus(final Instance instance, final Optional<Instance> via) {

		String statusJson;
		try {
			String sshTo = (via.isPresent() ? via.get() : instance).getPrivateDnsName();

			String subCmd = "export LC_ALL=C && mongo";

			if (via.isPresent()) {
				subCmd += " --host '" + instance.getPrivateDnsName() + "'";
			}
			subCmd += " --eval 'printjson(rs.status())'";

			ProcessBuilder pb = new ProcessBuilder("ssh", "-oStrictHostKeyChecking=no", sshTo, subCmd);

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
				statusJson = sb.toString();
				pr.waitFor();
			}
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException();
		}

		statusJson = statusJson.replaceAll("ISODate\\([^\\)]*\\)", "\"\"");
		statusJson = statusJson.replaceAll("Timestamp\\([^\\)]*\\)", "\"\"");

		//System.out.println(statusJson);
		Gson gson = new Gson();

		return gson.fromJson(statusJson, RsStatus.class);
	}
}
