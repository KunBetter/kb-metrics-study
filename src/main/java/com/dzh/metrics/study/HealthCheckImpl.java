package com.dzh.metrics.study;

import java.util.Map;
import java.util.Map.Entry;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

public class HealthCheckImpl {

	static final HealthCheckRegistry healthChecks = new HealthCheckRegistry();

	private Database database = null;

	public void register() {
		healthChecks.register("postgres", new DatabaseHealthCheck(database));
	}

	static final Map<String, HealthCheck.Result> results = healthChecks
			.runHealthChecks();

	public void run() {
		for (Entry<String, HealthCheck.Result> entry : results.entrySet()) {
			if (entry.getValue().isHealthy()) {
				System.out.println(entry.getKey() + " is healthy");
			} else {
				System.err.println(entry.getKey() + " is UNHEALTHY: "
						+ entry.getValue().getMessage());
				final Throwable e = entry.getValue().getError();
				if (e != null) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
