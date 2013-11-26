package com.dzh.metrics.use;

import java.util.Random;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class KBTimers extends MetricObject {

	private static final Timer timer = metrics.timer(MetricRegistry.name(
			KBTimers.class, "timer"));

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		consoleReporter(2);

		Random rn = new Random();
		while (true) {
			Timer.Context context = timer.time();
			Thread.sleep(rn.nextInt(1000));
			context.stop();
		}
	}

}