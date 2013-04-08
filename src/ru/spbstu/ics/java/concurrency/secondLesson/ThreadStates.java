package ru.spbstu.ics.java.concurrency.secondLesson;

public class ThreadStates {

	Object _monitor = new Object();

	private class Waitingthread implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("Befor synch");
				synchronized (_monitor) {
					System.out.println("Befor wait");
					_monitor.wait();
					System.out.println("After wait");
				}
				System.out.println("After synch");
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
	private class SleepingThread implements Runnable {
		
		@Override
		public void run() {
			try {
				Thread.sleep(100);
				synchronized (_monitor) {
					_monitor.notify();
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	public void runThreads() {
		Thread waitingThread = new Thread(new Waitingthread());
		waitingThread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		new Thread(new SleepingThread()).start();
	}

	public static void main(String[] args) {
		new ThreadStates().runThreads();
	}
}
