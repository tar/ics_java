package ru.spbstu.ics.java.collections;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyDeque<T extends Comparable<T>> implements Deque<T>{
	
	private class Entry{
		T data;
		Entry next;
		Entry prev;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFirst(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLast(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean offerFirst(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(T e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
