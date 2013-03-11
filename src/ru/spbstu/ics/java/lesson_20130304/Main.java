package ru.spbstu.ics.java.lesson_20130304;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		int[] intArray={3,1,2};
		Arrays.sort(intArray);
		printArray(intArray);
		changeArray(intArray);
		printArray(intArray);
		String string1="abc";
		String string2=new String("cba");
		StringBuffer sbf=new StringBuffer(string1);
		StringBuilder sb=new StringBuilder(string2);
		List<String> list=new ArrayList<String>();
		List<String> list2=new LinkedList<String>();
		Vector<Integer> vector = new Vector<Integer>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> map2 = new TreeMap<String, Integer>();
		Set<String> set = new HashSet<String>();
		list.add("First");
		list.add("Second");
		for (String str : list) {
			System.out.println(str);
		}
	}
	public static void printArray(int[] array){
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	public static void changeArray(int[] array){
		for (int i = 0; i < array.length; i++) {
			array[i]=-1;
		}
	}
}
