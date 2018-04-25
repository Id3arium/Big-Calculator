package cisc187.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link ArrayQueue} Unit tests
 */
public class TestArrayQueue {


  // Using ArrayQueue vice Queue in order to access protected methods.
  private ArrayQueue<Integer> foo;

  /**
   * Add <tt>N</tt> ints to the queue with a set amount of wraparound.
   */
  private void enqueueNThings(int n, int wraparound) {
    foo = new ArrayQueue<Integer>(n);
    for (int i=1; i<=n; i++) {
      foo.enqueue(i);
    }
    int k = foo.size();
    for (int i=1; i <= wraparound; i++) {
      foo.dequeue();
      foo.enqueue(++k);
    }
  }
  /**
   * Add <tt>N</tt> ints to the queue.
   */
  private void enqueueNThings(int n) {
    enqueueNThings(n, 0);
  }

  //
  // Initialization tests
  //

  @Test
  public void initDefaultConstructor() {
    foo = new ArrayQueue<Integer>();
    assertEquals("Failed to init.", 16, foo.capacity());
  }
  @Test
  public void initNonDefaultConstructor() {
    foo = new ArrayQueue<Integer>(45);
    assertEquals("Failed to init at capacity.", 45, foo.capacity());
  }
  @Test
  public void initFront() {
    foo = new ArrayQueue<>(45);
    assertEquals("Failed to init front.", 1, foo.getFront());
  }
  @Test
  public void initRear() {
    foo = new ArrayQueue<>(45);
    assertEquals("Failed to init rear.", 0, foo.getRear());
  }

  //
  // Minimal API tests
  //

  @Test
  public void enqueueOneSize() {
    foo = new ArrayQueue<Integer>();
    foo.enqueue(42);
    assertEquals("Failed to update size.", 1, foo.size());
  }
  @Test
  public void enqueueOne() {
    foo = new ArrayQueue<Integer>();
    foo.enqueue(42);
    assertEquals("Failed to enqueue item.", new Integer(42), foo.peek());
  }
  @Test
  public void enqueueOneCheckRear() {
    foo = new ArrayQueue<>();
    foo.enqueue(42);
    assertEquals("Failed to update rear.", 1, foo.getRear());
    assertEquals("Front was unexpectedly modified.", 1, foo.getFront());
  }

  @Test
  public void enqueue100AndDequque3() {
    enqueueNThings(100);
    foo.dequeue();
    foo.dequeue();
    foo.dequeue();
    assertEquals("Failed to enqueue 100 and dequeue 3 items.", new Integer(4), foo.peek());
  }

  @Test
  public void verifyQueueIsAlmostFull() {
    enqueueNThings(100);
    foo.dequeue();
    assertFalse("isFull method is broken.", foo.isFull());
  }
  @Test
  public void verifyQueueIsFull() {
    enqueueNThings(100);
    assertTrue("Failed to detect Full Queue.", foo.isFull());
  }


  //
  // Check the wraparound capability of the queue circular array
  //
  //

  @Test
  public void enqueue3CheckReferences() {
    foo = new ArrayQueue<>();
    enqueueNThings(3);
    assertEquals("Failed to update rear.", 3, foo.getRear());
    assertEquals("Front was unexpectedly modified.", 1, foo.getFront());
  }

  /**
   * This test is the "base case" - a full queue, but no wraparound yet.
   */
  @Test
  public void enqueueTen() {
    foo = new ArrayQueue<>(10);
    enqueueNThings(10);
    assertEquals("Unexpected value for front.", 1, foo.getFront());
    assertEquals("Unexpeceted value for rear.", 10, foo.getRear());
  }
  /**
   * Fills the queue, then adds and removes items just to get front 
   * and rear to shift
   */
  @Test
  public void enqueueTenWrap3CheckReferences() {
    foo = new ArrayQueue<>(10);
    enqueueNThings(10,3);

    assertEquals("Failed to update size.", 10, foo.size());
    assertEquals("Unexpected value for front.", 4, foo.getFront());
    assertEquals("Unexpeceted value for rear.", 2, foo.getRear());
  }

  /**
   * Fills the queue, then adds and removes items just to get front 
   * and rear to shift
   */
  @Test
  public void enqueueTenWrap9CheckReferences() {
    foo = new ArrayQueue<>(10);
    enqueueNThings(10,9);

    assertEquals("Unexpected value for front.", 10, foo.getFront());
    assertEquals("Unexpeceted value for rear.", 8, foo.getRear());
  }

  //
  // Check expected exceptions
  //
 
  // This test will fail if the expected exception is not thrown
  @Test(expected=FullQueueException.class)
  public void verifyQueueFullError() {
    enqueueNThings(100);
    foo.enqueue(-1);
  }

  @Test
  // This is the same test, functionally as the prior verifyQueueFullError
  // It's a bit more explicit and only catches the exception on the enqueue line.
  public void verifyQueueFullException() {
    boolean thrown = false;
    enqueueNThings(100);
    try {
      foo.enqueue(-1);
    } catch (FullQueueException e) {
      thrown = true;
    }
    assertTrue("Failed to throw expected FullQueueException.", thrown);
  }

  @Test(expected=EmptyQueueException.class)
  public void verifyEmptyQueueException() {
    foo = new ArrayQueue<>();
    foo.dequeue();
  }

  @Test(expected=EmptyQueueException.class)
  public void verifyEmptyQueueExceptionPeek() {
    foo = new ArrayQueue<>();
    foo.peek();
  }


  @Test(expected=EmptyQueueException.class)
  public void verifyEmptyQueueException2() {
    enqueueNThings(100,75);
    for (int i = 0; i <= 100; i++) {
      foo.dequeue();
    }
  }

  @Test(expected=UnsupportedOperationException.class)
  public void verifyUnsupportedOperationException() {
    enqueueNThings (10);
    Iterator<Integer> itr = foo.iterator();
    itr.remove();
  }

  @Test(expected=NoSuchElementException.class)
  public void verifyNoSuchElementException() {
    enqueueNThings (10);
    Iterator<Integer> itr = foo.iterator();
    while (itr.hasNext()) {
      itr.next();
    }
    itr.next();
  }



  //
  // Check the iterator
  //
 

  @Test
  public void queueIterator() {
    enqueueNThings (10);
    int i = 1;
    for (Integer itr : foo ) {
      assertEquals("Failed to use iterator to get item.", new Integer(i++), itr);
    }
  }

  @Test
  public void manualIterator() {
    enqueueNThings (10);
    Iterator<Integer> itr = foo.iterator();
    for (int i=1; itr.hasNext(); i++ ) {
      assertEquals("Failed to use iterator to get item.", new Integer(i), itr.next());
    }
  }


}
