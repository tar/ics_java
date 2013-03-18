package ru.spbstu.ics.java.collections;

public class OurVector<T> {

	private Object[] _data;
	private int _initialSize = 4;
	private int _currentPosition=0;

	public OurVector() {
		_data = new Object[_initialSize];
	}

	public T get(int index) {
		if (index >= _data.length) {
			//
		}
		return (T) _data[index];
	}
	
	public void add(T value){
		_data[_currentPosition]=value;
	}

}
