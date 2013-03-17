package ru.spbstu.ics.java.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        Map<String, Integer> hashtable = new Hashtable<String, Integer>();
        Map<String, Integer> treeMap = new TreeMap<String, Integer>();
        Map<String, Integer> weakMap = new WeakHashMap<String, Integer>();
        Map<String, Integer> linkedMap = new LinkedHashMap<String, Integer>();
        Set<String> hashSet = new HashSet<String>();
        Set<String> treeSet = new TreeSet<String>();
        Set<String> linkedSet = new LinkedHashSet<String>();
        List<String> arrayList = new ArrayList<String>();
        List<String> linkedList = new LinkedList<String>();
        List<String> vectorList = new Vector<String>();
        List<String> stackList = new Stack<String>();
        Queue<String> fifoList = new LinkedList<String>();
        Deque<String> listDeque = new LinkedList<String>();
        Deque<String> arrayDeque = new ArrayDeque<String>();

        Collections.synchronizedList(arrayList);
    }
}
