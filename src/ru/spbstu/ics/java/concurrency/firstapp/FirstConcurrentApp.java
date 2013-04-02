package ru.spbstu.ics.java.concurrency.firstapp;

public class FirstConcurrentApp {

	static class Test1 extends Thread {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getId());
		}
	}

	static class Test2 implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getId());
		}

	}

	public static void main(String[] args) {
		Test1 test1 = new Test1();
		Test2 test2 = new Test2();

		test1.setName("First_thread");
		System.out.println(test1.isAlive());
		test1.run();
		test1.start();
		new Thread(test2).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println(Thread.currentThread().getId());
			}
		}).start();
	}
}