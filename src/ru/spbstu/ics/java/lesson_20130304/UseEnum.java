package ru.spbstu.ics.java.lesson_20130304;

public class UseEnum {
	public static void main(String[] args) {
		for (int i = 0; i < FirstEnum.values().length; i++) {
			System.out.println(FirstEnum.values()[i]);
		}
	}
}
