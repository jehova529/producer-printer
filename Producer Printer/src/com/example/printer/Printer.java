package com.example.printer;

import java.util.concurrent.BlockingDeque;

/**
 * The Class Printer.
 */
public class Printer {

	/** The queue. */
	private BlockingDeque<Integer> queue;

	/**
	 * Instantiates a new printer.
	 *
	 * @param sharedQueue the shared queue
	 */
	public Printer(BlockingDeque<Integer> sharedQueue) {
		this.queue = sharedQueue;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		long end = System.currentTimeMillis() + (3 * 1000);
		int n = 0;
		while (System.currentTimeMillis() < end) {
			try {
				n = queue.takeLast();
			} catch (InterruptedException e) {
				System.out.println("Exception in Printer : " + e);
			}
			System.out.println("consumed value - " + n);
		}
	}

}
