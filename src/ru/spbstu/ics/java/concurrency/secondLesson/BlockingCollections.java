package ru.spbstu.ics.java.concurrency.secondLesson;

import java.util.concurrent.BlockingQueue;

public class BlockingCollections {

	private class BadBlockingQueue implements OurQueue {

		@Override
		public synchronized Object get() {
			return null;
		}

		@Override
		public synchronized void put(Object o) {

		}

	}

	private class GoodBlockingQueue implements OurQueue {

		private Object _lock = new Object();

		@Override
		public Object get() {
			synchronized (_lock) {

				return null;
			}
		}

		@Override
		public void put(Object o) {
			synchronized (_lock) {
				//
			}
		}

	}

	public static void main(String[] args) {
		BlockingQueue<String> queue;
	}
}
