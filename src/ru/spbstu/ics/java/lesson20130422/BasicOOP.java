package ru.spbstu.ics.java.lesson20130422;

public class BasicOOP {
	public static void main(String[] args) {
		Class1 class1 = new Class1();
		((Class2) class1).foo1();
		Interface1 test1 = new Class1();
		Interface1 test2 = new Class2();
	}
}
