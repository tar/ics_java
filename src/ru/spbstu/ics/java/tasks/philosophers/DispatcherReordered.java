package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * This dispatcher creates philosophers in the order: 
 * R L R L R, 
 * so no deadlock occurs.
 * 
 * @author Oleg Rekin
 * @see LeftPhilosopher
 * @see RightPhilosopher
 *
 */
public class DispatcherReordered {
	public static final int PHILOSOPHERS_COUNT = 5;
	
	public static void main(String[] args) {
		Lock chopsticks[] = new Lock[PHILOSOPHERS_COUNT];
		AbstractPhilosopher philosophers[] = new AbstractPhilosopher[PHILOSOPHERS_COUNT];
		//Create chopsticks (locks)
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new ReentrantLock();
		}
		//Create philosophers, assign chopsticks
		philosophers[0] = new RightPhilosopher(chopsticks[chopsticks.length - 1], chopsticks[0]);
		for (int i = 1; i < philosophers.length; i++) {
			if ((i % 2) == 1) {
				philosophers[i] = new LeftPhilosopher(chopsticks[i-1], chopsticks[i]);
			} else {
				philosophers[i] = new RightPhilosopher(chopsticks[i-1], chopsticks[i]);
			}
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