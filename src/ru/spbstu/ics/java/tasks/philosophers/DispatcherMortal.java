package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * This dispatcher creates five <code>MortalPhilosopher</code>s and
 * <code>MortalPhilosophersController</code>, so no deadlock
 * occurs.
 * 
 * @author Oleg Rekin
 * @see MortalPhilosopher, MortalPhilosophersController
 * 
 */
public class DispatcherMortal {
	public static final int PHILOSOPHERS_COUNT = 5;

	public static void main(String[] args) {
		Lock chopsticks[] = new Lock[PHILOSOPHERS_COUNT];
		MortalPhilosopher philosophers[] = new MortalPhilosopher[PHILOSOPHERS_COUNT];
		// Create chopsticks (locks)
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new ReentrantLock();
		}
		// Create philosophers, assign chopsticks
		philosophers[0] = new MortalPhilosopher(
				chopsticks[chopsticks.length - 1], chopsticks[0]);
		for (int i = 1; i < philosophers.length; i++) {
			philosophers[i] = new MortalPhilosopher(chopsticks[i - 1],
					chopsticks[i]);
		}
		// start all threads
		Thread controller = new Thread(new MortalPhilosophersController(philosophers));
		controller.start();
		for (int i = 0; i < philosophers.length; i++) {
			philosophers[i].start();
		}
		try {
			for (int i = 0; i < philosophers.length; i++) {
				philosophers[i].join();
			}
		} catch (InterruptedException e) {
			System.err.println("Main thread was interrupted in its waiting");
			e.printStackTrace();
		}
		System.out.println("All completed.");
		controller.interrupt();
	}
}
