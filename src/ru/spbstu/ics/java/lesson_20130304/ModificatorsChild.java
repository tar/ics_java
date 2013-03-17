package ru.spbstu.ics.java.lesson_20130304;

public class ModificatorsChild  extends Modificators implements FirstInterface{
	
	@Override
	public void print(String str){
		super.print("hello");
		System.err.println(str + " bye");
	}

	@Override
	public String getString() {
		return null;
	}
}
