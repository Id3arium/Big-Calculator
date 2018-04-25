package cisc187.util;

import java.util.Iterator;

/**
 * Basic Stack interface
 *
 * @author dparillo@sdccd.edu
 *
 */
public interface Stack<E> extends Iterable<E> { 

  /**
   * Iterator for this stack.
   * Allows users to iterate over the stack elements
   * @return an iterator appropriate for this stack.
   */
  Iterator<E> iterator();


  /**
   * Tests if this stack is empty.
   * @return {@code true} if the stack has no elements.
   */
  boolean isEmpty();

  /**
   * Adds an element to the top of the stack
   *
   * @param element element to be added to the top of the stack
   */
  void push(E element);

  /**
   * Adds an element to the top of the stack
   *
   * @return {@code true} if the stack has no elements.
   */
  E pop();

  /**
   * Examines the element at the top of the stack
   *
   * @return the element at the top of the stack without removing it.
   */
  E peek();

  /**
   * Report on the current size of the stack.
   * Note that this may differ from the size of the backing store
   * where the stack is actually implemented.
   *
   * @return An integer indicating the current size of the stack.
   */
  int size();
	
}
