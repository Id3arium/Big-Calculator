package cisc187.util;

import java.util.Iterator;

/**
 * An interface that specifies the familiar queue abstraction, but with
 * limited capacity.
 * <p>
 * In addition to the methods required in the definition of this interface,
 * a class implementing this interface should provide a public constructor
 * with a single argument of type <tt>int</tt>, which specifies the
 * capacity of the Queue.  The constructor should throw an
 * IllegalArgumentException if the specified capacity is negative.
 * @param E the element Object type stored in this Queue
 */
public interface Queue<E> extends Iterable<E> {

  /**
   * Iterator for this queue.
   * Allows users to iterate over the queue elements
   * @return an iterator appropriate for this queue.
   */
  Iterator<E> iterator();

  /**
   * Returns the capacity of this Queue, that is,
   * the maximum number of elements it can hold.
   * @return the capacity of this Queue
   */
  int capacity();

  
  /**
   * Tests if this queue is empty.
   * @return {@code true} if the queue has no elements.
   */
  boolean isEmpty();

  /**
   * Tests if this queue is full.
   * @return {@code true} if the queue canot store any more elements.
   */
  boolean isFull();


  /**
   * Returns the number of elements in this Queue.
   * @return the number of elements in this Queue
   */
  int size();

  /**
   * Reinitialize the queue.
   */
  void clear();

  /**
   * Adds the specified element to the tail of this Queue.
   * <br>PRECONDITION: the Queue's size is less than its capacity.
   * <br>POSTCONDITION: the element is now the tail element in this
   * Queue, none of the other elements have been changed, and
   * the size is increased by 1.
   * @param e the element to add to the queue
   * @throws FullQueueException if the Queue is at capacity.
   */
  void enqueue(E e);

  /**
   * Removes the element at the head of this Queue.
   * Returns the element removed, or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the Queue's size is greater than zero.
   * <br>POSTCONDITION: the head element in this Queue has been removed,
   * none of the other elements have been changed, and
   * the size is decreased by 1.
   * @return  the element removed, or <tt>null</tt> if the queue is empty.
   * @throws EmptyQueueException if the Queue is already empty.
   */
  E dequeue();

  /**
   * Returns the element at the head of this Queue,
   * or <tt>null</tt> if there was no such element.
   * <br>PRECONDITION: the Queue's size is greater than zero.
   * <br>POSTCONDITION: The Queue is unchanged.
   * @return  the element at the head, or <tt>null</tt> if the size was zero.
   */
  E peek();

}
