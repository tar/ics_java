package ru.spbstu.ics.java.lesson_20130304;

/**
 * 
 * @author tar
 *
 */
public class Modificators {
	
	private int integer;
	
	private static Integer int1;
	private static Integer int2;
	
	//Constructor
	public Modificators(){
		/*
		 * default constructor
		 */
		integer=0;
	}
	
	static{
		int1=int2;
	}
	
	void print(String str){
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		System.out.println(int1);
		System.out.println(int2);
		new Modificators().print("Hello");
	}
	
}
