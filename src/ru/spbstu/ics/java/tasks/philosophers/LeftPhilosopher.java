package ru.spbstu.ics.java.tasks.philosophers;

import java.util.concurrent.locks.Lock;

/**
 * 
 * This Philosopher takes the left chopstick, then the right chopstick.
 * 
 * @author Oleg Rekin
 * @see AbstractPhilosopher
 * 
 */
public class LeftPhilosopher extends AbstractPhilosopher {
	
	public LeftPhilosopher(Lock _rightChopstick, Lock _leftChopstick) {
		super(_rightChopstick, _leftChopstick);
	}

	@Override
	protected void takeFirst() {
		_leftChopstick.lock();
	}

	@Override
	protected void takeLast() {
		_rightChopstick.lock();
	}

}
