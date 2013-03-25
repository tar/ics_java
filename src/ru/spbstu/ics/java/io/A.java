package ru.spbstu.ics.java.io;

import java.io.Serializable;

class A extends B implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient int _int;
	private String _string;
	private long _date;

	A() {
		System.out.println("constructor runned");
		_int = -10;
		_string = "Hello world";
		_date = System.currentTimeMillis();
	}

	public void print() {
		System.out.println("integer = " + _int);
		System.out.println("today is " + _date);
		System.out.println("string " + _string);
	}

	public void setString(String str) {
		_string = str;
	}
}
