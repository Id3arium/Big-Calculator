package cisc187.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a generic queue of fixed size using a raw array
 * as a backing store.
 *
 * Elements are stored in array indices 0 .. n-1.
 *
 * @author dparillo@sdccd.edu
 *
 * @param E the element stored in the Array
 */
public class ArrayQueue<E> implements Queue<E> {

  private E[] queue;            // Queue backing store
  private int maxSize;          // maximum size of queue
                                // default maximum capacity of queue
  private static final int INITIAL_CAPACITY = 16;
  private int front;            // array index of front of queue
  private int rear;             // array index of back of queue

	
  /**
   * Creates an empty bounded queue with a specified size.
   * Once this bounded queue is cfeated, it's size cannot be changed.
   * @param size the size of this queue.
   */
	public ArrayQueue(int size) {
    maxSize = size+1;
    init();
	}

  /**
   * Creates an empty queue with a default size = INITIAL_CAPACITY
   */
	public ArrayQueue() {
    this(INITIAL_CAPACITY);
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    init();
  }

  private void init() {
    rear = 0;
    front = 1;
    @SuppressWarnings("unchecked")
    E[] q = (E[]) new Object[maxSize];
    queue = q;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return ((rear+maxSize) - front + 1) % maxSize;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int capacity() {
    return maxSize-1;
  }

  

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return size() == 0;
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFull() {
    return ((rear+2) % maxSize) == front;
  }

  /**
   * {@inheritDoc}
   *
   */
  @Override
  public void enqueue(E element) throws FullQueueException {
    if (isFull()) {
      throw new FullQueueException();
    }
    rear = (rear+1) % maxSize; // Circular increment
    queue[rear] = element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E dequeue() {
    if(isEmpty()) {
      throw new EmptyQueueException();
    }
    E it = queue[front];
    front = (front+1) % maxSize; // Circular increment
    return it;

  }

  /**
   * Allows callers to get the element from the front of the queue
   * without removing it.
   */
  @Override
  public E peek() {
    if (isEmpty()) {
      throw new EmptyQueueException();
    }
    return queue[front];
  }

  
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (int i=front; i != (rear+1) % maxSize; i++) {
      i = i % maxSize;            // Adjust for wrap-around
      sb.append(queue[i]);
      sb.append(", ");
    }
    return sb.toString();
  }

  //
  //  protected accessors for testing only
  //

  int getFront() {
    return front;
  }

  int getRear() {
    return rear;
  }


  /**
	 * Return an iterator for the elements of the queue. 
	 * This iterator does not support the remove operation,
   * but does allow observing the entire queue.
   * 
	 */
	public Iterator<E> iterator() {
    return new Iterator<E> () {
			int k = front;
			
			public boolean hasNext() {
				return (k <= size());
			}
			
			public E next() {
				if (k > size()) {
          throw new NoSuchElementException();
        }
				E it = queue[((front+k)-1) % queue.length];
        k++;
				return it;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
