package ru.spbstu.ics.java.concurrency.secondLesson;

import java.util.LinkedList;
import java.util.Queue;

public class WorkerThreadExample {
	private class WorkedThread implements Runnable {

		private Queue _tasks = new LinkedList();

		private final Object POISON_PILL = new Object();

		public void addtask(Runnable task) {
			_tasks.add(task);
		}

		public void shutdown() {
			_tasks.add(POISON_PILL);
		}

		@Override
		public void run() {
			while (true) {
				Object currentTask = _tasks.peek();
				if (currentTask == POISON_PILL) {
					System.out.println("О нет.. моя жизнь кончена");
					break;
				}
				((Runnable) currentTask).run();
			}
			System.out.println("Уффф.. как я устал. пора поспать");
		}

	}
}
