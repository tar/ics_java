package ru.spbstu.ics.java.concurrency.secondLesson;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutors {
	
	static ExecutorService _service= Executors.newFixedThreadPool(5);
	
	private static class Task implements Runnable{
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " working...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " continued");
		}
	
	}
	private static class CallableTask implements Callable<String>{
		
		@Override
		public String call() throws Exception {
			System.out.println("start");
			Thread.sleep(10000);
			return "result";
		}
		
		
	}
	
	public static void main(String[] args) {
		Future<String> future = _service.submit(new CallableTask());
		long startTime = System.currentTimeMillis();
		System.out.println("is complete = " + future.isDone());
		System.out.println("is canceled = " + future.isCancelled());
		System.out.println("Time is " + (System.currentTimeMillis()-startTime));
		try {
			System.out.println(future.get());
			System.out.println("Time is " + (System.currentTimeMillis()-startTime));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
