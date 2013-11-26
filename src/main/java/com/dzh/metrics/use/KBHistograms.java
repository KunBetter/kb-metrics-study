package com.dzh.metrics.use;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

public class KBHistograms extends MetricObject {

	private static final Histogram histo = metrics.histogram(MetricRegistry
			.name(KBHistograms.class, "response-sizes"));

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		consoleReporter(2);

		int i = 0;
		while (true) {
			histo.update(i++);
			Thread.sleep(1000);
		}
	}

}
