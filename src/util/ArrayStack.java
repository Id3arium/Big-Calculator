package cisc187.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a generic stack using a raw array as a backing store.
 *
 * Elements are stored in array indices 0 .. n-1. When the array is full, it is resized to double it's current size + 1
 *
 * Taken from 'Effective Java', Joshua Bloch
 *
 * @author dparillo@sdccd.edu
 *
 * @param <E> the element stored in the Array
 */
public class ArrayStack<E> implements Stack<E> {

	private Object[] stack;       // Stack backing store
	private int n;             // count of elements in stack
	private static final int INITIAL_CAPACITY = 16;

	/**
	 * Creates an empty stack with a default size = INITIAL_CAPACITY
	 */
	public ArrayStack() {
		stack = new Object[INITIAL_CAPACITY];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * This implementation uses an array as it's backing store and needs to ensure there is room to add an element
	 * before trying.</p>
	 *
	 * @see #ensureCapacity
	 */
	@Override
	public void push(E element) {
		ensureCapacity();
		stack[n++] = element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		@SuppressWarnings("unchecked")
		E result = (E) stack[--n];
		stack[n] = null;       // Eliminate obsolete reference
		return result;
	}

	/**
	 * Allows callers to get the element from the top of the stack without removing it.
	 *
	 * <p>
	 * Not part of the Stack interface.</p>
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		@SuppressWarnings("unchecked")
		E result = (E) stack[n - 1];
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return n;
	}

	/**
	 * Verify there is enough room in the backing store to add at least 1 element.
	 *
	 * <p>
	 * If there is no space left in the array, then the storage capacity is doubled.</p>
	 */
	private void ensureCapacity() {
		if (stack.length == n) {
			stack = Arrays.copyOf(stack, 2 * n + 1);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = n - 1; i >= 0; i--) {
			sb.append(stack[i]);
			sb.append(", ");
		}
		return sb.toString();
	}

	/**
	 * Return an iterator for the elements of the stack. This iterator does not support the remove operation, but does
	 * allow clients to "peek" at the entire stack.
	 * <p>
	 * If you do need an instance of this iterator, you canb get it as
	 * </p>
	 * <pre>
	 * ArrayStack.new StackIterator()
	 * </pre>
	 */
	@Override
	public Iterator<E> iterator() {
		class StackIterator implements Iterator<E> {

			int idx;

			public StackIterator() {
				idx = 0;
			}

			public boolean hasNext() {
				return (idx < n);
			}

			public E next() {
				if (idx >= n) {
					throw new NoSuchElementException();
				}
				@SuppressWarnings("unchecked")
				E x = (E) stack[idx];
				idx++;
				return x;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		return new StackIterator();
	}

}
