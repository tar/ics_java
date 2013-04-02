package ru.spbstu.ics.java.concurrency.firstapp;

public class OurSingleton {

	private static OurSingleton _instance;

	private OurSingleton() {
	}

	public static OurSingleton getInstance() {
		if (_instance == null) {
			synchronized (OurSingleton.class) {
				if (_instance == null) {
					_instance = new OurSingleton();
				}
			}
		}
		return _instance;
	}
}
