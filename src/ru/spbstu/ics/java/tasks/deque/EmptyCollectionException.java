package ru.spbstu.ics.java.tasks.deque;

import java.util.NoSuchElementException;

/**
 * Thrown by elements access methods of an <code>MyDeque</code> 
 * to indicate that there are no elements in the collection.
 *
 * @author  Oleg Rekin
 * @see     ru.spbstu.ics.java.tasks.deque.MyDeque
 */
public class EmptyCollectionException extends NoSuchElementException {

	/**
     * Constructs a <code>EmptyCollectionException</code> with <tt>null</tt> 
     * as its error message string.
     */
    public EmptyCollectionException() {
	super();
    }

    /**
     * Constructs a <code>EmptyCollectionException</code>, saving a reference 
     * to the error message string <tt>s</tt> for later retrieval by the 
     * <tt>getMessage</tt> method.
     *
     * @param   s   the detail message.
     */
    public EmptyCollectionException(String s) {
	super(s);
    }
}
