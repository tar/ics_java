package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * 
 * This is an abstract philosopher.
 * Its children should define the order of taking chopsticks.
 * This class is a base class for <code>LeftPhilosopher</code> and 
 * <code>RightPhilosopher</code>.
 * 
 * @author Oleg Rekin
 * @see LeftPhilosopher, RightPhilosopher
 *
 */
public abstract class AbstractPhilosopher implements Runnable {

	public static final int EATING_PERIOD = 100;
	public static final int ITERATIONS_COUNT = 100;

	protected Lock _rightChopstick;
	protected Lock _leftChopstick;

	private AbstractPhilosopher() {
	}

	public AbstractPhilosopher(Lock _rightChopstick, Lock _leftChopstick) {
		this._rightChopstick = _rightChopstick;
		this._leftChopstick = _leftChopstick;
	}
	
	protected abstract void takeFirst();
	
	protected abstract void takeLast();
	
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
			//Take chopsticks
			System.out.println(lThreadId + " Waiting for first chopstick...");
			takeFirst();
			System.out.println(lThreadId + " Waiting for second chopstick...");
			takeLast();
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
