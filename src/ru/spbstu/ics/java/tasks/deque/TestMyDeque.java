package ru.spbstu.ics.java.tasks.deque;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TestMyDeque {
	public static void main(String[] args) {
		try {
			Deque<Integer> iMyDeque = new MyDeque<Integer>();
			Deque<Number> nMyDeque = new MyDeque<Number>();
			List<Float> fList = new ArrayList<Float>(1);
			Integer[] iArray = new Integer[3];
			Number[] nArray = new Number[3];
			Object[] oArray;
			iMyDeque.addFirst(1);
			iMyDeque.add(2);
			iMyDeque.addLast(3);
			System.out.print("MyDeque<Integer>: ");
			System.out.println(iMyDeque.toString());
			iMyDeque.toArray(iArray);
			System.out.print("Integer[]: ");
			for (int i = 0; i < iArray.length; i++) {
				System.out.format("%d ", iArray[i]);
			}
			System.out.println();
			fList.add(new Float(1.2));
			nMyDeque.addAll(iMyDeque);
			nMyDeque.addAll(fList);
			System.out.print("MyDeque<Number>: ");
			System.out.println(nMyDeque.toString());
			nArray = nMyDeque.toArray(nArray);
			System.out.print("Number[]: ");
			for (int i = 0; i < nArray.length; i++) {
				System.out.print(nArray[i]);
				System.out.print(" ");
			}
			System.out.println();
			oArray = nMyDeque.toArray();
			System.out.print("Numbers from Object[]: ");
			for (int i = 0; i < oArray.length; i++) {
				System.out.print((Number)oArray[i]);
				System.out.print(" ");
			}
			System.out.println();
			System.out.print("Contains 2:");
			System.out.println(nMyDeque.contains(new Integer(2)));
			System.out.print("Contains 5:");
			System.out.println(nMyDeque.contains(new Integer(5)));
			System.out.print("Contains float 2:");
			System.out.println(nMyDeque.contains(new Float(2)));
			System.out.print("Contains all iMyDeque:");
			System.out.println(nMyDeque.containsAll(iMyDeque));
			System.out.print("getFirst: ");
			System.out.println(nMyDeque.getFirst());
			System.out.print("getLast: ");
			System.out.println(nMyDeque.getLast());
			System.out.print("removeFirst: ");
			System.out.println(nMyDeque.removeFirst());
			System.out.print("removeLast: ");
			System.out.println(nMyDeque.removeLast());
			System.out.print("MyDeque<Number>: ");
			System.out.println(nMyDeque.toString());
			System.out.print("Is Empty:");
			System.out.println(nMyDeque.isEmpty());
			System.out.print("Contains all iMyDeque:");
			System.out.println(nMyDeque.containsAll(iMyDeque));
			nMyDeque.clear();
			System.out.print("Clear MyDeque<Number>: ");
			System.out.println(nMyDeque.toString());
			System.out.print("Is Empty:");
			System.out.println(nMyDeque.isEmpty());
			System.out.print("Peek first:");
			System.out.println(nMyDeque.peekFirst());
			System.out.print("Peek last:");
			System.out.println(nMyDeque.peekLast());
			
			iMyDeque.add(2);
			iMyDeque.add(-6);
			iMyDeque.add(2);
			iMyDeque.add(4);
			iMyDeque.addFirst(0);
			System.out.print("iMyDeque: ");
			System.out.println(iMyDeque.toString());
			System.out.print("iMyDeque size: ");
			System.out.println(iMyDeque.size());
			
			Iterator<Integer> it = iMyDeque.iterator();
			Iterator<Integer> dit = iMyDeque.descendingIterator();
			
			
			System.out.println("->, <-");
			while (it.hasNext()) {
				System.out.format("%d ", it.next());
			}
			System.out.println();
			while (dit.hasNext()) {
				System.out.format("%d ", dit.next());
			}
			System.out.println();
			it.remove();
			dit.remove();
			System.out.print("Remove via iterators: ");
			System.out.println(iMyDeque.toString());
			
			iMyDeque.removeFirstOccurrence(2);
			System.out.print("Remove first 2: ");
			System.out.println(iMyDeque.toString());
			iMyDeque.removeLastOccurrence(2);
			System.out.print("Remove last 2: ");
			System.out.println(iMyDeque.toString());
			iMyDeque.remove(100);
			System.out.print("Remove 100: ");
			System.out.println(iMyDeque.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
