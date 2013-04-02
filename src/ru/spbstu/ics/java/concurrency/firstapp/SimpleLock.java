package ru.spbstu.ics.java.concurrency.firstapp;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLock {
	static Lock _lock = new ReentrantLock();
	
	static Semaphore _semaphore = new Semaphore(1);
	
	public static void main(String[] args) {
		_lock.tryLock();
		_semaphore.tryAcquire();
	}
}
