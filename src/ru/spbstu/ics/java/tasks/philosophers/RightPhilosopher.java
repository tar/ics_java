package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * 
 * This Philosopher takes the right chopstick, then the left chopstick.
 * 
 * @author Oleg Rekin
 * @see AbstractPhilosopher
 *
 */
public class RightPhilosopher extends AbstractPhilosopher {

	public RightPhilosopher(Lock _rightChopstick, Lock _leftChopstick) {
		super(_rightChopstick, _leftChopstick);
	}

	@Override
	protected void takeFirst() {
		_rightChopstick.lock();
	}

	@Override
	protected void takeLast() {
		_leftChopstick.lock();
	}

}
