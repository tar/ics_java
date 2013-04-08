package ru.spbstu.ics.java.concurrency.secondLesson;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestBarrier {
	static CyclicBarrier _barrier;

	private static class Task implements Runnable {
		@Override
		public void run() {
			System.out.println("Before barrier");
			try {
				Thread.sleep(100);
				_barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("haha");
		}
	};

	public static void main(String[] args) {
		_barrier = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println("Сообразим на троих?");
			}
		});
		try {
			Thread.sleep(1000);
			new Thread(new Task()).start();
			Thread.sleep(1000);
			new Thread(new Task()).start();
			Thread.sleep(1000);
			new Thread(new Task()).start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
