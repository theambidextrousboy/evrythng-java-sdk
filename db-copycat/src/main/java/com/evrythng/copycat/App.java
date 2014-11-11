package com.evrythng.copycat;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.ServerAddress;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {

	private static final String SSH_PORT_FORWARDING_TEMPLATE = "ssh -o StrictHostKeyChecking=no -L%s:%s:%s %s";
	private static final int    DEFAULT_MONGO_DB_PORT        = 27017;

	public static void main(final String... args) throws IOException, InterruptedException {

		List<String> emailsOfUsersToCopy = emailsOfUsersToCopy(args);
		List<Address> sourceAddresses = sourceAddresses(args);
		List<Address> destinationAddresses = destinationAddresses(args);
		Set<Process> sourceSshPortForwardingProcesses = new HashSet<>();
		Set<Process> destinationSshPortForwardingProcesses = new HashSet<>();

		List<Integer> localSourcePorts = new ArrayList<>();
		for (Address address : sourceAddresses) {
			if (!address.host.equals("localhost") && !address.host.equals("127.0.0.1")) {
				int localPort = availablePort();
				Process sshPortForwardingProcess = setUpSshPortForwarding(localPort, address.host, address.port);
				sourceSshPortForwardingProcesses.add(sshPortForwardingProcess);
				localSourcePorts.add(localPort);
			} else {
				localSourcePorts.add(DEFAULT_MONGO_DB_PORT);
			}
		}

		List<Integer> localDestinationPorts = new ArrayList<>();
		for (Address address : destinationAddresses) {
			if (!address.host.equals("localhost") && !address.host.equals("127.0.0.1")) {
				int localPort = availablePort();
				Process sshPortForwardingProcess = setUpSshPortForwarding(localPort, address.host, address.port);
				destinationSshPortForwardingProcesses.add(sshPortForwardingProcess);
				localDestinationPorts.add(localPort);
			} else {
				localDestinationPorts.add(DEFAULT_MONGO_DB_PORT);
			}
		}

		MongoClient sourceClient = null;
		MongoClient destinationClient = null;
		try {

			List<Address> sourceLocalAddresses = localSourcePorts.stream().map(localSourcePort -> new Address("localhost", localSourcePort)).collect(Collectors.toList());

			sourceClient = new MongoClient(sourceLocalAddresses.stream().map(convertToServerAddress).collect(Collectors.toList()));

			List<Address> destinationLocalAddresses = localDestinationPorts.stream().map(localSourcePort -> new Address("localhost", localSourcePort)).collect(Collectors.toList());
			destinationClient = new MongoClient(destinationLocalAddresses.stream().map(convertToServerAddress).collect(Collectors.toList()));

			DB thngStore = sourceClient.getDB("thng-store");

			for (String email : emailsOfUsersToCopy) {
				QueryBuilder qb = QueryBuilder.start();
				qb.put("email").is(email);
				Set<String> owners = new HashSet<>();
				thngStore.getCollection("UserDocument").find(qb.get()).forEach(dbObject -> owners.add((String) dbObject.get("owner")));
				System.out.println(owners);
				if (owners.size() > 1) {
					throw new IllegalStateException("More than 1 owner found for email: " + email + ". Aborting!");
				}
				QueryBuilder query = QueryBuilder.start();
				if (!owners.isEmpty()) {
					query.put("owner").is(owners.iterator().next());
					for (String databaseName : sourceClient.getDatabaseNames()) {
						DB database = sourceClient.getDB(databaseName);
						System.out.println("Searching inside " + databaseName + "...");
						for (String collectionName : database.getCollectionNames()) {
							System.out.println("Searching inside " + collectionName + "...");
							Set<DBObject> objectsFound = new HashSet<>();
							database.getCollection(collectionName).find(query.get()).forEach(objectsFound::add);
							if (!objectsFound.isEmpty()) {
								System.out.println(objectsFound);
							}
						}
					}
				}
			}
		} finally {
			System.out.println("Cleaning resources...");
			if (sourceClient != null) {
				sourceClient.close();
			}
			if (destinationClient != null) {
				destinationClient.close();
			}
			if (sourceSshPortForwardingProcesses != null) {
				tearDownSshPortForwarding(sourceSshPortForwardingProcesses);
			}
			if (destinationSshPortForwardingProcesses != null) {
				tearDownSshPortForwarding(destinationSshPortForwardingProcesses);
			}
			System.out.println("...resources cleaned");
		}

		System.out.println("Hello World!");
	}

	private static int availablePort() throws IOException {

		try (ServerSocket socket = new ServerSocket(0)) {
			return socket.getLocalPort();
		}
	}

	private static List<String> emailsOfUsersToCopy(final String... args) {

		// TODO make real
		return Arrays.asList("tmcadeeko@googlemail.com");
	}

	private static List<Address> sourceAddresses(final String... args) {

		// TODO make real
		List<Address> addresses = new ArrayList<>();
		addresses.add(new Address("ip-172-30-73-236.ec2.internal", DEFAULT_MONGO_DB_PORT));
		addresses.add(new Address("ip-172-30-50-140.ec2.internal", DEFAULT_MONGO_DB_PORT));
		return addresses;
	}

	private static List<Address> destinationAddresses(final String... args) {

		// TODO make real
		List<Address> addresses = new ArrayList<>();
		addresses.add(new Address("localhost", DEFAULT_MONGO_DB_PORT));
		return addresses;
	}

	private static final class Address {

		private final String host;
		private final int    port;

		private Address(final String host, final int port) {

			this.port = port;
			this.host = host;
		}
	}

	private static final Function<Address, ServerAddress> convertToServerAddress = address -> {
		try {
			return new ServerAddress(address.host, address.port);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	};

	private static Process setUpSshPortForwarding(final int localPort, final String remoteHost, final int remotePort) throws IOException, InterruptedException {

		String command = String.format(SSH_PORT_FORWARDING_TEMPLATE, localPort, "localhost", remotePort, remoteHost);
		System.out.println("Executing command: " + command);
		Process process = Runtime.getRuntime().exec(command);
		Thread.sleep(10000);
		return process;
	}

	private static void tearDownSshPortForwarding(final Set<Process> portForwardingProcess) {

		portForwardingProcess.forEach(Process::destroyForcibly);
	}
}
