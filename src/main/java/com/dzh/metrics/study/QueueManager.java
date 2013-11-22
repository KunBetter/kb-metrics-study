package com.dzh.metrics.study;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class QueueManager {

	static final MetricRegistry metrics = new MetricRegistry();

	private final Queue<Job> queue = new LinkedBlockingQueue<Job>();

	/**
	 * Counters
	 */
	private final Counter pendingJobs = metrics.counter(MetricRegistry.name(
			QueueManager.class, "pending-jobs"));

	public void addJob(Job job) {
		pendingJobs.inc();
		queue.offer(job);
	}

	public Job takeJob() {
		pendingJobs.dec();
		return queue.peek();
	}

	/**
	 * Meters
	 */
	private final Meter requests = metrics.meter(MetricRegistry.name(
			RequestHandler.class, "requests"));

	/*
	 * This meter will measure the rate of requests in requests per second
	 */
	public void handleRequest1(Request request, Response response) {
		requests.mark();
		// etc
	}

	/**
	 * Timers
	 */
	private final Timer responses = metrics.timer(MetricRegistry.name(
			RequestHandler.class, "responses"));

	/*
	 * A timer measures both the rate that a particular piece of code is called
	 * and the distribution of its duration. This timer will measure the amount
	 * of time it takes to process each request in nanoseconds and provide a
	 * rate of requests in requests per second.
	 */
	public String handleRequest2(Request request, Response response) {
		final Timer.Context context = responses.time();
		try {
			// etc;
			return "OK";
		} finally {
			context.stop();
		}
	}

	/**
	 * Histograms
	 */
	/*
	 * A histogram measures the statistical distribution of values in a stream
	 * of data. In addition to minimum, maximum, mean, etc., it also measures
	 * median, 75th, 90th, 95th, 98th, 99th, and 99.9th percentiles. This
	 * histogram will measure the size of responses in bytes.
	 */
	private final Histogram responseSizes = metrics.histogram(MetricRegistry
			.name(RequestHandler.class, "response-sizes"));

	public void handleRequest3(Request request, Response response) {
		// etc
		responseSizes.update(response.getContent().length);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
