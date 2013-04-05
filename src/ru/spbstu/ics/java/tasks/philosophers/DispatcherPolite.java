package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * This dispatcher creates five <code>PolitePhilosopher</code>s, so no deadlock
 * occurs.
 * 
 * @author Oleg Rekin
 * @see PolitePhilosopher
 * 
 */
public class DispatcherPolite {
	public static final int PHILOSOPHERS_COUNT = 5;

	public static void main(String[] args) {
		Lock chopsticks[] = new Lock[PHILOSOPHERS_COUNT];
		PolitePhilosopher philosophers[] = new PolitePhilosopher[PHILOSOPHERS_COUNT];
		// Create chopsticks (locks)
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new ReentrantLock();
		}
		// Create philosophers, assign chopsticks
		philosophers[0] = new PolitePhilosopher(
				chopsticks[chopsticks.length - 1], chopsticks[0]);
		for (int i = 1; i < philosophers.length; i++) {
			philosophers[i] = new PolitePhilosopher(chopsticks[i - 1],
					chopsticks[i]);
		}
		// start all threads
		Thread threads[] = new Thread[PHILOSOPHERS_COUNT];
		for (int i = 0; i < philosophers.length; i++) {
			threads[i] = new Thread(philosophers[i]);
			threads[i].start();
		}
		try {
			for (int i = 0; i < threads.length; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.err.println("Main thread was interrupted in its waiting");
			e.printStackTrace();
		}
		System.out.println("All completed.");
	}
}