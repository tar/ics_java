package ru.spbstu.ics.java.tasks.deque;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyDeque<T> implements Deque<T> {

	private class Entry {
		private T _data;
		private Entry _next;
		private Entry _prev;
		
		public Entry() {
			_data = null;
			_next = null;
			_prev = null;
		}
		
		public Entry(T _data) {
			this();
			this._data = _data;
		}
		
		public T get_data() {
			return _data;
		}
		
		public void set_data(T _data) {
			this._data = _data;
		}
		
		public Entry get_next() {
			return _next;
		}
		
		public void set_next(Entry _next) {
			this._next = _next;
		}
		
		public Entry get_prev() {
			return _prev;
		}
		
		public void set_prev(Entry _prev) {
			this._prev = _prev;
		}
	}
	
	private List<Entry> _data;
	private int _size;
	private Entry _head;
	private Entry _tail;
	
	public MyDeque() {
		_data = new LinkedList<Entry>();
		_size = 0;
		_head = null;
		_tail = null;
	}
	
	public MyDeque(Collection<? extends T> c) {
		this();
		addAll(c);
	}
	
	private void removeExistingEntry(Entry e) {
		if (_head == e) {
			_head = e.get_next();
		}
		if (_tail == e) {
			_tail = e.get_prev();
		}
		if (e.get_prev() != null) {
			e.get_prev().set_next(e.get_next());
		}
		if (e.get_next() != null) {
			e.get_next().set_prev(e.get_prev());
		}
		_data.remove(e);
		_size--;
	}
	
	@Override
	public boolean isEmpty() {
		return (_size == 0);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[_size];
		int index = 0;
		Entry entry = _head;
		while (entry != null) {
			array[index] = entry.get_data();
			entry = entry.get_next();
			index++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		Object[] array;
		if (a.length < _size) {
			array = new Object[_size];
		} else {
			array = (Object[])a;
		}
		int index = 0;
		Entry entry = _head;
		while (entry != null) {
			array[index] = entry.get_data();
			entry = entry.get_next();
			index++;
		}
		for (int i = index; i < array.length; i++) {
			array[i] = null;
		}
		return (T[])array;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.size() == 0) {
			return true;
		}
		if (_size == 0) {
			return false; 
		}
		for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
			if (!contains((Object) iterator.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean result = false;
		for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
			result |= add((T) iterator.next());
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
			result |= remove(iterator.next());
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Entry entry = _head;
		Entry nextEntry;
		boolean result = false;
		while (entry != null) {
			if (!c.contains(entry.get_data())) {
				nextEntry = entry.get_next();
				removeExistingEntry(entry);
				result = true;
				entry = nextEntry;
			}
		}
		return result;
	}

	@Override
	public void clear() {
		_data.clear();
		_size = 0;
	}

	@Override
	public void addFirst(T e) {
		Entry entry = new Entry(e);
		if (_size != 0) {
			entry.set_next(_head);
			_head.set_prev(entry);
			_head = entry;
		} else {
			_head = entry;
			_tail = entry;
		}
		_data.add(entry);
		_size++;
	}

	@Override
	public void addLast(T e) {
		Entry entry = new Entry(e);
		if (_size != 0) {
			entry.set_prev(_tail);
			_tail.set_next(entry);
			_tail = entry;
		} else {
			_head = entry;
			_tail = entry;
		}
		_data.add(entry);
		_size++;
	}

	@Override
	public boolean offerFirst(T e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(T e) {
		addLast(e);
		return true;
	}

	@Override
	public T removeFirst() {
		if (_size == 0) {
			throw new NoSuchElementException("The deque is empty.");
		}
		T requestedData = _head.get_data();
		removeExistingEntry(_head);
		return requestedData;
	}

	@Override
	public T removeLast() {
		if (_size == 0) {
			throw new NoSuchElementException("The deque is empty.");
		}
		T requestedData = _tail.get_data();
		removeExistingEntry(_tail);
		return requestedData;
	}

	@Override
	public T pollFirst() {
		if (_size == 0) {
			return null;
		}
		T requestedData = _head.get_data();
		removeExistingEntry(_head);
		return requestedData;
	}

	@Override
	public T pollLast() {
		if (_size == 0) {
			return null;
		}
		T requestedData = _tail.get_data();
		removeExistingEntry(_tail);
		return requestedData;
	}

	@Override
	public T getFirst() {
		if (_size == 0) {
			throw new NoSuchElementException("The deque is empty.");
		}
		return _head.get_data();
	}

	@Override
	public T getLast() {
		if (_size == 0) {
			throw new NoSuchElementException("The deque is empty.");
		}
		return _tail.get_data();
	}

	@Override
	public T peekFirst() {
		if (_size == 0) {
			return null;
		}
		return _head.get_data();
	}

	@Override
	public T peekLast() {
		if (_size == 0) {
			return null;
		}
		return _tail.get_data();
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		Entry entry = _head;
		while (entry != null) {
			if (entry.get_data() != null) {
				if (entry.get_data().equals(o)) {
					removeExistingEntry(entry);
					return true;
				}
			} else {
				if (o == null) {
					removeExistingEntry(entry);
					return true;
				}
			}
			entry = entry.get_next();
		}
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		Entry entry = _tail;
		while (entry != null) {
			if (entry.get_data() != null) {
				if (entry.get_data().equals(o)) {
					removeExistingEntry(entry);
					return true;
				}
			} else {
				if (o == null) {
					removeExistingEntry(entry);
					return true;
				}
			}
			entry = entry.get_prev();
		}
		return false;
	}

	@Override
	public boolean add(T e) {
		addLast(e);
		return true;
	}

	@Override
	public boolean offer(T e) {
		addLast(e);
		return true;
	}

	@Override
	public T remove() {
		return removeFirst();
	}

	@Override
	public T poll() {
		return pollFirst();
	}

	@Override
	public T element() {
		return getFirst();
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	@Override
	public void push(T e) {
		addFirst(e);
	}

	@Override
	public T pop() {
		return removeFirst();
	}

	@Override
	public boolean remove(Object o) {
		return removeFirstOccurrence(o);
	}

	@Override
	public boolean contains(Object o) {
		for (Entry entry : _data) {
			if (entry.get_data() != null) {
				if (entry.get_data().equals(o)) {
					return true;
				}
			} else {
				if (o == null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int size() {
		return _size;
	}
	
	private class Itr implements Iterator<T> {
		private Entry _entry;
		private Entry _nextEntry;
		
		public Itr() {
			_entry = null;
			_nextEntry = _head;
		}
		
		@Override
		public boolean hasNext() {
			return (_nextEntry != null);
		}

		@Override
		public T next() {
			if (_nextEntry == null) {
				throw new NoSuchElementException("Next element doesn't exist.");
			}
			_entry = _nextEntry;
			_nextEntry = _nextEntry.get_next();
			return _entry.get_data();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class DescendingItr implements Iterator<T> {
		private Entry _entry;
		private Entry _nextEntry;
		
		public DescendingItr() {
			_entry = null;
			_nextEntry = _tail;
		}
		
		@Override
		public boolean hasNext() {
			return (_nextEntry != null);
		}

		@Override
		public T next() {
			if (_nextEntry == null) {
				throw new NoSuchElementException("Next element doesn't exist.");
			}
			_entry = _nextEntry;
			_nextEntry = _nextEntry.get_prev();
			return _entry.get_data();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	@Override
	public Iterator<T> descendingIterator() {
		return new DescendingItr();
	}

}
