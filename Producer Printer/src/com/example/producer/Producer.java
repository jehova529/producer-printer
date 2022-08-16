package com.example.producer;

import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * The Class Producer.
 */
public class Producer {
	
	/** The queue. */
	private BlockingDeque<Integer> queue;

	/**
	 * Instantiates a new producer.
	 *
	 * @param sharedQueue the shared queue
	 */
	public Producer(BlockingDeque<Integer> sharedQueue) {
		this.queue = sharedQueue;
	}

	/**
	 * Produces even numbers.
	 */
	public void producerOne() {
		int i = 1;
		Random random = new Random();
		while (true) {
			int n = i * 2;
			synchronized (queue) {
				queue.add(n);
			}
			System.out.println("P1 produced - " + n);
			try {
				int k = random.nextInt(10000);
				System.out.println("sleeping P1 for " + k + " milliseconds");
				Thread.sleep(k);
			} catch (InterruptedException e) {
				System.out.println("Exception in producer : " + e);
			}
			i++;
		}
	}

	/**
	 * Produce multiples of 5 starting from 10.
	 */
	public void producerTwo() {
		int i = 5;
		Random random = new Random();
		while (true) {
			int n = i + 5;
			synchronized (queue) {
				queue.add(n);
			}
			System.out.println("P2 produced - " + n);
			i = n;
			try {
				int k = random.nextInt(10000);
				System.out.println("sleeping P2 for " + k + " milliseconds");
				Thread.sleep(k);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
