package cisc187.util;


/**
 * Thrown by the peek and dequeue methods of a
 * {@link Queue} when there is nothing in the Queue.
 *
 */
public class EmptyQueueException extends RuntimeException {

  public EmptyQueueException(String message) {
    super(message);
  }

  public EmptyQueueException() {
    super("Error: can't dequeue an empty queue.");
  }
}
