/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.evrythngtools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

/**
 * TODO: comment this class
 */
public class Ec2Utils {

	private static AmazonEC2 ec2 = new AmazonEC2Client();
	public static final String STAGE_CI = "CI";
	public static final String STAGE_TEST = "TEST";
	public static final String STAGE_DEMO = "DEMO";
	public static final String STAGE_STAGING = "STAGING";
	public static final String STAGE_PROD = "PROD";
	public static final String ROLE_ENGINE_STORE = "store-app";
	public static final String ROLE_ENGINE_DB = "engine-db";

	private Ec2Utils() {

	}

	public static List<Instance> getInstances(String stage, String role) {

		List<Instance> ret = new ArrayList<Instance>();

		DescribeInstancesRequest request = new DescribeInstancesRequest();

		List<Filter> filters = new ArrayList<Filter>();
		if (stage != null) {
			Filter stageFilter = new Filter("tag:Stage", Arrays.asList(stage));
			filters.add(stageFilter);
		}
		if (role != null) {
			Filter roleFilter = new Filter("tag:Role", Arrays.asList(role));
			filters.add(roleFilter);
		}

		DescribeInstancesResult result = ec2.describeInstances(request.withFilters(filters));

		List<Reservation> reservations = result.getReservations();

		for (Reservation reservation : reservations) {
			ret.addAll(reservation.getInstances());
		}

		return ret;
	}

	private static Optional<String> getTagValue(List<Tag> tags, String key) {

		Optional<Tag> tag = tags.stream().filter((t) -> t.getKey().equals(key)).findFirst();

		return tag.isPresent() ? Optional.of(tag.get().getValue()) : Optional.empty();
	}

	private static Optional<String> getTagValue(Instance instance, String key) {

		return getTagValue(instance.getTags(), key);
	}

	public static Optional<String> getRole(Instance instance) {

		return getTagValue(instance, "Role");
	}

	public static Optional<String> getStage(Instance instance) {

		return getTagValue(instance, "Stage");
	}
}
