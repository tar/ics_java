package ru.spbstu.ics.java.concurrency.firstLesson;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLock {
	static Lock _lock = new ReentrantLock();
	static Lock _fairLock = new ReentrantLock(true);
	
	static Semaphore _semaphore = new Semaphore(1);
	static Semaphore _fairSemaphore = new Semaphore(1, true);
	
	public static void main(String[] args) {
		_lock.tryLock();
		_semaphore.tryAcquire();
	}
}
