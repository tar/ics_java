package ru.spbstu.ics.java.collections;

import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;

public class InterfacesAndImplementations {
    public static void main(String[] args) {
    	List<String> arrayList = new ArrayList<String>();
    	List<String> linkedList = new LinkedList<String>();
    	List<String> vectorList = new Vector<String>();
    	List<String> stackList = new Stack<String>();
    	Set<String> hashSet = new HashSet<String>();
    	Set<String> treeSet = new TreeSet<String>();
    	Set<String> linkedSet = new LinkedHashSet<String>();
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        Map<String, Integer> hashtable = new Hashtable<String, Integer>();
        Map<String, Integer> treeMap = new TreeMap<String, Integer>();
        Map<String, Integer> weakMap = new WeakHashMap<String, Integer>();
        Map<String, Integer> linkedMap = new LinkedHashMap<String, Integer>();      
        Map<byte[], Integer> badkeyMap;
        Map<Integer, Integer> intMap;
        Queue<String> fifoList = new LinkedList<String>();
        Deque<String> listDeque = new LinkedList<String>();
        Deque<String> arrayDeque = new ArrayDeque<String>();
        
        List myList = new ArrayList();
        
        List<String> resultList=new ArrayList<String>();
        resultList.addAll(vectorList);
        resultList.removeAll(resultList);
        
        myList.add("aaaa");
        myList.add(new Integer(5));
        String a= (String) myList.get(0);
        
        Collections.synchronizedList(arrayList);
        
    }
}
