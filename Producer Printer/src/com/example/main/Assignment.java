package com.example.main;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.printer.Printer;
import com.example.producer.Producer;

/**
 * The Class Assignment.
 */
public class Assignment {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		BlockingDeque<Integer> sharedQueue = new LinkedBlockingDeque<>();
		Producer producer = new Producer(sharedQueue);
		Printer printer = new Printer(sharedQueue);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(producer::producerOne);
		executorService.submit(producer::producerTwo);
		
		ScheduledExecutorService printerExecutor = Executors.newScheduledThreadPool(1);
		// Printer will be triggered every twenty seconds (initial delay - 5 seconds)
		printerExecutor.scheduleAtFixedRate(printer::print, 5, 20, TimeUnit.SECONDS);
	}

}
