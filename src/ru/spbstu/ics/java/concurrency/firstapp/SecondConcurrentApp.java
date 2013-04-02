package ru.spbstu.ics.java.concurrency.firstapp;

public class SecondConcurrentApp {

	static volatile int counter = 0;

	private static Object _obj = new Object();

	public synchronized void meth1(){
		
	}
	
	static class Thread1 implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				for (int i = 0; i < 100; i++) {
					System.out.println(counter);
					synchronized (_obj) {
						counter++;
					}
				}
				break;
			}
			// TODO exit thread
		}

	}

	static class Thread2 implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println(counter);
				synchronized (_obj) {
					counter--;
				}
			}
		}

	}

	public static void main(String[] args) {
		new Thread(new Thread1()).start();
		new Thread(new Thread2()).start();
	}
}
