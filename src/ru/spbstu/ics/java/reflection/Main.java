package ru.spbstu.ics.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
	private static class A{
		private  String _a;
		private  Integer _b;
		
		public void test(){
			System.out.println("Hello");
		}
		private void privateTest(){
			System.out.println("тссссс");
		}
	}
	
	public static void main(String[] args) {
		Class clazz =  A.class;
		System.out.println("name is "+ clazz.getCanonicalName());
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(field.getName());
			System.out.println(field.getModifiers());
			System.out.println(field.getGenericType().toString());
		}
		for (Method method : clazz.getDeclaredMethods()) {
			System.out.println(method.getName());
			System.out.println(method.getModifiers());
			try {
				System.out.println(method.invoke(new A(), null));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
