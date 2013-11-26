package com.dzh.metrics.use;

import java.util.LinkedList;
import java.util.Queue;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

public class KBCounter extends MetricObject {

	public final Counter pendingJobs = metrics.counter(MetricRegistry.name(
			KBCounter.class, "pending-jobs"));

	public final Queue<String> queue = new LinkedList<String>();

	public void add(String str) {
		pendingJobs.inc();
		queue.offer(str);
	}

	public String take() {
		pendingJobs.dec();
		return queue.poll();
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		consoleReporter(2);

		KBCounter tc = new KBCounter();
		while (true) {
			tc.add("1");
			Thread.sleep(1000);
		}
	}

}
