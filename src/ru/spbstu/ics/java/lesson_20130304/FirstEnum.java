package ru.spbstu.ics.java.lesson_20130304;

public enum FirstEnum {
	first(1),
	second(2),
	third(3);
	
	private int _id;
	
	public int getId(){
		return _id;
	}
	
	private FirstEnum(int id){
		_id=id;
	}
	
	@Override
	public String toString(){
		return new Integer(_id).toString();
	}
}
