package com.dzh.metrics.use;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

public class KBMeters extends MetricObject {

	private static final Meter meter = metrics.meter(MetricRegistry.name(
			KBMeters.class, "meter"));

	public static void main(String[] args) throws InterruptedException {

		consoleReporter(2);

		while (true) {
			meter.mark();
			Thread.sleep(1000);
		}
	}
}
