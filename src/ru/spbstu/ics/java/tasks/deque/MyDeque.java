package ru.spbstu.ics.java.tasks.deque;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

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
		
		@Override
		public int hashCode() {
			return _data.hashCode();
		}
		
		public boolean equals(Entry e) {
			return _data.equals((Object)e.get_data());
		}
		
		@Override
		public boolean equals(Object o) {
			if (o.getClass() != this.getClass()) {
				return false;
			}
			return this.equals((Entry)o);
		}
	}
	
	private List<Entry> _data;
	private int _size;
	private Entry _head;
	private Entry _tail;
	
	@Override
	public boolean isEmpty() {
		return (_size == 0);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[_size];
		int index = 0;
		for (Entry entry : _data) {
			array[index] = (Object)entry.get_data();
			index++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO
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
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			if (!contains((Object) iterator.next())) {
				return false;
			}
			//TODO: check type here or in contains(Object) or add contains(T)?
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean result = false;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			result |= this.add((T) iterator.next());
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			result |= this.remove(iterator.next());
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
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
		this.addLast(e);
		return true;
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
		Class<?> classT = _data.get(0).getClass();
		Entry entry = new Entry();
		Object object;
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return _size;
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
