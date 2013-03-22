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
		e.set_prev(null);
		e.set_data(null);
		e.set_next(null);
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
			array[index++] = entry.get_data();
			entry = entry.get_next();
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {   
		// \/ Copied from LinkedList
		if (a.length < _size)
        a = (T[])java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), _size);
		Object[] array = a;
		// /\ Copied from LinkedList
		int index = 0;
		Entry entry = _head;
		while (entry != null) {
			array[index++] = entry.get_data();
			entry = entry.get_next();
		}
		for (int i = index; i < array.length; i++) {
			array[i] = null;
		}
		return a;
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
		Entry entry = _head;
		Entry nextEntry;
		while (entry != null) {
			nextEntry = entry.get_next();
			entry.set_prev(null);
			entry.set_data(null);
			entry.set_next(null);
			entry = nextEntry;
		}
		_head = null;
		_tail = null;
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

	private enum IteratorStartPosition {
		head, tail;
	}

	private class Itr implements Iterator<T> {
		private Entry _previousEntry;
		private Entry _lastReturnedEntry;
		private Entry _nextEntry;
		
		public Itr(IteratorStartPosition itSP) {
			switch (itSP) {
			case head:
				_previousEntry = null;
				_lastReturnedEntry = null;
				_nextEntry = _head;
				break;
			case tail:
				_previousEntry = _tail;
				_lastReturnedEntry = null;
				_nextEntry = null;
				break;
			default:
				throw new IllegalArgumentException(
						"Unexpected start position argument.");
			}
		}
		
		public Itr() {
			this(IteratorStartPosition.head);
		}

		@Override
		public boolean hasNext() {
			return (_nextEntry != null);
		}

		public boolean hasPrevious() {
			return (_previousEntry != null);
		}

		@Override
		public T next() {
			if (_nextEntry == null) {
				throw new NoSuchElementException("Next element doesn't exist.");
			}
			_previousEntry = _lastReturnedEntry;
			_lastReturnedEntry = _nextEntry;
			_nextEntry = _nextEntry.get_next();
			return _lastReturnedEntry.get_data();
		}

		public T previous() {
			if (_previousEntry == null) {
				throw new NoSuchElementException(
						"Previous element doesn't exist.");
			}
			_nextEntry = _lastReturnedEntry;
			_lastReturnedEntry = _previousEntry;
			_previousEntry = _previousEntry.get_prev();
			return _lastReturnedEntry.get_data();
		}

		@Override
		public void remove() {
			if (_lastReturnedEntry == null) {
				throw new NoSuchElementException("An element doesn't exist.");
			}
			removeExistingEntry(_lastReturnedEntry);
			if (_nextEntry == null) {
				_previousEntry = null;
				_lastReturnedEntry = null;
				_nextEntry = _head;
			} else {
				_previousEntry = _nextEntry.get_prev();
				_lastReturnedEntry = _nextEntry;
				_nextEntry = _nextEntry.get_next();
			}
		}
	}

	private class DescendingItr implements Iterator<T> {
		private final Itr _itr = new Itr(IteratorStartPosition.tail);
		
		@Override
		public boolean hasNext() {
			return _itr.hasPrevious();
		}

		public boolean hasPrevious() {
			return _itr.hasNext();
		}

		@Override
		public T next() {
			return _itr.previous();
		}

		public T previous() {
			return _itr.next();
		}

		@Override
		public void remove() {
			_itr.remove();
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Entry entry = _head;
		while (entry != null) {
			sb.append(entry.get_data().toString());
			sb.append(' ');
			entry = entry.get_next();
		}
		return sb.toString();
	}
	
}
