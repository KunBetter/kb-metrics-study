package com.dzh.metrics.study;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

	private final Database database;

	public DatabaseHealthCheck(Database database) {
		this.database = database;
	}

	@Override
	public HealthCheck.Result check() throws Exception {
		if (database.isConnected()) {
			return HealthCheck.Result.healthy();
		} else {
			return HealthCheck.Result.unhealthy("Cannot connect to "
					+ database.getUrl());
		}
	}
}
