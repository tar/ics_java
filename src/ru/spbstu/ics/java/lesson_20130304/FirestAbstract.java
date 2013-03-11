package ru.spbstu.ics.java.lesson_20130304;

public abstract class FirestAbstract implements FirstInterface{
	private int integer=1;
	
	public void printInt(){
		System.out.println(integer);
	}
	
	@Override
	public String getString(){
		return "";
	}
	
	public abstract void printString();
	public abstract void printAnotherString();
	
	public void printAll(){
		printString();
		printAnotherString();
		printInt();
	}
}
