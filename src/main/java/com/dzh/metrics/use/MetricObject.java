package com.dzh.metrics.use;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

public class MetricObject {

	public static final MetricRegistry metrics = new MetricRegistry();

	public static void consoleReporter(long period) {
		ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build()
				.start(period, TimeUnit.SECONDS);
	}
}
