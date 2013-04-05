package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * 
 * This is a left polite philosopher.
 * He tries to take the right chopstick, then tries to take the left one. 
 * If he fails, he releases chopsticks he has already taken.
 * 
 * @author Oleg Rekin
 *
 */
public class PolitePhilosopher implements Runnable {

	public static final int EATING_PERIOD = 100;
	public static final int WAITING_PERIOD = 50;
	public static final int ITERATIONS_COUNT = 100;

	private Lock _rightChopstick;
	private Lock _leftChopstick;

	private PolitePhilosopher() {
	}

	public PolitePhilosopher(Lock _rightChopstick, Lock _leftChopstick) {
		this._rightChopstick = _rightChopstick;
		this._leftChopstick = _leftChopstick;
	}
	
	protected void release() {
		_leftChopstick.unlock();
		_rightChopstick.unlock();		
	}

	@Override
	public void run() {
		boolean bCompleted = false;
		int iIterationIndex = 0;
		long lThreadId = Thread.currentThread().getId();
		System.out.println(lThreadId + " Running");
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(WAITING_PERIOD);
			} catch (InterruptedException e1) {
				System.err.println(lThreadId + " Philosopher thread was interrupted in his sleep.");
				e1.printStackTrace();
			}
			//Take chopsticks
			System.out.println(lThreadId + " Trying to take first chopstick...");
			if (!_leftChopstick.tryLock()) {
				System.out.println(lThreadId + " Failed");
				continue;
			}
			System.out.println(lThreadId + " Trying to take second chopstick...");
			if (!_rightChopstick.tryLock()) {
				_leftChopstick.unlock();
				System.out.println(lThreadId + " Failed, first chopstick released.");
				continue;
			}
			//Eat
			System.out.println(lThreadId + " Eating...");
			try {
				Thread.sleep(EATING_PERIOD);
			} catch (InterruptedException e) {
				System.err.println(lThreadId + " Philosopher thread was interrupted in his sleep.");
				e.printStackTrace();
			}
			//Release chopsticks
			release();
			System.out.println(lThreadId + " Released.");
			iIterationIndex++;
			if (iIterationIndex == ITERATIONS_COUNT) {
				bCompleted = true;
				break;
			}
		}
		if (bCompleted) {
			System.out.println(lThreadId + " Completed.");
		} else {
			release();
			System.out.println(lThreadId + " Interrupted.");
		}
	}
}