package ru.spbstu.ics.java.lesson_20130304;

public class First {
	
	private static final String MY_CONStANT="test";
	
	static int i;
	long l;
	char c;
	static Integer integer1;
	static Integer integer2=8999;
	Character character;
	
	public static void main(String[] args) {
		System.out.println("Hello world!");
		System.out.println("i = "+i);
		System.out.println("integer = "+integer1);
		integer1=i;
		integer1++;
		i=integer1;
		System.out.println("i = "+i);
		System.out.println("integer = "+integer1);
		integer1=8999;
		if (integer1.equals(integer2)) {
			System.out.println("Ura!");
		}else {
			System.out.println("O, net!");
		}
		
		Object object = new Object();
		object.equals(new Object());
		object.hashCode();
		integer1.hashCode();
	}
}
