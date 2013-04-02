package ru.spbstu.ics.java.concurrency.firstapp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class AtomicOps {
	public static void main(String[] args) {
		AtomicInteger atomicInt;
		int a = 0;
		int b = 1;
		int x = 2;
		int y = 3;
		a=x;
		x=b;
		y=a;
		b=y;
	}
}
