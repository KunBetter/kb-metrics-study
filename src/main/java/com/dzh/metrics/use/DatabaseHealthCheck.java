package com.dzh.metrics.use;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

public class DatabaseHealthCheck extends HealthCheck {

	static final HealthCheckRegistry healthChecks = new HealthCheckRegistry();

	private Database database;

	public DatabaseHealthCheck(Database database) {
		this.database = database;
	}

	@Override
	public Result check() throws Exception {
		if (database.isConnected()) {
			return Result.healthy();
		} else {
			return Result.unhealthy("Can not connect to database");
		}
	}

	public static void main(String[] args) throws Exception {
		Database db = new Database();
		DatabaseHealthCheck checkHealth = new DatabaseHealthCheck(db);
		healthChecks.register("postgres", checkHealth);

		while (true) {
			Map<String, Result> results = healthChecks.runHealthChecks();
			for (Entry<String, Result> entry : results.entrySet()) {
				if (entry.getValue().isHealthy()) {
					System.out.println(entry.getKey() + " is healthy");
				} else {
					System.err.println(entry.getKey() + " is UNHEALTHY: "
							+ entry.getValue().getMessage());
				}
			}
			Thread.sleep(1000);
		}
	}
}

class Database {

	static Random rn = new Random();

	public boolean isConnected() {
		// TODOAuto-generated method stub
		return rn.nextBoolean();
	}

}
