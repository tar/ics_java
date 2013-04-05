package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * 
 * This is a mortal philosopher. He tries to take the left chopstick, then to
 * take the right chopstick.
 * <p>
 * There is a field <code>_lastMealTime</code> that contains the time of the
 * last meal of the philosopher (in ms).
 * <p>
 * If a philosopher dies, he looses a chopstick (if he had one), so it becomes
 * accessible for his neighbours.
 * 
 * @author Oleg Rekin
 * 
 */
public class MortalPhilosopher extends Thread {

	public static final int EATING_PERIOD = 100;
	public static final int ITERATIONS_COUNT = 100;

	private Lock _rightChopstick;
	private Lock _leftChopstick;
	private boolean _rightChopstickLocked;
	private boolean _leftChopstickLocked;
	private volatile long _lastMealTime;

	private MortalPhilosopher() {
	}

	public MortalPhilosopher(Lock _rightChopstick, Lock _leftChopstick) {
		this._rightChopstick = _rightChopstick;
		this._leftChopstick = _leftChopstick;
		_rightChopstickLocked = false;
		_leftChopstickLocked = false;
		set_lastMealTime(System.currentTimeMillis());
	}

	protected void release() {
		if (_leftChopstickLocked) {
			_leftChopstick.unlock();
			_leftChopstickLocked = false;
		}
		if (_rightChopstickLocked) {
			_rightChopstick.unlock();
			_rightChopstickLocked = false;
		}
	}

	@Override
	public void run() {
		boolean bCompleted = false;
		int iIterationIndex = 0;
		long lThreadId = Thread.currentThread().getId();
		System.out.println(lThreadId + " Running");
		while (!Thread.interrupted()) {
			// Take chopsticks
			try {
				System.out.println(lThreadId
						+ " Waiting for first chopstick...");
				_leftChopstick.lockInterruptibly();
				_leftChopstickLocked = true;
				System.out.println(lThreadId
						+ " Waiting for second chopstick...");
				_rightChopstick.lockInterruptibly();
				_rightChopstickLocked = true;
			} catch (InterruptedException e) {
				break;
			}
			// Eat
			set_lastMealTime(System.currentTimeMillis());
			System.out.println(lThreadId + " Eating...");
			try {
				Thread.sleep(EATING_PERIOD);
			} catch (InterruptedException e) {
				// Philosopher thread was interrupted (died)
				break;
			}
			// Release chopsticks
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
			System.out.println(lThreadId + " Died.");
			release();
		}
	}

	public long get_lastMealTime() {
		return _lastMealTime;
	}

	public void set_lastMealTime(long _lastMealTime) {
		this._lastMealTime = _lastMealTime;
	}
}
