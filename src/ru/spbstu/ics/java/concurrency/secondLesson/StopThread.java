package ru.spbstu.ics.java.concurrency.secondLesson;

public class StopThread {
	private class RunnigThread implements Runnable {

		@Override
		public void run() {
			while (Thread.currentThread().isInterrupted()) {
				System.out.println("Hello");
			}
			System.out.println("Ну вот...(((");
			System.out.println(Thread.currentThread().isInterrupted());
			System.out.println(Thread.interrupted());
			
		}
	}

	public void runThreads() {
		Thread thread = new Thread(new RunnigThread());
		thread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		thread.interrupt();
	}

	public static void main(String[] args) {
		new StopThread().runThreads();
	}
}
