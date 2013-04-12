package ru.spbstu.ics.java.tasks.blockingqueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import ru.spbstu.ics.java.tasks.deque.EmptyCollectionException;

public class MyBlockingQueue<E> implements BlockingQueue<E> {

	private class Entry {
		E _data;
		Entry _next;

		Entry() {
			_data = null;
			_next = null;
		}
	}

	private Entry _prehead;
	private Entry _tail;
	private int _size;

	private Object _lock;

	public MyBlockingQueue() {
		_lock = new Object();
		_prehead = new Entry();
		_tail = _prehead;
		_size = 0;
	}

	public MyBlockingQueue(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	private E removeExistingEntryAfter(Entry entryBeforeRemoving) {
		Entry temp = entryBeforeRemoving._next;
		entryBeforeRemoving._next = entryBeforeRemoving._next._next;
		if (entryBeforeRemoving._next == null) {
			_tail = entryBeforeRemoving;
		}
		temp._next = null;
		_size--;
		return temp._data;
	}

	private void addEntry(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		Entry entry = new Entry();
		entry._data = e;
		_tail._next = entry;
		entry._next = null;
		_tail = entry;
		_size++;
	}

	@Override
	public E remove() {
		synchronized (_lock) {
			if (_prehead._next == null) {
				throw new NoSuchElementException();
			} else {
				return removeExistingEntryAfter(_prehead);
			}
		}
	}

	@Override
	public E poll() {
		synchronized (_lock) {
			if (_prehead._next == null) {
				return null;
			} else {
				return removeExistingEntryAfter(_prehead);
			}
		}
	}

	@Override
	public E element() {
		synchronized (_lock) {
			if (_prehead._next == null) {
				throw new EmptyCollectionException();
			} else {
				return _prehead._next._data;
			}
		}
	}

	@Override
	public E peek() {
		synchronized (_lock) {
			if (_prehead._next == null) {
				return null;
			} else {
				return _prehead._next._data;
			}
		}
	}

	@Override
	public int size() {
		synchronized (_lock) {
			return _size;
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (_lock) {
			return (_size == 0);
		}
	}

	public class Iter implements Iterator<E> {
		private Entry _preposition;
		private Entry _last_preposition;
		private Object _it_lock;

		private Iter() {
			_preposition = _prehead;
			_last_preposition = null;
			_it_lock = _lock;
		}

		@Override
		public boolean hasNext() {
			return (_preposition._next != null);
		}

		@Override
		public E next() {
			if (_preposition._next == null) {
				throw new NoSuchElementException();
			} else {
				synchronized (_it_lock) {
					if (_preposition._next == null) {
						throw new NoSuchElementException();
					} else {
						_last_preposition = _preposition;
						_preposition = _preposition._next;
						return _preposition._data;
					}
				}
			}
		}

		@Override
		public void remove() {
			if (_last_preposition == null) {
				throw new NoSuchElementException();
			} else {
				synchronized (_it_lock) {
					if (_last_preposition == null) {
						throw new NoSuchElementException();
					} else {
						if (_last_preposition._next == null) {
							throw new NoSuchElementException();
						} else {
							removeExistingEntryAfter(_last_preposition);
							_last_preposition = null;
						}
					}
				}
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		synchronized (_lock) {
			return new Iter();
		}
	}

	@Override
	public Object[] toArray() {
		synchronized (_lock) {
			Object[] array = new Object[_size];
			Entry precurrent = _prehead;
			int iIndex = 0;
			while (precurrent._next != null) {
				precurrent = precurrent._next;
				array[iIndex++] = precurrent._data;
			}
			return array;
		}
	}

	@Override
	public <E> E[] toArray(E[] a) {
		synchronized (_lock) {
			if (a.length < _size) {
				a = (E[]) java.lang.reflect.Array.newInstance(
						a.getClass().getComponentType(), _size);
			}
			Entry precurrent = _prehead;
			int iIndex = 0;
			while (precurrent._next != null) {
				precurrent = precurrent._next;
				a[iIndex++] = (E) precurrent._data;
			}
			return a;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c) {
			result |= add(element);
		}
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object element : c) {
			result |= remove(element);
		}
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Entry precurrent = _prehead;
		boolean bModified = false;
		while (precurrent._next != null) {
			if (!c.contains(precurrent._next._data)) {
				removeExistingEntryAfter(precurrent);
				bModified = true;
			} else {
				precurrent = precurrent._next;
			}
		}
		return bModified;
	}

	@Override
	public void clear() {
		synchronized (_lock) {
			Entry temp;
			while (_prehead._next != null) {
				_prehead._next._data = null;
				temp = _prehead._next._next;
				_prehead._next._next = null;
				_prehead._next = temp;
			}
			_tail = _prehead;
			_size = 0;
		}
	}

	@Override
	public boolean add(E e) {
		synchronized (_lock) {
			addEntry(e);
			_lock.notify();
			return true;
		}
	}

	@Override
	public boolean offer(E e) {
		synchronized (_lock) {
			addEntry(e);
			_lock.notify();
			return true;
		}
	}

	@Override
	public void put(E e) throws InterruptedException {
		synchronized (_lock) {
			addEntry(e);
			_lock.notify();
		}
	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit)
			throws InterruptedException {
		synchronized (_lock) {
			addEntry(e);
			_lock.notify();
			return true;
		}
	}

	@Override
	public E take() throws InterruptedException {
		synchronized (_lock) {
			while (_prehead._next == null) {
				_lock.wait();
			}
			return removeExistingEntryAfter(_prehead);
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		synchronized (_lock) {
			if (_prehead._next == null) {
				_lock.wait(unit.toNanos(timeout));
			}
			if (_prehead._next == null) {
				return removeExistingEntryAfter(_prehead);
			} else {
				return null;
			}
		}
	}

	@Override
	public int remainingCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean remove(Object o) {
		synchronized (_lock) {
			Entry precurrent = _prehead;
			while (precurrent._next != null) {
				if (precurrent._next._data.equals(o)) {
					removeExistingEntryAfter(precurrent);
					return true;
				} else {
					precurrent = precurrent._next;
				}
			}
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		synchronized (_lock) {
			Entry precurrent = _prehead;
			while (precurrent._next != null) {
				if (precurrent._next._data.equals(o)) {
					return true;
				} else {
					precurrent = precurrent._next;
				}
			}
			return false;
		}
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		return drainTo(c, Integer.MAX_VALUE);
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		synchronized (_lock) {
			Entry precurrent = _prehead;
			int iIndex = 0;
			while (precurrent._next != null) {
				if (iIndex >= maxElements)
					break;
				c.add(removeExistingEntryAfter(precurrent));
				iIndex++;
			}
			return iIndex;
		}
	}

}
